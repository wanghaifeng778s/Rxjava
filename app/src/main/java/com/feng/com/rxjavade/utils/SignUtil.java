/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.feng.com.rxjavade.utils;


import android.util.Base64;

import com.feng.com.rxjavade.app.config.Constant;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * 签名工具
 */
public class SignUtil {

    /**
     * 计算签名
     *
     * @param secret               APP密钥
     * @param method               HttpMethod
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @param signHeaderPrefixList 自定义参与签名Header前缀
     * @return 签名后的字符串
     */
    public static String sign(String secret, String method, String path,
                              Map<String, String> headers,
                              Map<String, String> querys,
                              Map<String, String> bodys,
                              List<String> signHeaderPrefixList) {
        try {
            Mac hmacSha256 = Mac.getInstance(Constant.HMAC_SHA256);
            byte[] keyBytes = secret.getBytes(Constant.ENCODING);
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, Constant.HMAC_SHA256));

            return new String(Base64.encode((hmacSha256.doFinal(buildStringToSign(method, path, headers, querys, bodys, signHeaderPrefixList).getBytes(Constant.ENCODING))),Base64.DEFAULT));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建待签名字符串
     *
     * @param method
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @param signHeaderPrefixList
     * @return
     */
    private static String buildStringToSign(String method, String path,
                                            Map<String, String> headers,
                                            Map<String, String> querys,
                                            Map<String, String> bodys,
                                            List<String> signHeaderPrefixList) {
        StringBuilder sb = new StringBuilder();

        sb.append(method.toUpperCase()).append(Constant.LF);
        if (null != headers) {
            if (null != headers.get(Constant.HTTP_HEADER_ACCEPT)) {
                sb.append(headers.get(Constant.HTTP_HEADER_ACCEPT));
            }
            sb.append(Constant.LF);
            if (null != headers.get(Constant.HTTP_HEADER_CONTENT_MD5)) {
                sb.append(headers.get(Constant.HTTP_HEADER_CONTENT_MD5));
            }
            sb.append(Constant.LF);
            if (null != headers.get(Constant.HTTP_HEADER_CONTENT_TYPE)) {
                sb.append(headers.get(Constant.HTTP_HEADER_CONTENT_TYPE));
            }
            sb.append(Constant.LF);
            if (null != headers.get(Constant.HTTP_HEADER_DATE)) {
                sb.append(headers.get(Constant.HTTP_HEADER_DATE));
            }
        }
        sb.append(Constant.LF);
        sb.append(buildHeaders(headers, signHeaderPrefixList));
        sb.append(buildResource(path, querys, bodys));

        return sb.toString();
    }

    /**
     * 构建待签名Path+Query+BODY
     *
     * @param path
     * @param querys
     * @param bodys
     * @return 待签名
     */
    private static String buildResource(String path, Map<String, String> querys, Map<String, String> bodys) {
        StringBuilder sb = new StringBuilder();

        if (!StringUtil.isEmpty(path)) {
            sb.append(path);
        }
        Map<String, String> sortMap = new TreeMap<String, String>();
        if (null != querys) {
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (!StringUtil.isEmpty(query.getKey())) {
                    sortMap.put(query.getKey(), query.getValue());
                }
            }
        }

        if (null != bodys) {
            for (Map.Entry<String, String> body : bodys.entrySet()) {
                if (!StringUtil.isEmpty(body.getKey())) {
                    sortMap.put(body.getKey(), body.getValue());
                }
            }
        }

        StringBuilder sbParam = new StringBuilder();
        for (Map.Entry<String, String> item : sortMap.entrySet()) {
            if (!StringUtil.isEmpty(item.getKey())) {
                if (0 < sbParam.length()) {
                    sbParam.append(Constant.SPE3);
                }
                sbParam.append(item.getKey());
                if (!StringUtil.isEmpty(item.getValue())) {
                    sbParam.append(Constant.SPE4).append(item.getValue());
                }
            }
        }
        if (0 < sbParam.length()) {
            sb.append(Constant.SPE5);
            sb.append(sbParam);
        }

        return sb.toString();
    }

    /**
     * 构建待签名Http头
     *
     * @param headers              请求中所有的Http头
     * @param signHeaderPrefixList 自定义参与签名Header前缀
     * @return 待签名Http头
     */
    private static String buildHeaders(Map<String, String> headers, List<String> signHeaderPrefixList) {
        StringBuilder sb = new StringBuilder();

        if (null != signHeaderPrefixList) {
            signHeaderPrefixList.remove(Constant.X_CA_SIGNATURE);
            signHeaderPrefixList.remove(Constant.HTTP_HEADER_ACCEPT);
            signHeaderPrefixList.remove(Constant.HTTP_HEADER_CONTENT_MD5);
            signHeaderPrefixList.remove(Constant.HTTP_HEADER_CONTENT_TYPE);
            signHeaderPrefixList.remove(Constant.HTTP_HEADER_DATE);
//            signHeaderPrefixList.remove(Constant.X_CA_SIGNATURE_HEADERS);
            Collections.sort(signHeaderPrefixList);
            if (null != headers) {
                Map<String, String> sortMap = new TreeMap<String, String>();
                sortMap.putAll(headers);
                StringBuilder signHeadersStringBuilder = new StringBuilder();
                for (Map.Entry<String, String> header : sortMap.entrySet()) {
                    if (isHeaderToSign(header.getKey(), signHeaderPrefixList)) {
                        sb.append(header.getKey());
                        sb.append(Constant.SPE2);
                        if (!StringUtil.isEmpty(header.getValue())) {
                            sb.append(header.getValue());
                        }
                        sb.append(Constant.LF);
                        if (0 < signHeadersStringBuilder.length()) {
                            signHeadersStringBuilder.append(Constant.SPE1);
                        }
                        signHeadersStringBuilder.append(header.getKey());
                    }
                }
                headers.put(Constant.X_CA_SIGNATURE_HEADERS, signHeadersStringBuilder.toString());
            }
        }

         return sb.toString();
    }

    /**
     * Http头是否参与签名 return
     */
    private static boolean isHeaderToSign(String headerName, List<String> signHeaderPrefixList) {
        if (StringUtil.isEmpty(headerName)) {
            return false;
        }

        if (headerName.startsWith(Constant.CA_HEADER_TO_SIGN_PREFIX_SYSTEM)) {
            return true;
        }

        if (null != signHeaderPrefixList) {
            for (String signHeaderPrefix : signHeaderPrefixList) {
                if (headerName.equalsIgnoreCase(signHeaderPrefix)) {
                    return true;
                }
            }
        }

        return false;
    }
}
