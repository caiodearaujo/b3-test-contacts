package br.com.caiodearaujo.b3test.exceptions;

import br.com.caiodearaujo.b3test.entities.User;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(User user) {
        super(String.format("Already exists a contact with email %s with same companyId %d",
                user.getEmail(), user.getCompanyId()));
    }

}
