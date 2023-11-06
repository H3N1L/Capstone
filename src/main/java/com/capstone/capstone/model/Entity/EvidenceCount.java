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
public class EvidenceCount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evidenceCountId;


    @Column(name = "apprentice_id")
    private Integer apprenticeId;

    @Column(name = "ksb_code")
    private Integer ksbCode;

    @Column(name = "timestamp")
    private LocalDateTime insertTimestamp;

    @Column(name = "evidence_count")
    private Integer count;





}
