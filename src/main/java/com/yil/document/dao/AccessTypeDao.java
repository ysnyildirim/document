/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.document.dao;

import com.yil.document.model.AccessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTypeDao extends JpaRepository<AccessType, Integer> {
}