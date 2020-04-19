package com.vandendaelen.handles.exceptions;

public class NotATardisException extends Exception {
    @Override
    public String getMessage() {
        return "This is not a TARDIS dimension";
    }
}
