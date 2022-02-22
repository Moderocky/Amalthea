package mx.kenzie.amalthea;

public enum Mode {
    READ_ONLY("r"),
    READ_WRITE("rw");
    
    public final String key;
    
    Mode(String key) {
        this.key = key;
    }
    
}
