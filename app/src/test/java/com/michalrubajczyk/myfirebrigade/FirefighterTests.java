package com.michalrubajczyk.myfirebrigade;

import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import org.junit.Assert;
import org.junit.Test;

public class FirefighterTests {

    @Test
    public void createFirefighterByJson_isNotNull() {
        String json = "{\"idFirefighter\":1,\"name\":\"Michał\",\"lastName\":\"Rubajczyk\",\"birthday\":\"1994-04-14\",\"expiryMedicalTest\":\"2020-05-25\"}";

        Firefighter firefighter = new Firefighter(json);
        Assert.assertNotNull(firefighter);
    }

}
