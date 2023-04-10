/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.document.service;

import com.yil.document.dao.AccessTypeDao;
import com.yil.document.model.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "ACCESS_TYPE")
public class AccessTypeService {

    private final AccessTypeDao accessTypeDao;

    @Autowired
    public AccessTypeService(AccessTypeDao accessTypeDao) {
        this.accessTypeDao = accessTypeDao;
    }

    @Cacheable(key = "#id")
    public Boolean existsById(Integer id) {
        return accessTypeDao.existsById(id);
    }

    @CachePut(key = "#result.id", unless = "{#result.id!=null && #result.id>0}")
    public AccessType save(AccessType accessType) {
        return accessTypeDao.save(accessType);
    }

}