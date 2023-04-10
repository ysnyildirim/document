/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.document.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(schema = "DOC", name = "ACCESS_TYPE")
public class AccessType implements Serializable {

    @Id
    @SequenceGenerator(name = "ACCESS_TYPE_SEQUENCE_GENERATOR",
            schema = "DOC",
            sequenceName = "SEQ_ACCESS_TYPE_ID")
    @GeneratedValue(generator = "ACCESS_TYPE_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

}
