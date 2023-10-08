package com.capstone.capstone.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@Document(collection = "textEvidence")
public class Evidence  {
     @Id
     private String id;

     private String evidence;
     private String KsbCode;
     private String userKey;
}
