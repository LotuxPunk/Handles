package com.vandendaelen.handles.exceptions;

public class NoSubsystemException extends Exception {
    @Override
    public String getMessage() {
        return "No Aprioritron subsystem found";
    }
}
