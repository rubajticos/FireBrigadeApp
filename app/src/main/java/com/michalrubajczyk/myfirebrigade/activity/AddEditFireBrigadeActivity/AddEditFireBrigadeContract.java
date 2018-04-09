package com.michalrubajczyk.myfirebrigade.activity.AddEditFireBrigadeActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface AddEditFireBrigadeContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void saveFireBrigade(String name, String voivodeship, String district, String community, String city, boolean ksrg);
    }
}
