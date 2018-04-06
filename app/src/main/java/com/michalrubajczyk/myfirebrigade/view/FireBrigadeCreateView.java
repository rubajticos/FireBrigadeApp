package com.michalrubajczyk.myfirebrigade.view;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeCreateView {

    void validationSuccess();

    void validationFailure();

    void creatingSuccess();

    void creatingFailure();

    void progressDialogShow();

    void progressDialogDismiss();

}
