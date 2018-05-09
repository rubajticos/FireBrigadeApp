package com.michalrubajczyk.myfirebrigade.activity.CarActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.Car;

import java.util.List;

public interface CarContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showCars(List<Car> carsList);

        void showAddCar();

        void showCarDetailUi(int carId);

        void showLoadingCarsError();

        void showNoCars();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadCars(boolean forceUpdade);

        void openCarDetails(Integer requestedCarId);
    }
}
