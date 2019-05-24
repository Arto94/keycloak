package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @ApiModelProperty(value = "username", position = 1)
    private String username;
    @ApiModelProperty(value = "password", position = 2)
    private String password;

}
