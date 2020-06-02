package com.luv2code.payphoneApp.service;

import com.google.gson.Gson;
import com.interswitchng.payment.model.SecureExtractedCardDetail;
import com.interswitchng.payment.model.SecuredRequest;
import com.interswitchng.payment.util.CryptoUtil;
import com.luv2code.payphoneApp.exceptions.PayphoneException;
import com.luv2code.payphoneApp.exceptions.PayphoneIdException;
import com.luv2code.payphoneApp.model.Paymentmethods;
import com.luv2code.payphoneApp.model.PayphoneRequest;
import com.luv2code.payphoneApp.model.PayphoneResponse;
import com.luv2code.payphoneApp.model.User;
import com.luv2code.payphoneApp.outside.Constants;
import com.luv2code.payphoneApp.outside.InterswitchAuth;
import com.luv2code.payphoneApp.repositories.UserRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

@Service
public class PayphoneService {

   // private UserRepository userRepository;

    public String CreatePaymentMethod(PayphoneRequest payphoneRequest) throws IOException, NoSuchAlgorithmException {


        String scheme = Constants.SCHEME;
        String channel = Constants.CHANNEL;
        String merchantid = Constants.MERCHANTID;
        String version = Constants.VERSION;

        String selAccType = payphoneRequest.getAccoutType();
        String accountNo = payphoneRequest.getAccountNo();
        String firstName = payphoneRequest.getFirstName();
        String lastName = payphoneRequest.getLastName();
        String address = "-";
        String otherNames = "-";
        String email = payphoneRequest.getEmail();

        String transactionType = PayphoneRequest.TRANSACTION_TYPE.createpaymentmethod.name();
        String ttid = payphoneRequest.getTtid();
        String paymentMethodCode = payphoneRequest.getPaymentMethodCode();
        String cardName = payphoneRequest.getCardName();
        String paymentMethodTypeCode = payphoneRequest.getPaymentMethodTypeCode();
        String msisdn = payphoneRequest.getPhoneNumber();

        SecureExtractedCardDetail cardData = new SecureExtractedCardDetail();

        cardData.setCardPan(payphoneRequest.getCardPan());
        cardData.setCvv2(payphoneRequest.getCvv());
        cardData.setExpiryDate(payphoneRequest.getExpiryYear() + payphoneRequest.getExpiryMonth());
        cardData.setExpiryMonth(payphoneRequest.getExpiryMonth());
        cardData.setExpiryYear(payphoneRequest.getExpiryYear());
        cardData.setPin(payphoneRequest.getCardPin());

        System.out.println("Card Details  " + cardData);

        SecuredRequest securedRequest = new SecuredRequest();

        securedRequest.setPan(cardData.getCardPan());
        securedRequest.setCardName(cardName);
        securedRequest.setExpiryDate(cardData.getExpiryDate());
        securedRequest.setMobileNumber(msisdn);
        securedRequest.setTtid(String.valueOf(new SecureRandom().nextInt(Integer.MAX_VALUE)));

        String publicKeyExponent = "010001";
        String publicKeyModulus = "009c7b3ba621a26c4b02f48cfc07ef6ee0aed8e12b4bd11c5cc0abf80d5206be69e1891e60fc88e2d565e2fabe4d0cf630e318a6c721c3ded718d0c530cdf050387ad0a30a336899bbda877d0ec7c7c3ffe693988bfae0ffbab71b25468c7814924f022cb5fda36e0d2c30a7161fa1c6fb5fbd7d05adbef7e68d48f8b6c5f511827c4b1c5ed15b6f20555affc4d0857ef7ab2b5c18ba22bea5d3a79bd1834badb5878d8c7a4b19da20c1f62340b1f7fbf01d2f2e97c9714a9df376ac0ea58072b2b77aeb7872b54a89667519de44d0fc73540beeaec4cb778a45eebfbefe2d817a8a8319b2bc6d9fa714f5289ec7c0dbc43496d71cf2a642cb679b0fc4072fd2cf";


        CryptoUtil cryptoUtil = new CryptoUtil();
        byte[] pinKey = cryptoUtil.generateKey();

        String Secure = cryptoUtil.getSecureVersion9(publicKeyModulus, publicKeyExponent, pinKey, pinKey, securedRequest);


        System.out.println("Secure " + Secure);


        String pinData = cryptoUtil.getPINBlock(cardData.getPin(), cardData.getCvv2(), cardData.getExpiryDate(), pinKey);
        System.out.println("pinData " + pinData);

        JSONObject json = new JSONObject();

        json.put("scheme", scheme);
        json.put("channel", channel);
        json.put("merchantid", merchantid);
        json.put("version", version);
        json.put("transactionType", transactionType);
        json.put("pinData", pinData);
        json.put("newPinData", pinData);
        json.put("verifyNewPinData", pinData);
        json.put("secure", Secure);
        json.put("cardName", cardName);
        json.put("mobileNumber", msisdn);
        json.put("ttid", ttid);
        json.put("paymentMethodCode", paymentMethodCode);
        json.put("paymentMethodTypeCode", paymentMethodTypeCode);
        json.put("selAccType", selAccType);
        json.put("subscriberFirstName", firstName);
        json.put("subscriberLastName", lastName);
        json.put("subscriberOtherNames", otherNames);
        json.put("subscriberAddress", address);
        json.put("subscriberEmail", email);
        json.put("accountNo", accountNo);

        StringWriter out = new StringWriter();
        json.write(out);

        String jsonText = out.toString();
        System.out.println(jsonText);

        String httpMethod = "POST";

        String resourceUrl = Constants.BASE_URL + msisdn + "/paymentmethods";
        System.out.println("This is the Url for Create a payment method " + resourceUrl);
        String clientId = Constants.CLIENT_ID;
        String clientSecretKey = Constants.CLIENT_SECRET;
        String signatureMethod = "SHA-256";

        String additionalParameters = Secure + "&" + pinData + "&" + paymentMethodTypeCode;

        HashMap<String, String> interswitchAuth = InterswitchAuth.generateInterswitchAuth(httpMethod, resourceUrl, clientId,
                clientSecretKey, additionalParameters, signatureMethod);

        try {
            HttpHost httpHostProxy = new HttpHost("172.16.10.20", 8080);

            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHostProxy);

            HttpPost post = new HttpPost(resourceUrl);


            post.setHeader("Authorization", interswitchAuth.get(Constants.AUTHORIZATION));
            post.setHeader("Timestamp", interswitchAuth.get(Constants.TIMESTAMP));
            post.setHeader("Nonce", interswitchAuth.get(Constants.NONCE));
            post.setHeader("Signature", interswitchAuth.get(Constants.SIGNATURE));
            post.setHeader("SignatureMethod", interswitchAuth.get(Constants.SIGNATURE_METHOD));

            StringEntity entity = new StringEntity(jsonText);

            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            if (response == null) {
                throw new PayphoneException("Server Error", "unable to Create Card", "VerveMpin Pod is not Reachable",
                        "Please reachout to Implementation Enginners for help !");
            }

            System.out.println("Response " + response);

            int responseCode = response.getStatusLine().getStatusCode();

            System.out.println(responseCode);

            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();

                StringBuffer stringBuffer = new StringBuffer();

                int c;
                while ((c = inputStream.read()) != -1) {
                    stringBuffer.append((char) c);
                }
                System.out.println(stringBuffer);
            }

        } catch (Exception e) {
            throw new PayphoneException("Server Error", "unable to Create Card", "VerveMpin Pod is not Reachable",
                    "Please reachout to Implementation Enginners for help !");



        /*   String result = EntityUtils.toString(httpEntity);

          // JSONObject jsonObject =
         Gson gson = new Gson();

         PayphoneRequest request = gson.fromJson(result,PayphoneRequest.class);
         */


        }
        return "Your Card has been created ! ";

    }


    public PayphoneResponse getPaymentMethod(String phoneNumber) throws IOException, NoSuchAlgorithmException {

        PayphoneResponse payphoneResponse;

        //User user = userRepository.findByUsername(username);


        String phoneNumber1 = phoneNumber;
        String scheme = Constants.SCHEME;
        String merchantid = Constants.MERCHANTID;
        String channel = Constants.CHANNEL;
        String version = Constants.VERSION;
        String transactionType = "getpaymentmethods";
        String httpMethod = "GET";
        String resourceUrl = Constants.BASE_URL + phoneNumber1 + "/paymentmethods.json?scheme=" + scheme + "&channel=" + channel + "&merchantid=" + merchantid + "&version=" + version + "&transactionType=" + transactionType;
       System.out.println("REsourceUrl  " + resourceUrl);
        String clientId = Constants.CLIENT_ID;
        String clientSecretKey = Constants.CLIENT_SECRET;
        String signatureMethod = "SHA-256";
        HashMap interswitchAuth = InterswitchAuth.generateInterswitchAuth(httpMethod, resourceUrl, clientId, clientSecretKey, "", signatureMethod);

        try {
            //payphoneResponse.setUser(user);
            URL obj = new URL(resourceUrl);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.10.20", 8080));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
            con.setRequestMethod("GET");

            con.setRequestProperty("Authorization", (String) interswitchAuth.get(Constants.AUTHORIZATION));
            con.setRequestProperty("Timestamp", (String) interswitchAuth.get(Constants.TIMESTAMP));
            con.setRequestProperty("Nonce", (String) interswitchAuth.get(Constants.NONCE));
            con.setRequestProperty("Signature", (String) interswitchAuth.get(Constants.SIGNATURE));
            con.setRequestProperty("SignatureMethod", (String) interswitchAuth.get(Constants.SIGNATURE_METHOD));
            con.setRequestProperty("Proxy-Connection","Keep-Alive");

            int responseCode = con.getResponseCode();

            String m = con.getResponseMessage();
            System.out.println("Response Code : " + responseCode + " " + m);
            InputStream inputStream = con.getInputStream();

            StringBuffer response = new StringBuffer();
            System.out.println("RESPONSE  " + response);

            int c;
            while ((c = inputStream.read()) != -1) {
                response.append((char) c);
            }
          /* String   str = response.toString();
            System.out.println("This is the card  " + str);*/
            Gson gson = new Gson();

            payphoneResponse = gson.fromJson(response.toString(), PayphoneResponse.class);

        }catch (Exception e){
             throw  new PayphoneIdException("Unable to retrieve your card, please check the " + phoneNumber +  " passed");
        }finally {

             new PayphoneException("Server Error", "unable to Create Card", "VerveMpin Pod is not Reachable",
                    "Please reachout to Implementation Enginners for help !");
        }


        return payphoneResponse;

    }

  }