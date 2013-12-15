package com.roadgovern.vo;



public class MailAttachmentVO
{

    public MailAttachmentVO()
    {
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public byte[] getFileData()
    {
        return fileData;
    }

    public void setFileData(byte fileData[])
    {
        this.fileData = fileData;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    private String fileName;
    private byte fileData[];
    private String fileType;
}