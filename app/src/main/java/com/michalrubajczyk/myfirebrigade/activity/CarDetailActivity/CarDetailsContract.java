package com.michalrubajczyk.myfirebrigade.activity.CarDetailActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface CarDetailsContract {

    interface ViewDetails extends BaseView<PresenterDetails> {
        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showMissingCar();

        void setCarId(String carId);

        void hideModel();

        void showModel(String model);

        void hideOperationalNumbers();

        void showOperationalNumbers(String operationalNumbers);

        void hidePlates();

        void showPlates(String plates);

        void hideType();

        void showType(String Type);

        void hideWater();

        void showWater(int water);

        void hideFoam();

        void showFoam(int foam);

        void hideMotorPumpPerformance();

        void showMotoPumpPerformance(int motorPumpPerformance);

        void hideNumberOfSeats();

        void showNumberOfSeats(int numberOfSeats);

        void setActivityName(String name);

        void showEditCar(int carId);

        boolean isActive();

    }

    interface PresenterDetails extends BasePresenter {

        void editCar();

        void deleteCar();

    }

}
