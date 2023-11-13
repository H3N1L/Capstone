package com.capstone.capstone.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evidence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evidenceId;

    @Column(name = "evidence_category")
    private String evidenceCategory;

    @Column(name="apprentice_id")
    private Integer apprenticeId;

    @Column(name="s3_guid")
    private String s3Guid;

    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp;

    @Column(name="evidence_description")
    private String evidenceDescription;

}
