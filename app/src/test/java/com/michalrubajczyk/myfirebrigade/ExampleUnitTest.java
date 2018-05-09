package com.michalrubajczyk.myfirebrigade;

import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigade;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void emptyFireBrigadeShouldReturnTrue() {
        FireBrigade fireBrigade = new FireBrigade("", "", "", "", "", false);
        assertTrue(fireBrigade.isEmpty());
    }

    @Test
    public void nullFireBrigadeShouldReturnTrue() {
        FireBrigade fireBrigade = new FireBrigade(null, null, null, null, null, false);
        assertTrue(fireBrigade.isEmpty());
    }

}