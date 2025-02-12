package com.example.Insurance.Service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;


    private String fromEmail="aradhyuladhanunjay@gmail.com";

    public void sendOtpEmail(String toEmail, String otp) {
        Email from = new Email(fromEmail);
        String subject = "Your OTP Code";
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", "Your OTP code is: " + otp);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
