package com.yil.document.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDocumentRequest implements Serializable {
    @NotNull
    @Positive
    private Integer accessType;
    @NotBlank
    private String content;
    @Size(max = 1000)
    private String name;
    @Size(max = 100)
    private String mimeType;
    @Size(max = 4000)
    private String description;
    @Size(max = 4000)
    private String tag;

}
