package com.example.Insurance.Payload;

class OtpData {
    private final String otp;
    private final long expiryTime;

    public OtpData(String otp, long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public String getOtp() {
        return otp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
