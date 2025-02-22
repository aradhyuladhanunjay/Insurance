package com.example.Insurance.Service;

import com.example.Insurance.Entity.Customer;
import com.example.Insurance.Payload.JWTDTO;
import com.example.Insurance.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JWTtokenservice jwTtokenservice;

    private static final int EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes
    private static final Map<String, OtpData> otpStorage = new HashMap<>();
    private final Random random = new SecureRandom();
    JWTDTO jwtdto= new JWTDTO();

    // Generate OTP
    public String generateOtp(String email) {
        String otp = String.format("%06d", random.nextInt(1000000)); // 6-digit OTP
        otpStorage.put(email, new OtpData(otp, System.currentTimeMillis() + EXPIRY_TIME));
        System.out.println(otpStorage.get(email));
        return otp;
    }
    // Validate OTP
    public JWTDTO validateOtp(String email, String otp) {

        if (otpStorage.get(email) != null && String.valueOf(otpStorage.get(email).otp).equals(otp) && System.currentTimeMillis() < otpStorage.get(email).getExpiryTime()) {
            Optional<Customer> customerOptional = customerRepository.findByEmail(email);
            Customer customer = customerOptional.get();
            String generatetoken = jwTtokenservice.generatetoken(customer);
            jwtdto.setToken(generatetoken);
            jwtdto.setType("JSON Token");
            otpStorage.remove(email); // Remove OTP after successful verification
            return jwtdto;
        } else {
            return null;
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
