package com.roaddb.paper.controller;

import java.io.File;
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
public class CodeController {

    @Value("${upload.file.path}")
    String basePath;

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    UploadController uploadController;

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    /**
     * Add codes.
     */
    @Transactional
    public boolean addCode(Long paperId, List<MultipartFile> codeFiles) throws Exception {
        Paper paper = paperRepository.findOne(paperId);
        if (paper == null) {
            throw new InnerException(400, "paper not exist");
        }
        if (!CollectionUtils.isEmpty(codeFiles)) {
            for (MultipartFile code : codeFiles) {
                String codePath = uploadController.uploadFile(false, code);
                if (StringUtils.isEmpty(codePath)) {
                    String codeMsg = code.getOriginalFilename() + "upload failed.";
                    throw new InnerException(400, codeMsg);
                }
                Code codeModel = new Code();
                codeModel.setPaper(paper);
                codeRepository.save(codeModel);
            }
        }
        return true;
    }

    @Transactional
    public boolean deleteCode(List<Long> ids) throws Exception {
        if (!CollectionUtils.isEmpty(ids)) {
            for (Long id : ids) {
                Code code = codeRepository.findOne(id);
                if (code == null) {
                    throw new InnerException(400, "delete code failed.");
                }
                Tool.delFile(basePath + File.separator + code.getPath());
                codeRepository.delete(id);
            }
        }
        return true;
    }
}
