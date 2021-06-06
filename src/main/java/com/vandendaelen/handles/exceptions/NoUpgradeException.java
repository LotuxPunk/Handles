package com.vandendaelen.handles.exceptions;

import net.minecraft.util.text.TranslationTextComponent;

public class NoUpgradeException extends Exception {
    @Override
    public String getMessage() {
        return new TranslationTextComponent("error.handles.no_upgrade").getString();
    }
}
