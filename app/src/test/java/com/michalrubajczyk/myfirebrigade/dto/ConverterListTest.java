package com.michalrubajczyk.myfirebrigade.dto;

import com.michalrubajczyk.myfirebrigade.model.dto.PreparedCarInIncident;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

public class ConverterListTest {

    @Test
    public void SplitListByCommaShouldReturnList() {
        String s = "Object1, Object2, Object3";
        List<String> list = PreparedCarInIncident.makeAStringList(s, ", ");

        Assert.assertEquals("Object1", list.get(0));
        Assert.assertEquals("Object2", list.get(1));
        Assert.assertEquals("Object3", list.get(2));


    }

}
