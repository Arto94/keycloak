package com.example.demo.controller;

import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Auth", description = "REST API for Authentication", tags = {"Auth"})
public class AuthenticationController {

    @Value("${authUrl}")
    private String authUrl;

    @Value("${clientId}")
    private String clientId;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody Request request) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost post = new HttpPost(authUrl);
        post.setHeader("content-type",
                "application/x-www-form-urlencoded");
        List<BasicNameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("grant_type", "password"));
        urlParameters.add(new BasicNameValuePair("username", request.getUsername()));
        urlParameters.add(new BasicNameValuePair("password", request.getPassword()));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line1;
            while ((line1 = rd.readLine()) != null) {
                result.append(line1);
            }
            Response token = mapper.readValue(result.toString(), Response.class);
            return ResponseEntity.ok("bearer " + token.getAccessToken());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
