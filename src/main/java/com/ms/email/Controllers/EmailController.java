package com.ms.email.Controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.email.Dtos.EmailDto;
import com.ms.email.Models.EmailModel;
import com.ms.email.Services.EmailService;

import jakarta.validation.Valid;

@RestController
public class EmailController{

    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<EmailModel>(emailModel, HttpStatus.CREATED);
    }
}
