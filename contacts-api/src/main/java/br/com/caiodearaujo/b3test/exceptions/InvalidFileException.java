package br.com.caiodearaujo.b3test.exceptions;

import lombok.Getter;

public class InvalidFileException extends Exception {

    @Getter
    private String fileName;
    @Getter
    private Long row;

    public InvalidFileException(String message, String fileName, Long row) {
        super(message);
        this.fileName = fileName;
        this.row = row;
    }

}
