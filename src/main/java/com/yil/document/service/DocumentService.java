package com.yil.document.service;
/*
 * Copyright (c) 2023. Tüm hakları Yasin Yıldırım'a aittir.
 */

import com.yil.document.dao.DocumentDao;
import com.yil.document.dao.UserDocumentDao;
import com.yil.document.dto.UserDocumentDto;
import com.yil.document.dto.UserDocumentRequest;
import com.yil.document.dto.UserDocumentResponse;
import com.yil.document.exception.AccessTypeNotFoundException;
import com.yil.document.exception.DocumentNotFoundException;
import com.yil.document.model.Document;
import com.yil.document.model.UserDocument;
import com.yil.document.util.Mapper;
import com.yil.document.util.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


@Service
public class DocumentService {

    private final UserDocumentDao userDocumentDao;
    private final DocumentDao documentDao;
    private final AccessTypeService accessTypeService;
    private final Mapper<UserDocument, UserDocumentDto> mapper = new Mapper<>(DocumentService::toDto);
    @Value("${application.hash.type}")
    private String hashType;

    @Autowired
    public DocumentService(UserDocumentDao userDocumentDao, DocumentDao documentDao, AccessTypeService accessTypeService) {
        this.userDocumentDao = userDocumentDao;
        this.documentDao = documentDao;
        this.accessTypeService = accessTypeService;
    }

    private static UserDocumentDto toDto(UserDocument userDocument) {
        UserDocumentDto dto = new UserDocumentDto();
        dto.setUserDocumentId(userDocument.getId());
        dto.setName(userDocument.getName());
        dto.setMimeType(userDocument.getMimeType());
        dto.setTag(userDocument.getTag());
        dto.setDescription(userDocument.getDescription());
        dto.setHashValue(userDocument.getHashValue());
        dto.setCreatedDate(userDocument.getCreatedDate());
        return dto;
    }

    public PageDto<UserDocumentDto> findAllByUserId(Pageable pageable, Long userId) throws DocumentNotFoundException {
        Page<UserDocument> documents = userDocumentDao.findAllByUserId(pageable, userId);
        if (documents.isEmpty())
            throw new DocumentNotFoundException();
        return mapper.map(documents);
    }

    public UserDocumentResponse save(UserDocumentRequest request, Long userId) throws AccessTypeNotFoundException {
        if (request.getAccessType() != null && !accessTypeService.existsById(request.getAccessType()))
            throw new AccessTypeNotFoundException();
        byte[] content = Base64Utils.decodeFromString(request.getContent());
        String hash = encode(content);
        if (!documentDao.existsById(hash)) {
            Document document = new Document();
            document.setContent(content);
            document.setHashValue(hash);
            document.setCreatedDate(new Date());
            document.setCreatedUserId(userId);
            document.setLength(content.length);
            documentDao.save(document);
        }
        UserDocument userDocument = new UserDocument();
        userDocument.setUserId(userId);
        userDocument.setHashValue(hash);
        userDocument.setCreatedDate(new Date());
        userDocument.setName(request.getName());
        userDocument.setAccessTypeId(request.getAccessType() == null ? 1 : request.getAccessType()); //varsayılan private olsun
        userDocument.setMimeType(request.getMimeType());
        userDocument.setTag(request.getTag());
        userDocument.setDescription(request.getDescription());
        userDocument = userDocumentDao.save(userDocument);
        UserDocumentResponse documentResponse = new UserDocumentResponse();
        documentResponse.setHashValue(hash);
        documentResponse.setUserDocumentId(userDocument.getId());
        return documentResponse;
    }

    public String encode(byte[] arr) {
        try {
            MessageDigest digest = MessageDigest.getInstance(hashType);
            byte[] value = digest.digest(arr);
            String encoded = DatatypeConverter.printHexBinary(value);
            return encoded.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getContent(String hashValue, Long userId) throws DocumentNotFoundException {
        hashValue = hashValue.toUpperCase();
        if (userDocumentDao.countAllByHashValueAndUserId(hashValue, userId) < 1) //bu kullanının böyle bir dök yok
            throw new DocumentNotFoundException();
        Document document = documentDao.findById(hashValue).orElse(null);
        if (document == null)
            throw new DocumentNotFoundException();
        return document.getContent();
    }

    public PageDto<UserDocumentDto> findAllByHashValueAndUser(Pageable pageable, String hashValue, Long userId) throws DocumentNotFoundException {
        hashValue = hashValue.toUpperCase();
        Page<UserDocument> documents = userDocumentDao.findAllByUserIdAndHashValue(pageable, userId, hashValue);
        if (documents.isEmpty())
            throw new DocumentNotFoundException();
        return mapper.map(documents);
    }

}