package com.testtaskt1.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class StringModel {
    @Schema(description = "Строка входных данных", example = "aaaaabcccc")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
