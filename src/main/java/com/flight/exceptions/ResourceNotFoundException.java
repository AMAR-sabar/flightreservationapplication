package com.flight.exceptions;


import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fileName;
    Long fieldValue;

    public ResourceNotFoundException(String message, String fileName) {
        super(String.format("%s not found with %s ",message,fileName));
        this.resourceName = resourceName;
        this.fileName = fileName;
    }

    public ResourceNotFoundException(String resourceName, String fileName, Long fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fileName,fieldValue));
        this.resourceName = resourceName;
        this.fileName = fileName;
        this.fieldValue = fieldValue;
    }
}