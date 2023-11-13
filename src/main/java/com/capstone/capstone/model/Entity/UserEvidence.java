package com.capstone.capstone.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEvidence {

    @Id
    private String evidenceId;

    private String ksbID;

    private String type;

    private String specialism;

    private String dateCreated;

    private String description;

    private String submittedContent;

}
