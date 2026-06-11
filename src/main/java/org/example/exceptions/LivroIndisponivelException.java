package org.example.exceptions;

public class LivroIndisponivelException extends RuntimeException {

    public LivroIndisponivelException(String message) {
        super(message);
    }
}
