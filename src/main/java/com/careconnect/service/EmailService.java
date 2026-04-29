package com.careconnect.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class EmailService {

  public void sendEmail(String to, String subject, String body) {

    Email from = new Email("gogisettytulasi@gmail.com"); // verify in SendGrid
    Email toEmail = new Email(to);

    Content content = new Content("text/plain", body);
    Mail mail = new Mail(from, subject, toEmail, content);

    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      Response response = sg.api(request);

      System.out.println("✅ Email sent: " + response.getStatusCode());

    } catch (IOException ex) {
      System.out.println("❌ Email failed: " + ex.getMessage());
    }
  }
}