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
public class KsbDirectory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ksbDirectoryId;

    @Column(name="ksb_name")
    private String ksbName;

    @Column(name="ksb_code")
    private String ksbCode;

    @Column(name="description")
    private String description;

    @Column(name="degree_code")
    private String degreeCode;
}
