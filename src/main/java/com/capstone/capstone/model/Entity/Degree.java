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
public class Degree {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer degreeId;

    @Column(name = "degree_name")
    private String degreeName;

    @Column(name = "degree_code")
    private String degreeCode;

    @Column(name = "university_code")
    private String universityCode;


}
