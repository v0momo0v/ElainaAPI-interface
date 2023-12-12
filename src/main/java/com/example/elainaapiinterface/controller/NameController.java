package com.example.elainaapiinterface.controller;

import com.example.elainaapiinterface.model.User;
import com.example.elainaapiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name){
        return "GET:"+name;
    }
    @PostMapping("/")
    public String getNameByPost(@RequestParam String name){
        return "POST:"+name;
    }
    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        //String secretKey = request.getHeader("secretKey");
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        if(!accessKey.equals("elaina")){
            throw new RuntimeException("无权限");
        }
        String genSign = SignUtils.genSign(body, "love");
        if(!sign.equals(genSign)){
            throw new RuntimeException("无权限");
        }
        return "POST:"+user.getUsername();
    }
}
