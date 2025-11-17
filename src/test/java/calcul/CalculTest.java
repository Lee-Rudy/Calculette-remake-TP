package test.java.calcul;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import calcul.Calcul;

public class CalculTest {
    @Test
    void testDeltaPositif() {
        Calcul calcul = new Calcul(1, -3, 2);
        double[] res = calcul.calculSeconddegre();

        assertNotNull(res);
        assertEquals(2, res.length);
    }
}
