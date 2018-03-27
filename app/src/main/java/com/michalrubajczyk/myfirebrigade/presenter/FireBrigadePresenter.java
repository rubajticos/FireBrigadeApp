package com.michalrubajczyk.myfirebrigade.presenter;

import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadePresenter {

    void loadFireBrigadeByUsername();

    void createFireBrigadeToUser(String name, String voivodeship, String district, String community, String city, boolean ksrg);
}
