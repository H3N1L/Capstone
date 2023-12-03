package com.capstone.capstone.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "evidenceKsbMapper")
public class EvidenceKsbMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evidenceToKsbId;

    @Column(name="evidence_id")
    private Integer evidenceId;

    @Column(name="ksb_code")
    private String ksbCode;
}
