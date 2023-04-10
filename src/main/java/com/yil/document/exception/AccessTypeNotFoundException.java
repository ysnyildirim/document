package com.yil.document.exception;

import com.yil.document.config.ApiException;
import com.yil.document.config.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ApiException(code = ErrorCode.AccessTypeNotFound)
public class AccessTypeNotFoundException extends Exception {
}
