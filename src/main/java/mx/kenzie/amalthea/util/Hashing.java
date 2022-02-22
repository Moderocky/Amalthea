package mx.kenzie.amalthea.util;

public class Hashing {
    
    private static final byte HI_BYTE_SHIFT = 8, LO_BYTE_SHIFT = 0;
    
    public long hash(String value) {
        final byte[] bytes = value.getBytes();
        long hash = 0;
        int length = bytes.length >> 1;
        for (int i = 0; i < length; i++) {
            hash = 31 * hash + this.charAt(bytes, i);
        }
        for (int i = length; i > 0; i--) {
            hash = 31 * hash + this.charAt(bytes, i - 1);
        }
        return hash;
    }
    
    short charAt(final byte[] bytes, int index) {
        final int i = index << 1;
        return (short) (((bytes[i] & 0xff) << HI_BYTE_SHIFT) | ((bytes[i + 1] & 0xff) << LO_BYTE_SHIFT));
    }
    
    public long hash(byte[] bytes) {
        long hash = 0;
        for (final byte b : bytes) hash = 31 * hash + (b & 0xff);
        return hash;
    }
    
}
