package com.wuhai.retrofit.bean;

import java.io.File;

/**
 * Created by wangC on 2017/8/11.
 */

public class UpLoadFile {

    private String fileName;

    private File file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
