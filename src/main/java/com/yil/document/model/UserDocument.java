/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.document.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(schema = "DOC", name = "USER_DOCUMENT")
public class UserDocument implements Serializable {
    @Id
    @SequenceGenerator(name = "USER_DOCUMENT_SEQUENCE_GENERATOR",
            schema = "DOC",
            sequenceName = "SEQ_USER_DOCUMENT_ID")
    @GeneratedValue(generator = "USER_DOCUMENT_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "HASH_VALUE")
    private String hashValue;
    @Column(name = "USER_ID")
    private Long userId;
    @ColumnDefault("0")
    @Column(name = "ACCCESS_TYPE_ID")
    private Integer accessTypeId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TAG")
    private String tag;
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;
    @Column(name = "CREATED_USER_ID", updatable = false)
    private Long createdUserId;
}
