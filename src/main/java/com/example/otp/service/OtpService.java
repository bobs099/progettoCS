package com.example.otp.service;

import com.example.otp.model.OtpEntry;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String, OtpEntry> otpStore = new ConcurrentHashMap<>();
    private final JavaMailSender mailSender;

    public OtpService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        OtpEntry entry = new OtpEntry(otp, System.currentTimeMillis() + 5 * 60 * 1000);
        otpStore.put(email, entry);
        sendEmail(email, otp);
    }

    public boolean verifyOtp(String email, String otp) {
        OtpEntry entry = otpStore.get(email);
        return entry != null && entry.getCode().equals(otp) && entry.getExpiresAt() > System.currentTimeMillis();
    }

    private void sendEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Il tuo codice OTP");
        message.setText("Il tuo codice OTP è: " + otp + ". Scade tra 5 minuti.");
        mailSender.send(message);
    }
}
