package com.michalrubajczyk.myfirebrigade.model.dto;

public class Training {

    private int idTraining;
    private String name;

    public int getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(int idTraining) {
        this.idTraining = idTraining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Training{" +
                "idTraining=" + idTraining +
                ", name='" + name + '\'' +
                '}';
    }
}
