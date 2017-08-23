package com.feng.com.rxjavade.testjava;

/**
 * Created by WHF.Javas on 2017/8/23.
 */

public class TravelContext {
    Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void travel(){
        if (strategy != null) {
            strategy.travel();
        }
    }
}
