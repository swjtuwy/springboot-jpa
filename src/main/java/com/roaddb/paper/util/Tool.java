package com.roaddb.paper.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tool {

    /**
     * upload file.
     */
    public static boolean uploadFile(byte[] file, String filePath, String fileName) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            boolean result = targetFile.mkdirs();
            if (!result){

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


    public static void main(String[] args) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        System.out.println(bartDateFormat.format(date));
    }
}
