package br.com.caiodearaujo.b3test.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("No one user found with id requested.");
    }
}
