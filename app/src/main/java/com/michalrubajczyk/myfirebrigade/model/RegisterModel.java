package com.michalrubajczyk.myfirebrigade.model;

import com.michalrubajczyk.myfirebrigade.model.dto.UserDTO;

/**
 * Created by Michal on 18/03/2018.
 */

public interface RegisterModel {

    void registerUser(UserDTO user, final DataListener dataListener);

}
