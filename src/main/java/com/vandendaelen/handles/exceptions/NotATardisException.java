package com.vandendaelen.handles.exceptions;

import net.minecraft.util.text.TranslationTextComponent;

public class NotATardisException extends Exception {
    @Override
    public String getMessage() {
        return new TranslationTextComponent("error.handles.non_tardis_dimension").getString();
    }
}
