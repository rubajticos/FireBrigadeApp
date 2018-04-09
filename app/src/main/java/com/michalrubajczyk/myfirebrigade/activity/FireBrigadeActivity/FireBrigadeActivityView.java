package com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity;

/**
 * Created by Michal on 23/03/2018.
 */

public interface FireBrigadeActivityView {

    void setFireBrigadeFragment();

    void setEmptyFragment();

    void setFireBrigadeCreateFragment();

    void callCreatingFailure();

    void callCreatingSuccess();

    void callValidationSuccess();

    void callValidationFailure();

    void showCreatingLoading();

    void dismissCreatingLoading();

    void showFireBrigadeToEdit();

    void showFireBrigade(String fireBrigade);
}
