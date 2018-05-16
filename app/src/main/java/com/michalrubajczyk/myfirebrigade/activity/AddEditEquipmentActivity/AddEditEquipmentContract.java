package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

import java.util.List;

public interface AddEditEquipmentContract {

    interface View extends BaseView<Presenter> {

        void setCarNames(List<String> mCarNames);

        void showInwalidEquipmentError();

        void showServerError();

        void showAddEquipmentError();

        void showUpdateEquipmentError();

        void showEquipments();

        void showNoCars();

        void setName(String name);

        void setType(String type);

        void setCar(String carName);

        boolean isActive();


    }

    interface Presenter extends BasePresenter {

        void saveEquipment(String name, String type, String carName);

        void populateEquipment();

        boolean isDataMissing();

    }

}
