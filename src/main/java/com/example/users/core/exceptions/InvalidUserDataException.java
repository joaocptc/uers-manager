package com.example.users.core.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidUserDataException extends RuntimeException {

    private static final long serialVersionUID = 7157324356289054056L;

    public InvalidUserDataException(final String message) {
        super(message);
    }
}
