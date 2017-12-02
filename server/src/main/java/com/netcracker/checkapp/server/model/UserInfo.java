package com.netcracker.checkapp.server.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userInfo")
@Data
public class UserInfo {

    private String login; // login format : +X(XXX)XXXXXXX
    private String pwd; // password format : any (taking from client side)
    private String role;
}
