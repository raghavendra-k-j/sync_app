package com.example.syncapp.rest;

import com.example.syncapp.rest.defaults.Response;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class DefaultRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        System.out.println("handleException: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response().setError(e.getMessage(), true, false));
    }


    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class, MissingPathVariableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, ServletRequestBindingException.class, MethodArgumentNotValidException.class, NoHandlerFoundException.class, AsyncRequestTimeoutException.class, ErrorResponseException.class, ConversionNotSupportedException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class, BindException.class})
    public final ResponseEntity<Response> handleUnknownException(Exception e) {
        System.out.println("handleUnknownException: " + e.getMessage());
        HttpStatus httpStatus;
        if (e instanceof HttpRequestMethodNotSupportedException) {
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        } else if (e instanceof MissingPathVariableException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof MissingServletRequestParameterException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof MissingServletRequestPartException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof ServletRequestBindingException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof MethodArgumentNotValidException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof NoHandlerFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (e instanceof AsyncRequestTimeoutException) {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        } else if (e instanceof ErrorResponseException) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (e instanceof ConversionNotSupportedException) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (e instanceof TypeMismatchException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof HttpMessageNotReadableException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e instanceof HttpMessageNotWritableException) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (e instanceof BindException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(new Response().setError(e.getMessage(), true, false));
    }
}
