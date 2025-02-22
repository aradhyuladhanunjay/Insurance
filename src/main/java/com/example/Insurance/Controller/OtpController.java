package com.example.Insurance.Controller;

import com.example.Insurance.Payload.JWTDTO;
import com.example.Insurance.Service.EmailService;
import com.example.Insurance.Service.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;
    private final EmailService emailService;

    public OtpController(OtpService otpService, EmailService emailService) {
        this.otpService = otpService;
        this.emailService = emailService;
    }

    // Generate OTP and send it via email
    @PostMapping("/generate")
    public String generateOtp(@RequestParam String email) {
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
        return "OTP sent successfully to " + email;
    }

    // Validate OTP
    //http://localhost:8080/otp/validate
    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(@RequestParam String email, @RequestParam String otp) {
        JWTDTO jwtdto = otpService.validateOtp(email, otp);

        if (jwtdto!=null){
            return new ResponseEntity<>(jwtdto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid or expired OTP!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
