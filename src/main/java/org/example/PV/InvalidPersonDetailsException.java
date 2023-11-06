package org.example.PV;

import java.lang.Exception;

public class InvalidPersonDetailsException extends Exception {
    public InvalidPersonDetailsException(String err){
        super(err);
    }
}
