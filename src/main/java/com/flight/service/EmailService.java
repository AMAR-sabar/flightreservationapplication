package com.flight.service;

// Java Program to Illustrate Creation Of
// Service Interface
// Importing required classes


import com.flight.entities.EmailDetails;

// Interface
public interface EmailService {

    // Method
    // To send a simple email
    //String sendSimpleMail(String to, String sub, String body);
    void sendSimpleMail(String to, EmailDetails emailDetails);

    // Method
    // To send an email with attachment
    // String sendMailWithAttachment(EmailDetails details);
}