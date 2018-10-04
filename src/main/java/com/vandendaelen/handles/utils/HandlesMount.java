package com.vandendaelen.handles.utils;

import dan200.computercraft.api.filesystem.IMount;
import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HandlesMount implements IMount {

    public static final String MOUNT_DIRECTORY = Reference.BASE_HANDLES_DIR+"handles_mount";
    public static final String DIRECTORY = "/handles";
    private IPeripheral peripheral;

    public HandlesMount(IPeripheral peripheral) {
        this.peripheral = peripheral;
    }

    @Override
    public boolean exists(@Nonnull String s) throws IOException {
        return new File(MOUNT_DIRECTORY+"/"+s).exists();
    }

    @Override
    public boolean isDirectory(@Nonnull String s) throws IOException {
        return new File(MOUNT_DIRECTORY+"/"+s).isDirectory();
    }

    @Override
    public void list(@Nonnull String s, @Nonnull List<String> list) throws IOException {
        File file = new File(MOUNT_DIRECTORY+"/"+s);
        for (File f : file.listFiles()) {
            String type = peripheral.getType();
            if (f.getName().equals(type) || file.getAbsolutePath().contains(type))
                if (!s.contains("index"))
                    list.add(f.getName());
        }
    }

    @Override
    public long getSize(@Nonnull String s) throws IOException {
        return new File(MOUNT_DIRECTORY+"/"+s).getTotalSpace();
    }

    @Nonnull
    @Override
    public InputStream openForRead(@Nonnull String s) throws IOException {
        return new FileInputStream(new File(MOUNT_DIRECTORY+"/"+s));
    }
}
