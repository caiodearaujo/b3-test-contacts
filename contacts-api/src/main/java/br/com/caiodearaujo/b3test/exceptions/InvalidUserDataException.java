package br.com.caiodearaujo.b3test.exceptions;

import br.com.caiodearaujo.b3test.dto.UserDTO;

public class InvalidUserDataException extends Exception {

    public InvalidUserDataException(String message, UserDTO userDTO) {
        super(message);
    }
}
