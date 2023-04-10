/*
 * Copyright (c) 2022. Tüm hakları Yasin Yıldırım'a aittir.
 */
package com.yil.document.config;

public enum ErrorCode {
    DocumentNotFound(1001, "Döküman bulunamadı!"),
    AccessTypeNotFound(1002, "Erişim türü bulunamadı!");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
