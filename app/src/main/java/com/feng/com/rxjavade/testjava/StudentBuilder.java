package com.feng.com.rxjavade.testjava;

/**
 * Created by WHF.Javas on 2017/8/23.
 */

public class StudentBuilder {
    private String name;
    private String age;
    private String sex;
    private String clazz;

    private StudentBuilder (Build build){
        this.age=build.age;
        this.clazz=build.clazz;
        this.sex=build.sex;
        this.name=build.name;
    }

    public static class Build{
        private String name;
        private String age;
        private String sex;
        private String clazz;

        public Build name(String name) {
            this.name = name;
            return this;
        }

        public Build age(String age) {
            this.age = age;
            return this;
        }

        public Build sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Build clazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public  StudentBuilder builder(){
            return new StudentBuilder(this);
        }
    }
}
