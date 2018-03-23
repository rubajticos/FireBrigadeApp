package com.michalrubajczyk.myfirebrigade.view;

import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeMainView {

    void setFragmentWithFirebrigade(FireBrigadeDTO fireBrigadeDTO);

    void setFragmentNoFirebrigade();
}
