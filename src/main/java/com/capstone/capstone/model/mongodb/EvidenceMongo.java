package com.capstone.capstone.model.mongodb;

import lombok.Builder;
import lombok.Getter;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@Builder
@Document(collection = "textEvidence")
public class EvidenceMongo {
     @Id
     private String id;

     private String evidenceKey;
     private String UserId;
     private String KsbCode;
}
