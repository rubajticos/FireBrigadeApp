package com.michalrubajczyk.myfirebrigade.model.apiRequests;

interface IncidentRequest {

    void getIncidentById(int incidentId, DataListener dataListener);

    void getIncidentsByFireBrigadeId(int fireBrigadeId, DataListener dataListener);

    void getFirefightersAndCars(int fireBrigadeId, DataListener dataListener);

//    void addIncident(Incident incident, DataListener dataListener);

//    void updateIncident(Incident incident, DataListener dataListener);

}
