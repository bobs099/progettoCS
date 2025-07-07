package com.example.otp.controller;

import com.example.otp.model.OtpRequest;
import com.example.otp.model.OtpVerificationRequest;
import com.example.otp.service.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final OtpService otpService;

    public AuthController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOtp(@RequestBody OtpRequest request) {
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP inviato.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest req) {
        boolean valid = otpService.verifyOtp(req.getEmail(), req.getOtp());
        if (valid) {
            return ResponseEntity.ok("Login avvenuto con successo!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP non valido o scaduto.");
    }
}
