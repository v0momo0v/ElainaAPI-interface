package com.example.elainaapiinterface.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.elainaapiinterface.model.User;
import com.example.elainaapiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    private String accessKey;
    private String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private Map<String,String> getHeaderMap(String body){
        Map<String,String> map=new HashMap<>();
        map.put("accessKey",accessKey);
        //map.put("secretKey",secretKey);
        map.put("body",body);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        map.put("sign", SignUtils.genSign(body,secretKey));
        return map;
    }
    public String getNameByGet(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.get("http://localhost:8102/api/name/", paramMap);
        System.out.println(result);
        return result;
    }
    public String getNameByPost(@RequestParam String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.post("http://localhost:8102/api/name/", paramMap);
        System.out.println(result);
        return result;
    }
    public String getUsernameByPost(@RequestBody User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post("http://localhost:8102/api/name/user/")
                .addHeaders(getHeaderMap(json)).body(json).execute();
        System.out.println(response.getStatus());
        String result = response.body();
        System.out.println(result);
        return result;
    }
}
