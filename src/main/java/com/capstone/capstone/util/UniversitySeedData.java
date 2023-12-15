package com.capstone.capstone.util;

import com.capstone.capstone.model.mongodb.University;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UniversitySeedData {

    public List<University> insertUniversitySeedData() {
        List<University> universityList = new ArrayList<>();
        universityList.add(
                University.builder()
                        .universityCode("NU23")
                        .softwareEngineerDegreeCode("S23")
                        .dataAnalyticsDegreeCode("D23")
                        .universityName("University of newcastle")
                        .build());
        return universityList;
    }
}
