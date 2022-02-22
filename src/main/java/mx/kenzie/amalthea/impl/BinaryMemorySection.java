package mx.kenzie.amalthea.impl;

import mx.kenzie.amalthea.MemorySection;
import mx.kenzie.amalthea.Mode;
import mx.kenzie.amalthea.error.DataException;
import mx.kenzie.amalthea.util.Hashing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class BinaryMemorySection extends MemorySection {
    
    protected final RandomAccessFile file;
    protected Hashing hashing;
    protected long[] keys;
    protected long[] locations;
    
    public BinaryMemorySection(File file, Mode mode) throws FileNotFoundException {
        this(new RandomAccessFile(file, mode.key), new Hashing());
    }
    
    protected BinaryMemorySection(RandomAccessFile file, Hashing hashing) {
        this.hashing = hashing;
        this.file = file;
    }
    
    public BinaryMemorySection(File file) throws FileNotFoundException {
        this(new RandomAccessFile(file, Mode.READ_WRITE.key), new Hashing());
    }
    
    @Override
    public int size() {
        return keys.length;
    }
    
    @Override
    public boolean isEmpty() {
        return keys.length == 0;
    }
    
    @Override
    public boolean containsKey(Object key) {
        return false;
    }
    
    @Override
    public boolean containsValue(Object value) {
        return false;
    }
    
    @Override
    public Object get(Object key) {
        if (key == null) return null;
        try {
            return this.retrieve(key + "");
        } catch (IOException e) {
            throw new DataException(e);
        }
    }
    
    @Override
    public Object retrieve(String key) throws IOException {
        final long hash = this.hashing.hash(key);
        final int index = Arrays.binarySearch(keys, hash);
        if (index == -1) return null;
        final long location = locations[index];
        this.file.seek(location);
        this.file.getChannel();
        try (final ObjectInputStream stream = new ObjectInputStream(new RandomAccessInputStream(file))) {
            return stream.readObject();
        } catch (ClassNotFoundException e) {
            throw new DataException(e);
        }
    }
    
    @Override
    public <Type> Type retrieve(String key, Class<Type> type) throws IOException {
        return null;
    }
    
    @Override
    public <Type> void store(String key, Object object, Class<Type> type) throws IOException {
    
    }
    
    @Nullable
    @Override
    public Object put(String key, Object value) {
        if (key == null) return null;
        try {
            this.store(key + "", value, value.getClass());
        } catch (IOException e) {
            throw new DataException(e);
        }
        return null;
    }
    
    @Override
    public Object remove(Object key) {
        return null; // todo
    }
    
    @Override
    public void putAll(@NotNull Map<? extends String, ?> map) {
        for (final Entry<? extends String, ?> entry : map.entrySet()) {
            try {
                this.store(entry.getKey(), entry.getValue(), entry.getValue().getClass());
            } catch (IOException e) {
                throw new DataException(e);
            }
        }
    }
    
    @Override
    public void clear() {
        try {
            file.seek(0);
            file.setLength(0);
        } catch (IOException e) {
            throw new DataException(e);
        }
    }
    
    @NotNull
    @Override
    public Set<String> keySet() {
        throw new DataException("The keys of a binary section cannot be retrieved.");
    }
    
    @NotNull
    @Override
    public Collection<Object> values() {
        throw new DataException("The set of values of a binary section cannot be retrieved.");
    }
    
    @NotNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        throw new DataException("The keys of a binary section cannot be retrieved.");
    }
    
    
}
