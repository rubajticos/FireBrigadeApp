package com.michalrubajczyk.myfirebrigade.activity.FirefighterActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;

import java.util.List;

public interface FirefighterContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showFirefighters(List<Firefighter> firefightersList);

        void showAddFirefighter();

        void showFirefighterDetailUi(int firefighterId);

        void showLoadingFirefightersError();

        void showNoFirefighters();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadFirefighters(boolean forceUpdade);

        void addNewFirefighter();

        void deleteFirefighter();

        void openFirefighterDetails(Integer requestedFirefighterId);
    }
}
