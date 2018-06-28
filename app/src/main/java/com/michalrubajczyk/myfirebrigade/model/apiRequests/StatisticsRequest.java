package com.michalrubajczyk.myfirebrigade.model.apiRequests;

interface StatisticsRequest {

    void getStatisticsForFireBrigade(int firebrigadeId, DataListener dataListener);

}
