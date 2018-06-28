package com.michalrubajczyk.myfirebrigade.model.apiRequests;

public interface AnalysisRequest {

    void analysisDetail(boolean fullReport, int fireBrigadeId, DataListener dataListener);

    void analysisGeneral(boolean fullReport, int fireBrigadeId, DataListener dataListener);

}
