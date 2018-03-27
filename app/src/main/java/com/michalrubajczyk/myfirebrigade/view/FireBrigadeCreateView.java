package com.michalrubajczyk.myfirebrigade.view;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeCreateView {

    void validateFields();

    void registerSuccess();

    void progressDialogShow();

    void progressDialogDismiss();

}
