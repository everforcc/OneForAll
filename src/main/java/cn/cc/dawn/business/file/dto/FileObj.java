package cn.cc.dawn.business.file.dto;

public class FileObj {

    private String md5;
    private String fileName;
    private long range;
    private long size;

    public FileObj() {
    }

    public FileObj(String md5, String fileName, long range, long size) {
        this.md5 = md5;
        this.fileName = fileName;
        this.range = range;
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
