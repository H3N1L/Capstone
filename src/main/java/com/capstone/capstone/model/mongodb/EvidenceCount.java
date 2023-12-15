package com.capstone.capstone.model.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@Document(collection = "evidenceCount")
public class EvidenceCount {

    @Id
    private String id;

    private String ksbCode;
    private String userId;
    private BigDecimal count;
}
