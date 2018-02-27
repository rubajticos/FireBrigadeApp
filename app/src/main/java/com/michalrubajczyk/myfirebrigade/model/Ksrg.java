package com.michalrubajczyk.myfirebrigade.model;

/**
 * Created by Michal on 27/02/2018.
 */

public class Ksrg {

    private int id;
    private String name;

    public Ksrg(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ksrg) {
            Ksrg k = (Ksrg) o;
            if (k.getName().equals(name) && k.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
