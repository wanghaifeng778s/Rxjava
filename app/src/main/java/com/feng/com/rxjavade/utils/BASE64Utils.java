package com.feng.com.rxjavade.utils;

import java.io.IOException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


/**
 * Created by susyimes on 2017/7/19.
 */

public class BASE64Utils {
    public static String BASE64ToStr(String base64)  {

        BASE64Decoder base64Decoder = new BASE64Decoder();
        String result = null;
        try {
            result = new String(base64Decoder.decodeBuffer(base64));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String StrToBASE64(String str) {
        BASE64Encoder base64Encoder = new BASE64Encoder();

        String result = base64Encoder.encode(str.getBytes());
        return result;
    }
    public static String byteToBASE64(byte[] str) {
        BASE64Encoder base64Encoder = new BASE64Encoder();

        String result = base64Encoder.encode(str);
        return result;
    }

}
