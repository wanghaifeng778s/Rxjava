package com.feng.com.rxjavade.testjava;

/**
 * Created by WHF.Javas on 2017/8/23.
 */

public class StrategyFactory {

    public static Strategy createStrategy(Class<? extends Strategy> clazz){
        Strategy user=null;
        try {
            user=clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
