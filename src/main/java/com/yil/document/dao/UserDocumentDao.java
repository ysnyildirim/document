/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.document.dao;

import com.yil.document.model.UserDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentDao extends JpaRepository<UserDocument, Long> {

    @Query(nativeQuery = true,
            value = " select count(1)\n" +
                    " from DOC.user_document ud\n" +
                    " where ud.hash_value = :hashValue\n" +
                    "   and (\n" +
                    "         (ud.user_id = :userId and ud.access_type_id = 1)\n" +
                    "         or ud.access_type_id = 2\n" +
                    "         or (ud.access_type_id = 3\n" +
                    "         and exists(\n" +
                    "                     select *\n" +
                    "                     from APP_DOCS.GROUP_USER gu\n" +
                    "                     where ud.user_id = gu.user_id\n" +
                    "                       and exists(SELECT 1\n" +
                    "                                  from APP_DOCS.GROUP_USER gu2\n" +
                    "                                  where gu2.GROUP_ID = gu.GROUP_ID\n" +
                    "                                    and user_id = :userId)\n" +
                    "                 )\n" +
                    "             )\n" +
                    "     )")
    Long countAllByHashValueAndUserId(@Param(value = "hashValue") String hashValue, @Param(value = "userId") Long userId);

    Page<UserDocument> findAllByUserId(Pageable pageable, Long userId);

    Page<UserDocument> findAllByUserIdAndHashValue(Pageable pageable, Long userId, String hashValue);

}