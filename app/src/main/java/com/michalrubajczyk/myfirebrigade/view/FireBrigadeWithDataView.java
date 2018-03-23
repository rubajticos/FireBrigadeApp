package com.michalrubajczyk.myfirebrigade.view;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeWithDataView {

    void showFireBrigadeName(String name);

    void showFireBrigadeVoivodeship(String voivodeship);

    void showFireBrigadeDistrict(String district);

    void showFireBrigadeCommunity(String community);

    void showFireBrigadeCity(String city);

    void showFireBrigadeKSRG(boolean ksrg);

    void showFirefightersCount(int firefighters);

    void showCarsCount(int cars);

}
