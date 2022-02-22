package mx.kenzie.amalthea.impl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomAccessInputStream extends InputStream {
    
    protected final RandomAccessFile file;
    
    public RandomAccessInputStream(RandomAccessFile file) {
        this.file = file;
    }
    
    @Override
    public int read() throws IOException {
        return file.read();
    }
    
    @Override
    public int read(byte @NotNull [] bytes) throws IOException {
        return file.read(bytes);
    }
    
}
