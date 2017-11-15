package com.koko.crud.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String userId;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private String email;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
}
