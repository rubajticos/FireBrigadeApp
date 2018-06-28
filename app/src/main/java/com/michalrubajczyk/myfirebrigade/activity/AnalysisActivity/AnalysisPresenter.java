package com.michalrubajczyk.myfirebrigade.activity.AnalysisActivity;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.AnalysisRequestImpl;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

public class AnalysisPresenter implements AnalysisContract.Presenter {

    private final AnalysisRequestImpl mAnalysisRequest;

    private final AnalysisContract.View mAnalysisView;

    private final FireBrigadeUtils mFireBrigadeUtils;

    private final int mFireBrigadeId;

    public AnalysisPresenter(FireBrigadeUtils fireBrigadeUtils, AnalysisRequestImpl analysisRequest, AnalysisContract.View analysisView) {
        this.mFireBrigadeUtils = fireBrigadeUtils;
        this.mFireBrigadeId = mFireBrigadeUtils.getFireBrigadeIdFromSharedPreferences();
        this.mAnalysisRequest = analysisRequest;
        this.mAnalysisView = analysisView;
        this.mAnalysisView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void makeAnalysis() {

    }
}
