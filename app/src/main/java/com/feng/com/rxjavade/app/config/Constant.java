package com.feng.com.rxjavade.app.config;

/**
 * Created by WHF.Javas on 2017/9/25.
 */

public class Constant {
    /*
    新API参数
     */

    //签名算法HmacSha256
    public static final String HMAC_SHA256 = "HmacSHA256";
    //编码UTF-8
    public static final String ENCODING = "UTF-8";
    //UserAgent
    public static final String USER_AGENT = "demo/aliyun/java";
    //换行符
    public static final String LF = "\n";
    //串联符
    public static final String SPE1 = ",";
    //示意符
    public static final String SPE2 = ":";
    //连接符
    public static final String SPE3 = "&";
    //赋值符
    public static final String SPE4 = "=";
    //问号符
    public static final String SPE5 = "?";
    //默认请求超时时间,单位毫秒
    public static final int DEFAULT_TIMEOUT = 1000;
    //参与签名的系统Header前缀,只有指定前缀的Header才会参与到签名中
    public static final String CA_HEADER_TO_SIGN_PREFIX_SYSTEM = "X-Ca-";

    //请求Header Accept
    public static final String HTTP_HEADER_ACCEPT = "Accept";
    //请求Body内容MD5 Header
    public static final String HTTP_HEADER_CONTENT_MD5 = "Content-MD5";
    //请求Header Content-Type
    public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    //请求Header UserAgent
    public static final String HTTP_HEADER_USER_AGENT = "User-Agent";
    //请求Header Date
    public static final String HTTP_HEADER_DATE = "Date";
    //签名Header
    public static final String X_CA_SIGNATURE = "X-Ca-Signature";
    //所有参与签名的Header
    public static final String X_CA_SIGNATURE_HEADERS = "X-Ca-Signature-Headers";
    //请求时间戳
    public static final String X_CA_TIMESTAMP = "X-Ca-Timestamp";
    //请求放重放Nonce,15分钟内保持唯一,建议使用UUID
    public static final String X_CA_NONCE = "X-Ca-Nonce";
    //APP KEY
    public static final String X_CA_KEY = "X-Ca-Key";

}
