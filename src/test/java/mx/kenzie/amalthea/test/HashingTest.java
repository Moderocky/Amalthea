package mx.kenzie.amalthea.test;

import mx.kenzie.amalthea.util.Hashing;
import org.junit.Test;

public class HashingTest {
    
    @Test
    public void test() {
        final Hashing hashing = new Hashing();
        assert hashing.hash("hello") == 823725152L;
        assert hashing.hash("hello there") == 731081640733957728L;
    }
    
}
