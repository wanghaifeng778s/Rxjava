package com.feng.com.rxjavade.testjava;

import android.util.Log;

import rx.Observable;

/**
 * Created by WHF.Javas on 2017/8/23.
 */

public class ObservableTest {

    UserBuilder builder=new UserBuilder.Build()
            .age(23)
            .build();
    private void method(){

        Observable.just(builder)
                .map(userBuilder -> {
                    if (userBuilder.getAge()>0) {
                        userBuilder.setAge(88);
                    }
                    return userBuilder;
                })
                .subscribe(userBuilder -> {
                    Log.d("name",userBuilder.getAge()+"");
                });

        TravelContext context=new TravelContext();
        context.setStrategy(new BikeStrategy());
        context.travel();

        Strategy strategy = StrategyFactory.createStrategy(BikeStrategy.class);
    }


}
