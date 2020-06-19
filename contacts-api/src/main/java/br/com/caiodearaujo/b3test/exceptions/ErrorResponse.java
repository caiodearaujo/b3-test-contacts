package br.com.caiodearaujo.b3test.exceptions;

import lombok.Value;

@Value
public class ErrorResponse {

    String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse from(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

}
