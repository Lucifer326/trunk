package com.jeecg.mailSendAndReceiving.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMailService {
    public void send(String sender, String content, String receive, String title, List imgList, List attachmentList) {
        Properties props = new Properties();
        // 使用协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 协议地址
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        // 协议端口
//		props.setProperty("mail.smtp.port", "465");
        // 是否授权验证
        props.setProperty("mail.smtp.auth", "true");
        // QQ邮箱的SSL安全认证
//		props.setProperty("mail.smtp.socketFactory.class", "javax.net.SocketFactory");
        // 不支持SSL认证的邮件一概不处理
//		props.setProperty("mail.smtp.socketFactory.fallback", "false");
//		props.setProperty("mail.smtp.socketFactory.port", "465");
//		与邮箱服务器建立连接
        Session session = Session.getInstance(props);
//		session.setDebug(true);

        try {
            // 创建邮件
            MimeMessage message = null;
//			判断正文中是否存在图片和附件
            if (imgList.size() == 0 && attachmentList.size() == 0) {
//				，如果没有则创建文本邮件
                message = createTextMessage(session, sender, content, title, receive);
            } else if (imgList.size() > 0 && attachmentList.size() == 0) {
//				，如果有图片没有附件，则创建图文邮件
                message = createImageMessage(session, sender, content, title, receive, imgList);
            } else if (imgList.size() == 0 && attachmentList.size() > 0) {
//				，如果没有图片有附件，则创建文本附件邮件
                message = createAttachmentMessage(session, sender, content, title, receive, attachmentList);
            } else if (imgList.size() > 0 && attachmentList.size() > 0) {
//              ，如果既有图片又有附件，则创建图片附件文本邮件
                message = createMimeMessage(session, sender, content, title, receive, imgList, attachmentList, null, null);
            }

            Transport transport = session.getTransport();// 创建连接对象
            transport.connect("1441249420@qq.com", "nbiozlolsotsgdaf");// 打开连接
            transport.sendMessage(message, message.getAllRecipients());// getAllRecipients获取所有收件人
            transport.close();
        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
        }
    }

    //	创建普通文本邮件

    public MimeMessage createTextMessage(Session session, String sender, String content, String title, String receive)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        Address address = new InternetAddress(sender, "发件人的名字", "utf8");
        message.setFrom(new InternetAddress(sender));// 设置收件人
        message.setSubject(title, "utf8");// 设置邮件主题
        message.setContent(content, "text/html;charset=UTF-8");// 设置正文
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "收件人的名字", "utf8"));// 普通件
        message.setSentDate(new Date());// 设置发件时间
        message.saveChanges();// 保存邮件
        return message;
    }

    //    创建图片文本邮件
    private MimeMessage createImageMessage(Session session, String sender, String content, String title, String receive, List imgList) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);

        // 简单邮件：主题、正文、发件人、收件人 复杂邮件：主题、正文、{图片、附件、}发件人、收件人
        Address address = new InternetAddress(sender, "发件人的名字", "utf8");
        message.setFrom(new InternetAddress(sender));// 设置收件人
        message.setSubject(title, "utf8");// 设置邮件主题
        message.setContent(content, "text/html;charset=UTF-8");// 设置正文

//		创建图文复合总节点
        MimeMultipart mm_text_image = new MimeMultipart();

        for (int i = 1; i <= imgList.size(); i++) {
            // 增加图片节点
            MimeBodyPart imagePart = new MimeBodyPart();

            File file = new File((String) imgList.get(i - 1));
            DataHandler imageDataHandler = new DataHandler(new FileDataSource(file));// 图片地址
            imagePart.setDataHandler(imageDataHandler);
//			创建图片节点ID
            imagePart.setContentID("image" + i);
//			将图片节点添加到图文复合总节点中
            mm_text_image.addBodyPart(imagePart);
        }


        // 创建文本节点:目的是为了加载图片节点
        MimeBodyPart textPart = new MimeBodyPart();
