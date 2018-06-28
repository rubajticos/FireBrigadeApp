package com.michalrubajczyk.myfirebrigade.activity.AnalysisActivity;

import com.michalrubajczyk.myfirebrigade.BasePresenter;
import com.michalrubajczyk.myfirebrigade.BaseView;

public interface AnalysisContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void hideLoadingIndicator();

        void showReport();

        void showNoAnalysisTypeError();

        void showNoReportTypeError();

        void showAnalysisError();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void makeAnalysis();

    }
}
