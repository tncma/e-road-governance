package com.roadgovern.utils;


import java.util.ArrayList;
import java.util.Iterator;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.roadgovern.vo.MailAttachmentVO;
import com.roadgovern.vo.MailUtilVO;

public class MailClient
{

    public MailClient()
    {
        logger = Logger.getLogger("Email");
    }

    public void sendMail(MailUtilVO mailUtilVO)
    {
        MimeMessage message = mailSender.createMimeMessage();
        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailUtilVO.getFrom());
            if(mailUtilVO.getToOverride() != null && mailUtilVO.getToOverride().trim().equals("Y"))
            {
                helper.setTo(splitAddresses(mailUtilVO.getMailTo()));
            } else
            {
                helper.setTo(splitAddresses(mailUtilVO.getTo()));
                if(mailUtilVO.getCc() != null && mailUtilVO.getCc().trim().length() > 0)
                    helper.setCc(splitAddresses(mailUtilVO.getCc()));
                if(mailUtilVO.getBcc() != null && mailUtilVO.getBcc().trim().length() > 0)
                    helper.setBcc(splitAddresses(mailUtilVO.getBcc()));
            }
            helper.setSubject(mailUtilVO.getSubject());
            helper.setText(mailUtilVO.getBodyContent(), true);
            ArrayList attachmentList = mailUtilVO.getAttachmentList();
            ByteArrayResource file = null;
            if(attachmentList != null && attachmentList.size() > 0)
            {
                for(Iterator iterator = attachmentList.iterator(); iterator.hasNext();)
                {
                    MailAttachmentVO attachment = (MailAttachmentVO)iterator.next();
                    if(attachment != null)
                    {
                        file = new ByteArrayResource(attachment.getFileData());
                        helper.addAttachment((new StringBuilder(String.valueOf(attachment.getFileName()))).append("\\.").append(attachment.getFileType()).toString(), file);
                    }
                }

            }
            if(mailUtilVO.isImportant())
                message.setHeader("X-Priority", "1");
            mailSender.send(message);
        }
        catch(Exception exception)
        {
            logger.error((new StringBuilder("Error Sending Mail - ")).append(exception.getMessage()).toString(), exception);
        }
    }

    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public String[] splitAddresses(String emails)
    {
        if(emails != null && emails.split(",") != null && emails.split(",").length > 1)
            return emails.split(",");
        else
            return (new String[] {
                emails
            });
    }

    private JavaMailSender mailSender;
    Logger logger;
}