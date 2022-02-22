package mx.kenzie.amalthea;

import java.io.IOException;
import java.util.Map;

public abstract class MemorySection implements Map<String, Object> {
    
    public abstract Object retrieve(String key) throws IOException;
    
    public abstract <Type> Type retrieve(String key, Class<Type> type) throws IOException;
    
    public abstract <Type> void store(String key, Object object, Class<Type> type) throws IOException;
    
}
