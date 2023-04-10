/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.document.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(schema = "DOC", name = "DOCUMENT")
public class Document implements Serializable {
    @Id
    @Column(name = "HASH_VALUE", updatable = false)
    private String hashValue;
    @Column(name = "CONTENT", updatable = false)
    private byte[] content;
    @Column(name = "LENGTH", updatable = false)
    private Integer length;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;
    @Column(name = "CREATED_USER_ID", updatable = false)
    private Long createdUserId;

}
