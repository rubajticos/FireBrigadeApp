package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;

import java.util.List;

public interface FirefighterRequest {

    void getFirefightersByFirebrigadeId(int firebrigadeId, DataListener dataListener);

    void getFirefighterById(int firefighterId, DataListener dataListener);

    void addFirefighterToFireBrigade(Firefighter firefighter, int firebrigadeId, DataListener dataListener);

    void deleteFirefighter(Firefighter firefighter, DataListener dataListener);

    void updateFirefighter(Firefighter firefighter, DataListener dataListener);

    void getFirefighterTrainings(int firefighterId, DataListener dataListener);

    void addFirefighterTrainings(List<FirefighterTraining> trainings, DataListener dataListener);

    void updateFirefighterTrainings(List<FirefighterTraining> trainingList, DataListener dataListener);
}