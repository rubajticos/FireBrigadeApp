package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface AddEditFireBrigadeContract {

    interface View extends BaseView<Presenter> {

        void showInvalidFirebrigadeError();

        void showUpdateFirebrigadeError();

        void showFirebrigade();

        void setName(String name);

        void setVoivodeship(String voivodeship);

        void setDistrict(String district);

        void setCommunity(String community);

        void setCity(String city);

        void setKsrg(boolean isKsrg);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {
        void saveFireBrigade(String name, String voivodeship, String district, String community, String city, boolean ksrg);

        void populateFirebrigade();

        boolean isDataMissing();
    }
}
