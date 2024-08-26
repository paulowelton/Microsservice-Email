package com.ms.email.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.email.Enums.StatusEmail;
import com.ms.email.Models.EmailModel;
import com.ms.email.Repositories.EmailRepository;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailrepository;

    public void sendEmail(EmailModel emailModel){
        emailModel.setEmailDateSend(LocalDateTime.now());
        try{
            
        }catch(MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally{
            return emailrepository.save(emailModel);
        }
        
    }
}
