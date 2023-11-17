package com.kyeiiih.twitterclone.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class OTPHarshToCode {
    public static void main(String[] args) {
        String otpHash = "$argon2id$v=19$m=65536,t=8,p=2$PoLs/uHhlxXsooD+IIocYA$KyOyQ0pf53WEthU4D0K+Apra1NY0vQEIf6xDbQWCa4U";

        // Convert the OTP hash to a byte array
        byte[] otpHashBytes = Base64.getDecoder().decode(otpHash.substring(13)); // Extracting the Base64 portion

        // Use a cryptographic hash function to create a digest
        byte[] digest = getDigest(otpHashBytes);

        // Extract the first 2 bytes of the digest and convert them to a 4-digit code
        int code = ((digest[0] & 0xFF) << 8) | (digest[1] & 0xFF);
        code %= 10000; // Ensure it's a 4-digit code

        System.out.println("Generated 4-digit code: " + String.format("%04d", code));

    }

    private static byte[] getDigest(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error creating digest", e);
        }
    }
}
