package com.roadgovern.vo;



import java.util.ArrayList;

public class MailUtilVO
{

    public MailUtilVO()
    {
        isImportant = false;
    }

    public boolean isImportant()
    {
        return isImportant;
    }

    public void setImportant(boolean isImportant)
    {
        this.isImportant = isImportant;
    }

    public String getTechSupportTo()
    {
        return techSupportTo;
    }

    public void setTechSupportTo(String techSupportTo)
    {
        this.techSupportTo = techSupportTo;
    }

    public String getSequenceErrorTo()
    {
        return sequenceErrorTo;
    }

    public void setSequenceErrorTo(String sequenceErrorTo)
    {
        this.sequenceErrorTo = sequenceErrorTo;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }

    public String getBcc()
    {
        return bcc;
    }

    public void setBcc(String bcc)
    {
        this.bcc = bcc;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBodyContent()
    {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent)
    {
        this.bodyContent = bodyContent;
    }

    public ArrayList getAttachmentList()
    {
        return attachmentList;
    }

    public void setAttachmentList(ArrayList attachmentList)
    {
        this.attachmentList = attachmentList;
    }

    public String getSequenceErrorFrom()
    {
        return sequenceErrorFrom;
    }

    public void setSequenceErrorFrom(String sequenceErrorFrom)
    {
        this.sequenceErrorFrom = sequenceErrorFrom;
    }

    public String getToOverride()
    {
        return toOverride;
    }

    public void setToOverride(String toOverride)
    {
        this.toOverride = toOverride;
    }

    public String getMailTo()
    {
        return mailTo;
    }

    public void setMailTo(String mailTo)
    {
        this.mailTo = mailTo;
    }

    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String bodyContent;
    private String sequenceErrorFrom;
    private String sequenceErrorTo;
    private String toOverride;
    private String mailTo;
    private String techSupportTo;
    private boolean isImportant;
    private ArrayList attachmentList;
}