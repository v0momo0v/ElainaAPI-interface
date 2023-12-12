package com.example.elainaapiinterface;

import com.example.elainaapiinterface.client.ApiClient;
import com.example.elainaapiinterface.model.User;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String accessKey="elaina";
        String secretKey="love";
        ApiClient apiClient=new ApiClient(accessKey,secretKey);
        String result1 = apiClient.getNameByGet("GET");
        String result2 = apiClient.getNameByPost("POST");
        User user=new User();
        user.setUsername("User");
        String result3 = apiClient.getUsernameByPost(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
