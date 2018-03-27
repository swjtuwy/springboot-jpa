package com.roaddb.paper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.roaddb.paper.model.Code;
import com.roaddb.paper.exception.InnerException;
import com.roaddb.paper.model.Paper;
import com.roaddb.paper.repository.CodeRepository;
import com.roaddb.paper.repository.PaperRepository;

@Component
public class PaperController {

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


}
