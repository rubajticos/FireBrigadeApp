package com.michalrubajczyk.myfirebrigade.dto;

import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirefighterTrainingTest {

    @Test
    public void emptyFirefighterTrainingReturnFalse() {
        FirefighterTraining ff = new FirefighterTraining();

        assertFalse(ff.isValid());
    }

    @Test
    public void filledTrainingWithoutFirefighterShouldReturnTrue() {
        Training training = new Training();
        training.setIdTraining(1);
        training.setName("TestTraining");

        FirefighterTraining ff = new FirefighterTraining();

        ff.setTrainingDate(new Date());
        ff.setTraining(training);

        assertTrue(ff.isValid());
    }

}
