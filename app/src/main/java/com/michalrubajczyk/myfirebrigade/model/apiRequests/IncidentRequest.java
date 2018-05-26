package com.michalrubajczyk.myfirebrigade.model.apiRequests;

import com.michalrubajczyk.myfirebrigade.model.dto.IncidentFull;

interface IncidentRequest {

    void getIncidentById(int incidentId, DataListener dataListener);

    void getIncidentsByFireBrigadeId(int fireBrigadeId, DataListener dataListener);

    void getFirefightersAndCars(int fireBrigadeId, DataListener dataListener);

    void addIncident(IncidentFull incident, int fireBrigadeId, DataListener dataListener);

//    void updateIncident(Incident incident, DataListener dataListener);

}
