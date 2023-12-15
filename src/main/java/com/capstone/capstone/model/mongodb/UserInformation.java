package com.capstone.capstone.model.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@Document(collection = "User")
public class UserInformation  {


    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String key;
    private ArrayList<String> coreKsb;
    private ArrayList<String> specialKsb;
    private String universityCode;
    private String degreeCode;
    private String email;
}
