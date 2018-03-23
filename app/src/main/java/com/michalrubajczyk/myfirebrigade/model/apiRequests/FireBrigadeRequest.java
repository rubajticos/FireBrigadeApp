package com.michalrubajczyk.myfirebrigade.model.apiRequests;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeRequest {

    void getFireBrigadeByUsername(String username, DataListener dataListener);

    void addFireBrigadeForUser();
}