//		textPart.setContent(content + "<image src='cid:image1'/>" + "正文下部", "text/html;charset=UTF-8");
//		获取图片路径在正文中的位置,将图片路径替换为图片节点ID
        int i = 0;
        int indexOf = -1;
        do {

            i++;
            indexOf = content.indexOf("img src", indexOf + 1);

            if (indexOf > 0) {
                int s1 = indexOf + 9;
                int s2 = s1 + 49;
                String subBefore = content.substring(0, s1);
                String subAfter = content.substring(s2);
                content = subBefore + "cid:image" + i + subAfter;
            }

        } while (indexOf > 0);
//		设置正文
        textPart.setContent(content, "text/html;charset=UTF-8");

        // 将文本节点与图片节点组装成复合节点
        mm_text_image.addBodyPart(textPart);
        mm_text_image.setSubType("related");// 复合关系

        // 因为：正文中只能出现普通节点MimeBodyPart，不能出现复合节点MimeMultipart
        // 所以：将MimeMultipart--->MimeBodyPart
//        MimeBodyPart textImageBodyPart = new MimeBodyPart();
//        textImageBodyPart.setContent(mm_text_image);

        // 图片 -> 文本节点 -> 复合节点 -> 根据需求 变成普通节点


        message.setContent(mm_text_image, "text/html;charset=UTF-8");// 设置正文图片附件
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "收件人的名字", "utf8"));// 普通件
        message.setSentDate(new Date());// 设置发件时间
        message.saveChanges();// 保存邮件

        return message;
    }

    //    创建附件文本邮件
    private MimeMessage createAttachmentMessage(Session session, String sender, String content, String title, String receive, List attachmentList) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        // 简单邮件：主题、正文、发件人、收件人 复杂邮件：主题、正文、{图片、附件、}发件人、收件人
        Address address = new InternetAddress(sender, "发件人的名字", "utf8");
        message.setFrom(new InternetAddress(sender));// 设置收件人
        message.setSubject(title, "utf8");// 设置邮件主题
        message.setContent(content, "text/html;charset=UTF-8");// 设置正文

//        创建附件文本总节点
        MimeMultipart mm_text_attachment = new MimeMultipart();

//        增加所有附件
        for (int i = 1; i <= attachmentList.size(); i++) {
//        创建附件节点
            MimeBodyPart attachment = new MimeBodyPart();
            // 增加附件
            File file = new File((String) attachmentList.get(i - 1));
            DataHandler attachmentDataHandler = new DataHandler(new FileDataSource(file));// 附件地址
            attachment.setDataHandler(attachmentDataHandler);
            // 给附件设置文件名,并用MimeUtility.encodeText处理乱码
            attachment.setFileName(MimeUtility.encodeText(attachmentDataHandler.getName()));
            mm_text_attachment.addBodyPart(attachment);
        }

        // 创建文本节点:目的是为了结合附件节点
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(content, "text/html;charset=UTF-8");
        // 将文本节点与附件节点组装成复合节点
        mm_text_attachment.addBodyPart(textPart);
        mm_text_attachment.setSubType("related");// 复合关系
        message.setContent(mm_text_attachment, "text/html;charset=UTF-8");// 设置正文图片附件
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "收件人的名字", "utf8"));// 普通件
        message.setSentDate(new Date());// 设置发件时间
        message.saveChanges();// 保存邮件

        return message;
    }

    //	MimeMessage:邮件
    public MimeMessage createMimeMessage(Session session, String sender, String content, String title, String receive, List imgList, List attachmentList,
                                         String CCreceive, String BCCreceive) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
