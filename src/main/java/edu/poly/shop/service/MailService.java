/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import edu.poly.shop.model.MailModel;

@Service
public class MailService {
    
    @Autowired
    JavaMailSender javaMailSender;
    
    List<MimeMessage> queueMimeMessage = new ArrayList<>();
    
    public void push(String to, String subject, String body) {
        MailModel mailModel = new MailModel(to, subject, body);
        push(mailModel);
    }
    
    public void push(MailModel mailModel) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setFrom(mailModel.getFrom());
            mimeMessageHelper.setTo(mailModel.getTo());
            mimeMessageHelper.setSubject(mailModel.getSubject());
            mimeMessageHelper.setText(mailModel.getBody(), true);
            for (String str : mailModel.getBcc()) {
                mimeMessageHelper.addBcc(str);
            }
            for (String str : mailModel.getCc()) {
                mimeMessageHelper.addCc(str);
            }
            for (File file : mailModel.getFiles()) {
                mimeMessageHelper.addAttachment(file.getName(), file);
            }
            queueMimeMessage.add(mimeMessage);
        } catch (MessagingException ex) {
        }
    }
    
    @Scheduled(fixedRate = 3000)
    private void scheduleMail() {
        if (!queueMimeMessage.isEmpty()) {
            MimeMessage mimeMessage = queueMimeMessage.remove(0);
            javaMailSender.send(mimeMessage);
        }
    }
}
