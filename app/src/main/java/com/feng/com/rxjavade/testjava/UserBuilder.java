package com.feng.com.rxjavade.testjava;

/**
 * Created by WHF.Javas on 2017/8/23.
 */

public class UserBuilder {

    private String name;
    private String clazz;
    private int age;
    private String sex;
    private String height;
    private String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    private UserBuilder(Build build){
        this.age=build.age;
        this.name=build.name;
        this.clazz=build.clazz;
        this.sex=build.sex;
        this.height=build.height;
        this.weight=build.weight;
    }

   public  static class Build{
        private String name;
        private String clazz;
        private int age;
        private String sex;
        private String height;
        private String weight;

        public Build name(String name) {
            this.name = name;
            return this;
        }

        public Build clazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public Build age(int age) {
            this.age = age;
            return this;
        }

        public Build sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Build height(String height) {
            this.height = height;
            return this;
        }

        public Build weight(String weight) {
            this.weight = weight;
            return this;
        }
        public UserBuilder build(){
            return new UserBuilder(this);
        }
    }
}
