package com.roaddb.paper.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.roaddb.paper.exception.InnerException;
import com.roaddb.paper.model.Code;
import com.roaddb.paper.model.Paper;
import com.roaddb.paper.repository.CodeRepository;
import com.roaddb.paper.repository.PaperRepository;
import com.roaddb.paper.util.Tool;

@Component
public class PaperController {

    private static final Logger logger = LoggerFactory.getLogger(PaperController.class);

    @Value("${upload.file.path}")
    String basePath;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    UploadController uploadController;

    /**
     *  add paper.
     */
    @Transactional
    public long addPaper(Paper paper, MultipartFile paperFile, List<MultipartFile> codeFiles) throws Exception {
        String paperPath = uploadController.uploadFile(true, paperFile);
        if (StringUtils.isEmpty(paperPath)) {
            String paperMsg = paperFile.getOriginalFilename() + "upload failed.";
            throw new InnerException(400,paperMsg);
        }
        paper.setPath(paperPath);
        Paper result = paperRepository.save(paper);
        if (!CollectionUtils.isEmpty(codeFiles)) {
            for (MultipartFile code : codeFiles) {
                String codePath = uploadController.uploadFile(false, code);
                if (StringUtils.isEmpty(codePath)) {
                    String codeMsg = code.getOriginalFilename() + "upload failed.";
                    throw new InnerException(400, codeMsg);
                }
                Code codeModel = new Code();
                codeModel.setPaper(result);
                codeRepository.save(codeModel);
            }
        }
        return result.getId();
    }

    @Transactional
    public long updatePaper(Paper paper) throws Exception {
        Long paperId = paper.getId();
        Paper paperDb = paperRepository.findOne(paperId);
        if (paperDb == null) {
            logger.error("paper not exits {}", paperId);
            throw new InnerException(400, "paper not exits {}");
        }
        Paper res = paperRepository.save(paper);
        return res.getId();
    }

    /**
     * update paper file.
     */
    @Transactional
    public long updatePaperFile(Long paperId, MultipartFile paperFile) throws Exception {
        Paper paperDb = paperRepository.findOne(paperId);
        if (paperDb == null) {
            logger.error("paper not exits {}", paperId);
            throw new InnerException(400, "paper not exits");
        }
        String fileName = paperFile.getOriginalFilename();
        int count = paperRepository.findPaperCountExcpetId(paperId, fileName);
        if (count > 0) {
            logger.error("paper file name duplicate: {}", fileName);
            throw new InnerException(400, "paper file name duplicate: " + fileName);
        }
        String filePath = basePath + paperDb.getPath();
        Tool.delFile(filePath);
        String paperPath = uploadController.uploadFile(true, paperFile);
        paperDb.setFilename(fileName);
        paperDb.setPath(paperPath);
        Paper result = paperRepository.save(paperDb);
        return result.getId();
    }
}
