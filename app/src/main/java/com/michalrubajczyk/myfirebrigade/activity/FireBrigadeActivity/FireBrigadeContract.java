package com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.FireBrigadeDTO;

public interface FireBrigadeContract {

    interface View extends BaseView<Presenter> {

        void showLoadingSpinner(boolean active);

        void hideLoadingSpinner(boolean active);

        void showFireBrigade(FireBrigadeDTO fireBrigade);

        void showAddFireBrigade();

        void showEditFireBrigade();

        void showLoadingFireBrigadeError();

        void showNoFireBrigade();

        void showLogin();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadFireBrigade(boolean forceUpdate);

        void addFireBrigade();

        void logOut();

    }

}