//		创建图文复合总节点
        MimeMultipart mm_text_image = new MimeMultipart();

        // 简单邮件：主题、正文、发件人、收件人 复杂邮件：主题、正文、{图片、附件、}发件人、收件人
        Address address = new InternetAddress(sender, "发件人的名字", "utf8");
        message.setFrom(new InternetAddress(sender));// 设置收件人
        message.setSubject(title, "utf8");// 设置邮件主题
        message.setContent(content, "text/html;charset=UTF-8");// 设置正文

        for (int i = 1; i <= imgList.size(); i++) {
            // 增加图片节点
            MimeBodyPart imagePart = new MimeBodyPart();

            File file = new File((String) imgList.get(i - 1));
            DataHandler imageDataHandler = new DataHandler(new FileDataSource(file));// 图片地址
            imagePart.setDataHandler(imageDataHandler);
//			创建图片节点ID
            imagePart.setContentID("image" + i);
//			将图片节点添加到图文复合总节点中
            mm_text_image.addBodyPart(imagePart);
        }


        // 创建文本节点:目的是为了加载图片节点
        MimeBodyPart textPart = new MimeBodyPart();
//		textPart.setContent(content + "<image src='cid:image1'/>" + "正文下部", "text/html;charset=UTF-8");
//		获取图片路径在正文中的位置,将图片路径替换为图片节点ID
        int i = 0;
        int indexOf = -1;
        do {

            i++;
            indexOf = content.indexOf("img src", indexOf + 1);

            if (indexOf > 0) {
                int s1 = indexOf + 9;
                int s2 = s1 + 49;
                String subBefore = content.substring(0, s1);
                String subAfter = content.substring(s2);
                content = subBefore + "cid:image" + i + subAfter;
            }

        } while (indexOf > 0);
//		设置正文
        textPart.setContent(content, "text/html;charset=UTF-8");

        // 将文本节点与图片节点组装成复合节点
//		MimeMultipart mm_text_image = new MimeMultipart();此行上移
//		mm_text_image.addBodyPart(imagePart);此行上移
        mm_text_image.addBodyPart(textPart);
        mm_text_image.setSubType("related");// 复合关系

        // 因为：正文中只能出现普通节点MimeBodyPart，不能出现复合节点MimeMultipart
        // 所以：将MimeMultipart--->MimeBodyPart
        MimeBodyPart textImageBodyPart = new MimeBodyPart();
        textImageBodyPart.setContent(mm_text_image);

        // 图片 -> 文本节点 -> 复合节点 -> 根据需求 变成普通节点
        // 将 附件节点 和 原复合节点转换成的普通节点 组装成 混合节点
        MimeMultipart mm_textImageBodyPart_attachment = new MimeMultipart();
        // 附件
        //        创建附件文本总节点
        MimeMultipart mm_text_attachment = new MimeMultipart();

//        增加所有附件
        for (int j = 1; j <= attachmentList.size(); j++) {
//        创建附件节点
            MimeBodyPart attachment = new MimeBodyPart();
            // 增加附件
            File file = new File((String) attachmentList.get(j - 1));
            DataHandler attachmentDataHandler = new DataHandler(new FileDataSource(file));// 附件地址
            attachment.setDataHandler(attachmentDataHandler);
            // 给附件设置文件名,并用MimeUtility.encodeText处理乱码
            attachment.setFileName(MimeUtility.encodeText(attachmentDataHandler.getName()));
            mm_text_attachment.addBodyPart(attachment);
            mm_textImageBodyPart_attachment.addBodyPart(attachment);
        }


        mm_textImageBodyPart_attachment.addBodyPart(textImageBodyPart);
        mm_textImageBodyPart_attachment.setSubType("mixed");// 混合关系

        message.setContent(mm_textImageBodyPart_attachment, "text/html;charset=UTF-8");// 设置正文图片附件

//		message.setRecipients(type, addresses);//设置多个收件人{普通收件人，抄送，密送}
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "收件人的名字", "utf8"));// 普通件
//		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(CCreceive, "抄送人的名字" ,"utf8"));//抄送
//		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(BCCreceive, "密送人的名字" ,"utf8"));//密送
        message.setSentDate(new Date());// 设置发件时间
        message.saveChanges();// 保存邮件
        return message;
    }

}