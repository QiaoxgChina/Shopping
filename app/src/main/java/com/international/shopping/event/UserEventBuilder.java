package com.international.shopping.event;

public class UserEventBuilder {

    private boolean isStudent = true;

    private boolean isYourClassmate = false;

    private boolean isBeijingPerson = true;

    private boolean isCanFly = false;

    private boolean isJava = false;

    private static volatile UserEventBuilder defaultInstance = null;

    private UserEventBuilder(){}

    public static UserEventBuilder getInstance(){
        if(defaultInstance == null){
            synchronized (UserEventBuilder.class){
                if(defaultInstance == null){
                    defaultInstance = new UserEventBuilder();
                }
            }
        }
        return defaultInstance;
    }

    public UserEventBuilder isStudent(boolean isStudent){
        this.isStudent = isStudent;
        return this;
    }

    public UserEventBuilder isYourClassmate(boolean isYourClassmate){
        this.isYourClassmate = isYourClassmate;
        return this;
    }

    public UserEventBuilder isBeijingPerson(boolean isBeijingPerson){
        this.isBeijingPerson = isBeijingPerson;
        return this;
    }

    public UserEventBuilder isCanFly(boolean isCanFly){
        this.isCanFly= isCanFly;
        return this;
    }

    public UserEventBuilder isJava(boolean isJava){
        this.isJava = isJava;
        return this;
    }
}
