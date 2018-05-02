package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.Date;

public class FirefighterTraining {

    private int idFirefighterTraining;
    private Firefighter firefighter;
    private Training training;
    private Date trainingDate;

    public FirefighterTraining() {
        this(-1, null, null, null);
    }

    public FirefighterTraining(Firefighter firefighter, Training training, Date trainingDate) {
        this(-1, firefighter, training, trainingDate);
    }

    public FirefighterTraining(int idFirefighterTraining, Firefighter firefighter, Training training, Date trainingDate) {
        this.idFirefighterTraining = idFirefighterTraining;
        this.firefighter = firefighter;
        this.training = training;
        this.trainingDate = trainingDate;
    }

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
