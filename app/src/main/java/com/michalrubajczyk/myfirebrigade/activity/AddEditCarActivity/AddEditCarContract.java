package com.michalrubajczyk.myfirebrigade.activity.AddEditCarActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface AddEditCarContract {

    interface View extends BaseView<Presenter> {


        void showInwalidCarError();

        void showServerError();

        void showUpdateCarError();

        void showAddCarError();

        void showPopulateCarError();

        void showAddCarSuccess();

        void showUpdateCarSuccess();

        void showCars();

        void setModel(String model);

        void setOperationalNumbers(String operationalNumbers);

        void setPlates(String plates);

        void setType(String type);

        void setWater(int water);

        void setFoam(int foam);

        void setMotorPumpPerformance(int motorPumpPerformance);

        void setNumberOfSeats(int numberOfSeats);

        void showProgressBar();

        void hideProgressBar();

        boolean isActive();


    }

    interface Presenter extends BasePresenter {

        void saveCar(String model, String operationalNumbers, String plates, String type, int water, int foam, int motorPumpPerformance, int numberOfSeats);

        void populateCar();

        boolean isDataMissing();

    }

}
