package com.feng.com.rxjavade.testjava;

import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WHF.Javas on 2017/8/23.
 */

public class ObservableTest {

    UserBuilder builder=new UserBuilder.Build()
            .age(23)
            .build();
    Person person=new Person.Build()
            .build();
    private void method(){

        Observable.just(builder)
                .subscribeOn(Schedulers.io())
                .map(userBuilder -> {
                    if (userBuilder.getAge()>0) {
                        userBuilder.setAge(88);
                    }
                    return userBuilder;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userBuilder -> {
                    Log.d("name",userBuilder.getAge()+"");
                });

        TravelContext context=new TravelContext();
        context.setStrategy(new BikeStrategy());
        context.travel();

        Strategy strategy = StrategyFactory.createStrategy(BikeStrategy.class);
        strategy.travel();
    }


}
