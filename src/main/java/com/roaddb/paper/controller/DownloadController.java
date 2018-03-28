package com.roaddb.paper.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.roaddb.paper.exception.InnerException;
import com.roaddb.paper.model.Paper;
import com.roaddb.paper.repository.PaperRepository;
import com.roaddb.paper.util.Tool;

@Component
public class DownloadController {

    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @Value("${download.file.path}")
    String downloadPath;

    @Value("${upload.file.path}")
    String basePath;

    @Autowired
    PaperRepository paperRepository;

    /**
     * compress files.
     */
    public String compressFile(List<Long> ids) throws Exception {
        List<File> srcfiles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ids)) {
            for (Long id : ids) {
                Paper paper = paperRepository.findOne(id);
                if (paper == null) {
                    throw new InnerException(400, "get paper failed");
                }
                File file = new File(basePath + paper.getPath());
                if (!file.exists() || !file.isFile() || !paper.getPath().endsWith("pdf")) {
                    throw new InnerException(400, "paper not exists");
                } else {
                    srcfiles.add(file);
                }
            }
        }
        String downloadPath = this.downloadPath + File.separator + Tool.initFolder();
        File downloadFile = new File(downloadPath);
        if (!downloadFile.exists() && !downloadFile.mkdirs()) {
            throw new InnerException(400, "create download failed");
        }
        String targetPath = downloadPath + File.separator + System.currentTimeMillis() + ".zip";
        File targetFile = new File(targetPath);
        boolean result = Tool.zipFiles(srcfiles, targetFile);
        if (!result) {
            throw new InnerException(400, "compress file failed.");
        }
        return targetPath;
    }
}
