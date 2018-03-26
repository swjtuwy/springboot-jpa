package com.roaddb.paper.util;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

@Component
public class Tool {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
