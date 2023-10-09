package com.capstone.capstone.model.amazon;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;



@Getter
@Setter
public class AmazonImage {

    @Id
    private String userId;

    private MultipartFile multipartFile;

}
