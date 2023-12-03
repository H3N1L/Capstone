package com.capstone.capstone.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evidence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evidenceId;

    @Column(name = "evidence_type")
    private String type;

    @Column(name = "evidence_specialism")
    private String specialism;

    @Column(name="apprentice_id")
    private Integer apprenticeId;

    @Column(name="s3_guid")
    private String s3Guid;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "insert_timestamp", updatable = false)
    private Date insertTimestamp;

    @Column(name="evidence_description")
    private String evidenceDescription;

}
