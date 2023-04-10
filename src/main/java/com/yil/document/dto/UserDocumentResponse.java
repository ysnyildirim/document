package com.yil.document.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDocumentResponse {
    private Long userDocumentId;
    private String hashValue;

    public Long getUserDocumentId() {
        return userDocumentId;
    }

    public void setUserDocumentId(Long userDocumentId) {
        this.userDocumentId = userDocumentId;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }
}
