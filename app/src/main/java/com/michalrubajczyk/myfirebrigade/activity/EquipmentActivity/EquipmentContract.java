package com.michalrubajczyk.myfirebrigade.activity.EquipmentActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.EquipmentAdapterObj;

import java.util.List;

public interface EquipmentContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showEquipment(List<EquipmentAdapterObj> equipmentList);

        void showAddEquipment();

        void showEditEquipment();

        void showLoadingEquipmentError();

        void showNoEquipment();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadEquipment(boolean forceUpdade);
    }
}
