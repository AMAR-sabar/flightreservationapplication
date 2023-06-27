package com.flight.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException{

    String resourceName;
    String fileName;

    public SQLIntegrityConstraintViolationException(String resourceName, String fileName) {
        super(String.format("%s is Already %s : %s",resourceName,fileName));
        this.resourceName = resourceName;
        this.fileName = fileName;
    }
}
