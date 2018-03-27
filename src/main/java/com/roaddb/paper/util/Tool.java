package com.roaddb.paper.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class Tool {

    private static final Logger logger = LoggerFactory.getLogger(Tool.class);

    /**
     * upload file.
     */
    public static boolean uploadFile(byte[] file, String filePath, String fileName) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            boolean result = targetFile.mkdirs();
            if (!result) {
                return false;
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
            out.write(file);
            out.flush();
            out.close();
        } catch (IOException e) {

            return false;
        }
        return true;
    }

    public static boolean checkFile(String fileName) {
        if (fileName.endsWith("pdf")) {
            return true;
        }
        return false;
    }

    public static String initFolder() {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        //System.out.println(bartDateFormat.format(date));
        return bartDateFormat.format(date);
    }

    private static void delFile(String filePath, boolean delDir) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    // files[i].delete();
                    if (files[i].isDirectory()) {
                        delFile(files[i].getAbsolutePath(), delDir);
                    } else {
                        files[i].delete();
                    }
                }
            }
            if (delDir) {
                file.delete();
            }
        } else {
            file.delete();
            logger.debug("delete file:" + filePath);
        }

    }

    public static void delFile(String filePath) {
        delFile(filePath, false);
    }

    public static void delDir(String filePath) {
        delFile(filePath, true);
    }


    /**
     * zip file.
     */
    public static boolean zipFiles(List<File> srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            if (!CollectionUtils.isEmpty(srcfile)) {
                for (File file : srcfile) {
                    FileInputStream in = new FileInputStream(file);
                    out.putNextEntry(new ZipEntry(file.getName()));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                }
            }
            out.close();
            logger.info("compress zip finished");
        } catch (Exception e) {
            logger.error("compress zip failed", e);
            return false;
        }
        return true;
    }

    /*
    public static void main(String[] args) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        System.out.println(bartDateFormat.format(date));
    }
    */
}
