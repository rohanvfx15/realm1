package com.example.rohan.realm1.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {


    @PrimaryKey
    private String id;
    private String name;
//    @Required
    private int age;  // age never null requird mean
    private SocailAccount socailAccount;

    @Ignore
    private String temData; // not save in database




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SocailAccount getSocailAccount() {
        return socailAccount;
    }

    public void setSocailAccount(SocailAccount socailAccount) {
        this.socailAccount = socailAccount;
    }



}
