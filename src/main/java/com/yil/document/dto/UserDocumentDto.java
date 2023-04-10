package com.yil.document.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDocumentDto implements Serializable {

    private Long userDocumentId;
    private String hashValue;
    private String name;
    private String mimeType;
    private Date createdDate;
    private String description;
    private String tag;

}
