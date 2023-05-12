package com.example.backendapimbanking.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Meta<T>{
        private String to;
        private String from;
        private String subject;
        private String templatesUrl;
        private T data;
    }
    public void send(Meta<?>meta)throws MessagingException {
      MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
        Context context=new Context();
        context.setVariable("data",meta.data);
       String templatesHtml=templateEngine.process(meta.templatesUrl,context);
        messageHelper.setText(templatesHtml,true);
        messageHelper.setTo(meta.to);
        messageHelper.setFrom(meta.from);
        messageHelper.setSubject(meta.subject);
       javaMailSender.send(mimeMessage);
    }
}
