package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeRequest {

    void getFireBrigadeByUsername(String username, DataListener dataListener);

    void getFireBrigade(Integer firebrigadeId, DataListener dataListener);

    void addFireBrigadeToUser(FireBrigadeDTO fireBrigadeDTO, String username, DataListener dataListener);

    void updateFirebrigade(FireBrigadeDTO updateFirebrigade, DataListener dataListener);
}
