package com.example.Insurance.Controller;

import com.example.Insurance.Service.OtpService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class ProtectedController {

    private final OtpService otpService;

    public ProtectedController(OtpService otpService) {
        this.otpService = otpService;
    }


    //http://localhost:8080/secure/data
    @GetMapping("/data")
    public String getSecureData(@RequestParam String email, @RequestParam String otp) {
        if (otpService.validateOtp(email, otp)) {
            return "Secure data accessed successfully!";
        } else {
            return "Invalid OTP!";
        }
    }
}

