package com.luv2code.payphoneApp.outside;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

   //public static final String BASE_URL = "https://sandbox.interswitchng.com/api/v1/verve/wallet/subscribers/";

    public static final String BASE_URL = "http://172.35.2.5:9080/api/v1/verve/wallet/subscribers/";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String NONCE = "NONCE";
    public static final String SIGNATURE_METHOD = "SIGNATURE_METHOD";
    public static final String SIGNATURE = "SIGNATURE";
    public static final String AUTHORIZATION = "AUTHORIZATION";

    public static final String CLIENT_ID = "IKIAF6C068791F465D2A2AA1A3FE88343B9951BAC9C3";   //IKIAF6C068791F465D2A2AA1A3FE88343B9951BAC9C3
    public static final String CLIENT_SECRET = "FTbMeBD7MtkGBQJw1XoM74NaikuPL13Sxko1zb0DMjI=";  //FTbMeBD7MtkGBQJw1XoM74NaikuPL13Sxko1zb0DMjI=

    //For Prod
 //"QTMOBILE1WALSIS"
     //webpay
    //private static final String CLIENT_ID =	"IKIAB4BF2CFC5621D9F07D0F1830B8A715026E4ADBF1";
    //private static final String CLIENT_SECRET = "/5pY1ezAhYZUtwnxLGWCc2AZlTMsDthJ4CgRgmXhVus=";

    public static final String  SCHEME = "webpay"; //"webpay"; //"EPG";
    public static final String  CHANNEL = "quicktellermobile";
    public static final String  MERCHANTID = "QTMOBILE1ISWPIS"; //"QTMOBILE1ISWPIS";  //"QTMOBILE1EPPIGY";
    public static final String  VERSION = "5.0";

    public static final String AUTHORIZATION_REALM = "InterswitchAuth";
    public  static final String ISO_8859_1 = "ISO-8859-1";

    /*public final  static Map<String,String>  paymentMethodTypeCode = new HashMap<String,String>(){
        {
            put("MCC","MasterCard");
            put("VCC","VisaCard");
            put("VVC","VerveCard");
        }

    };

    public final static List<String> paymentMethodCode = new ArrayList<>(paymentMethodTypeCode.keySet());
    public final static List<String> paymentMethodName = new ArrayList<>(paymentMethodTypeCode.values());

    public final static  Map<String,String> paymentMethodCodes = new HashMap<String, String>(){
        {
            put("CBP","Stanbic Bank");
            put("UBA","UBA Bank");
            put("KBL","Keystone Bank");
            put("ABP","Access Bank");
        }
    };

    public  final  static List<String> paymentMetCodes = new ArrayList<>(paymentMethodCodes.keySet());
    public  final  static List<String> paymentMetNames = new ArrayList<>(paymentMethodCodes.values());*/
}
