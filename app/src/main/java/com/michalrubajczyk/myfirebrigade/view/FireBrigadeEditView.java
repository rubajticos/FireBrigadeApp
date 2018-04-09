package com.michalrubajczyk.myfirebrigade.view;

public interface FireBrigadeEditView {

    void showUpdateProgressBar();

    void dismissUpdateProgressBar();

    void onSuccess();

    void onFailure();

}
