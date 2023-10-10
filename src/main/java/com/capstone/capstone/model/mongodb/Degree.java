package com.capstone.capstone.model.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Builder
@Getter
@Setter
public class Degree {

    @Id
    private String id;

    private String universityCode;
    private String degreeName;
    private String description;
    private String degreeCode;
}
