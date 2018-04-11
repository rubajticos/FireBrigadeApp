package com.michalrubajczyk.myfirebrigade.activity.LoginActivity;

/**
 * Created by Michal on 20/03/2018.
 */

public interface LoginView {

    void emptyLoginError();

    void emptyPasswordError();

    void signInValidationError();

    void signInSuccess();

    void signInError();

    void signInTimeoutError();

    void progressDialogShow();

    void progressDialogDismiss();

    void badRequestError();

}
