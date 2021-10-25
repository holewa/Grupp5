package com.groupfive.bookmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// this is just a pojo for authentication requests.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String userName;
    private String passWord;
}
