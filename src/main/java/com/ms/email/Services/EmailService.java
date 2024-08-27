package com.ms.email.Services;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.email.Enums.StatusEmail;
import com.ms.email.Models.EmailModel;
import com.ms.email.Repositories.EmailRepository;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailrepository;

    public void sendEmail(EmailModel emailModel){
        emailModel.setEmailDateSend(LocalDateTime.now());
        
        Email email = new Email();

        email.setFrom("name", emailModel.getEmailFrom());
        email.addRecipient("mame", emailModel.getEmailTo());
        email.setSubject(emailModel.getSubject());
        email.setPlain(emailModel.getText());
        email.setHtml("<p>Email Teste</p>");

        MailerSend ms = new MailerSend();

        ms.setToken("mlsn.8014322607f4e60bff344012ee84645a70788ae5ad9828133b2ffe8b7714cf0d");

        try{
            MailerSendResponse response = ms.emails().send(email);
            System.out.println(response.messageId);
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailerSendException e){
            e.printStackTrace();
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally{
            emailrepository.save(emailModel);
            
        }
    }
}
