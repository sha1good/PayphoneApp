package com.luv2code.payphoneApp.outside;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.UUID;

public class InterswitchAuth {

    public static HashMap<String, String> generateInterswitchAuth(
            String httpMethod, String resourceUrl, String clientId,
            String clientSecretKey, String additionalParameters,
            String signatureMethod) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        HashMap<String, String> interswitchAuth = new HashMap<String, String>();

        //Timezone MUST be Africa/Lagos.
        TimeZone lagosTimeZone = TimeZone.getTimeZone("Africa/Lagos");

        Calendar calendar = Calendar.getInstance(lagosTimeZone);

        // Timestamp must be in seconds.
        long timestamp = calendar.getTimeInMillis() / 1000;

        UUID uuid = UUID.randomUUID();
        String nonce = uuid.toString().replaceAll("-", "");

        String clientIdBase64 = new String(Base64.encodeBase64(clientId
                .getBytes()));
        String authorization = Constants.AUTHORIZATION_REALM + " " + clientIdBase64;

        //resourceUrl = resourceUrl.replace("http://", "https://");
        String encodedResourceUrl = URLEncoder.encode(resourceUrl, Constants.ISO_8859_1);
        String signatureCipher = httpMethod + "&" + encodedResourceUrl + "&"
                + timestamp + "&" + nonce + "&" + clientId + "&"
                + clientSecretKey;
        if (additionalParameters != null && !"".equals(additionalParameters))
            signatureCipher = signatureCipher + "&" + additionalParameters;
        System.out.println(signatureCipher);
        MessageDigest messageDigest = MessageDigest
                .getInstance(signatureMethod);
        byte[] signatureBytes = messageDigest
                .digest(signatureCipher.getBytes());

        // encode signature as base 64
        String signature = new String(Base64.encodeBase64(signatureBytes));
        System.out.println("Cipher: " + signatureCipher);

        interswitchAuth.put(Constants.AUTHORIZATION, authorization);
        interswitchAuth.put(Constants.TIMESTAMP, String.valueOf(timestamp));
        interswitchAuth.put(Constants.NONCE, nonce);
        interswitchAuth.put(Constants.SIGNATURE_METHOD, signatureMethod);
        interswitchAuth.put(Constants.SIGNATURE, signature);

        return interswitchAuth;

    }

}
