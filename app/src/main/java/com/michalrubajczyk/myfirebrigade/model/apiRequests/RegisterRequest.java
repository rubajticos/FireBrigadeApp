package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.User;

/**
 * Created by Michal on 18/03/2018.
 */

public interface RegisterRequest {

    void registerUser(User user, final DataListener dataListener);

}
