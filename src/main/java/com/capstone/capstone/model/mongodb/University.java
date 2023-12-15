package com.capstone.capstone.model.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection="university")
public class University {

    @Id
    private String id;

    private String universityCode;
    private String universityName;
    private String softwareEngineerDegreeCode;
    private String dataAnalyticsDegreeCode;
}
