package com.michalrubajczyk.myfirebrigade.model.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class PreparedCarInIncident {

    String carName;
    String datetimeOfDeparture;
    String datetimeOfReturn;
    String commanderName;
    String driverName;
    List<String> firefightersNames;
    List<String> equipmentNames;

    public static List<String> makeAStringList(String string, String regex) {
        List<String> stringList = Arrays.asList(string.split(regex));
        return stringList;
    }

}
