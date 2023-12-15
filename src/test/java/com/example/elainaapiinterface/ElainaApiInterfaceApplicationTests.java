package com.example.elainaapiinterface;

import com.example.elainaapisdk.client.ApiClient;
import com.example.elainaapisdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElainaApiInterfaceApplicationTests {
    @Autowired
    private ApiClient apiClient;

    @Test
    void contextLoads() {
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
