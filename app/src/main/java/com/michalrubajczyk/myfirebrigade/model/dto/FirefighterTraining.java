package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.Date;

public class FirefighterTraining {

    private int idFirefighterTraining;
    private Firefighter firefighter;
    private Training training;
    private Date trainingDate;

    public int getIdFirefighterTraining() {
        return idFirefighterTraining;
    }

    public void setIdFirefighterTraining(int idFirefighterTraining) {
        this.idFirefighterTraining = idFirefighterTraining;
    }

    public Firefighter getFirefighter() {
        return firefighter;
    }

    public void setFirefighter(Firefighter firefighter) {
        this.firefighter = firefighter;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Override
    public String toString() {
        return "FirefighterTraining{" +
                "idFirefighterTraining=" + idFirefighterTraining +
                ", firefighter=" + firefighter.toString() +
                ", training=" + training.toString() +
                ", trainingDate=" + trainingDate +
                '}';
    }
}
