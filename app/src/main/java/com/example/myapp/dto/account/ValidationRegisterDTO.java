package com.example.myapp.dto.account;

public class ValidationRegisterDTO {
    private int status;
    private String title;
    private com.example.myapp.dto.account.RegisterDTO errors;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.example.myapp.dto.account.RegisterDTO getErrors() {
        return errors;
    }

    public void setErrors(com.example.myapp.dto.account.RegisterDTO errors) {
        this.errors = errors;
    }
}