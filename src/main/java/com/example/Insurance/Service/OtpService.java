package com.example.Insurance.Service;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private static final int EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes
    private static final Map<String, OtpData> otpStorage = new HashMap<>();
    private final Random random = new SecureRandom();

    // Generate OTP
    public String generateOtp(String email) {
        String otp = String.format("%06d", random.nextInt(1000000)); // 6-digit OTP
        otpStorage.put(email, new OtpData(otp, System.currentTimeMillis() + EXPIRY_TIME));
        System.out.println(otpStorage.get(email));
        return otp;
    }
    // Validate OTP
    public boolean validateOtp(String email, String otp) {

        if (otpStorage.get(email) != null && String.valueOf(otpStorage.get(email).otp).equals(otp) && System.currentTimeMillis() < otpStorage.get(email).getExpiryTime()) {
            otpStorage.remove(email); // Remove OTP after successful verification
            return true;
        } else {
            System.out.println("OTP validation failed!");
            return false;
        }

    }

    // Inner class to store OTP and expiry time
    private static class OtpData {
        private final String otp;
        private final long expiryTime;

        public OtpData(String otp, long expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return this.otp;
        }

        public long getExpiryTime() {
            return this.expiryTime;
        }
    }
}
