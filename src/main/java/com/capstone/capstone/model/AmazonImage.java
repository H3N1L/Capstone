package com.capstone.capstone.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document
public class AmazonImage {

    @Id
    private String amazonUserImageId;

    @NonNull
    private String imageUrl;

}
