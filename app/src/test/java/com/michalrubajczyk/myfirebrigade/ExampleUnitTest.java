package com.michalrubajczyk.myfirebrigade;

import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

import org.junit.Test;

import static org.junit.Assert.*;

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
        FireBrigadeDTO fireBrigadeDTO = new FireBrigadeDTO("", "", "", "", "", false);
        assertTrue(fireBrigadeDTO.isEmpty());
    }

    @Test
    public void nullFireBrigadeShouldReturnTrue() {
        FireBrigadeDTO fireBrigadeDTO = new FireBrigadeDTO(null, null, null, null, null, false);
        assertTrue(fireBrigadeDTO.isEmpty());
    }

}