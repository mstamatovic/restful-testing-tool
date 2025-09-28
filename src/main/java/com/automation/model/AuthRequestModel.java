package com.automation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestModel {

    public String username;
    public String password;

    public AuthRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
