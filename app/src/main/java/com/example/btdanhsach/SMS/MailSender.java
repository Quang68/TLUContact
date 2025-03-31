package com.example.btdanhsach.SMS;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private final String senderEmail = "hovanq022@gmail.com";
    private final String senderPassword = "rszy wpll ngns ptfj";

    public void sendMail(String recipientEmail, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Xác thực OTP đăng nhập");
        message.setText("Chào bạn,\n\nMã OTP của bạn là: " + otp + "\nVui lòng nhập mã này để hoàn tất đăng nhập.\n\nNếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.");

        Transport.send(message);
    }
}
