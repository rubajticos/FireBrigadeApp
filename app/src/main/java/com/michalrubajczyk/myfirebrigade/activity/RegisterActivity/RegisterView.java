package com.michalrubajczyk.myfirebrigade.view;

/**
 * Created by Michal on 18/03/2018.
 */

public interface RegisterView {

    void registerValidations();

    void registerSuccess();

    void passwordNotTheSame();

    void progressDialogShow();

    void progressDialogDismiss();

    void userExistError();

    void badRequestError();

    void timeoutError();
}
