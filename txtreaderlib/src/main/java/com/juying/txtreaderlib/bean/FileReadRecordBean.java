package com.juying.txtreaderlib.bean;

/*用于存储和记录用户在特定文件上的阅读位置和信息*/
public class FileReadRecordBean {
    public String fileHashName;
    public int id;
    public String fileName;
    public String filePath;
    public int paragraphIndex;
    public int chartIndex;

    @Override
    public String toString() {
        return "FileReadRecordBean{" +
                "fileHashName='" + fileHashName + '\'' +
                ", id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", paragraphIndex='" + paragraphIndex + '\'' +
                ", chartIndex='" + chartIndex + '\'' +
                '}';
    }
}
