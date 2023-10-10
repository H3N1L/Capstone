package com.capstone.capstone.model.mongodb;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Builder
@Getter
@Setter
public class Specialksb {

    @Id
    private String id;

    private String ksbCode;
    private String description;
    private String universityCode;
    private String degreeCode;
}
