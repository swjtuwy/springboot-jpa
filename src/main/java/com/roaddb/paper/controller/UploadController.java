package com.roaddb.paper.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roaddb.paper.exception.InnerException;
import com.roaddb.paper.util.Tool;

@Component
public class UploadController {

    @Value("${upload.file.path}")
    String basePath;

    public String uploadFile(boolean isCheck, MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        if (isCheck && !(Tool.checkFile(filename))) {
            throw new InnerException(400, "file is valid");
        }
        String relatePath = File.separator + Tool.initFolder();
        String filePath = basePath + relatePath;
        try {
            Tool.uploadFile(file.getBytes(), filePath, filename);
        } catch (Exception e) {
            throw new InnerException(400, "file upload failed");
        }
        return relatePath;
    }
}
