package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequest {

    @NotBlank(message = "名前は必須です")
    @Size(max = 10, message = "名前は10文字以内")
    private String name;

    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレス形式が無効です")
    private String email;

    public UserCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
