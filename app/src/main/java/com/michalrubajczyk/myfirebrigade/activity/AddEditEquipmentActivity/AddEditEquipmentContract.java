package com.michalrubajczyk.myfirebrigade.activity.AddEditEquipmentActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.CarEquipment;

import java.util.List;

public interface AddEditEquipmentContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void setCarNames(List<String> mCarNames);

        void showInwalidEquipmentError();

        void showServerError();

        void showAddEquipmentError();

        void showUpdateEquipmentError();

        void showEquipments();

        void showNoCars();

        void showCars();

        void setName(String name);

        void setTypes(String type, String subtype);

        void setCar(String carName);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void saveEquipment(String name, String type, String carName);

        void populateEquipment(CarEquipment carEquipment);

        boolean isDataMissing();

    }

}
