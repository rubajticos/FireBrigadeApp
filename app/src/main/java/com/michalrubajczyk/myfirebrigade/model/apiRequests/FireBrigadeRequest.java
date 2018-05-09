package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigade;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeRequest {

    void getFireBrigadeByUsername(String username, DataListener dataListener);

    void getFireBrigade(Integer firebrigadeId, DataListener dataListener);

    void addFireBrigadeToUser(FireBrigade fireBrigade, String username, DataListener dataListener);

    void updateFirebrigade(FireBrigade updateFirebrigade, DataListener dataListener);
}
