package com.feng.com.rxjavade.http;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class Api {
    private static CommonService commonService;
    public static CommonService getComApi(){
        if (commonService == null) {
            synchronized (Api.class){
                if (commonService == null){
                    commonService=HttpService.create(CommonService.class);
                }
            }
        }
        return commonService;
    }
}
