package com.vandendaelen.handles.exceptions;

public class NoUpgradeException extends Exception {
    @Override
    public String getMessage() {
        return "No Aprioritron Upgrade found, or the upgrade is not activated";
    }
}
