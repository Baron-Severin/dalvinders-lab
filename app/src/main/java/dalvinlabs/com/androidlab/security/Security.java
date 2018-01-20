package dalvinlabs.com.androidlab.security;


import javax.crypto.Mac;

public class Security {

    static void generateHMAC() throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        System.out.println();
    }
}
