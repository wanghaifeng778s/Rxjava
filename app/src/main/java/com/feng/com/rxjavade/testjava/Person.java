package com.feng.com.rxjavade.testjava;

/**
 * Created by WHF.Javas on 2017/9/5.
 */

public class Person {
    private int age;
    private String sex;
    private String name;

    private Person(){}
    private Person(Build build){
        this.age=build.age;
        this.sex=build.sex;
        this.name=build.name;
    }

    public static class Build{
        private int age;
        private String sex;
        private String name;

        public Build setAge(int age) {
            this.age = age;
            return this;
        }

        public Build setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Build setName(String name) {
            this.name = name;
            return this;
        }
        public Person build(){
            return new Person(this);
        }
    }
}
