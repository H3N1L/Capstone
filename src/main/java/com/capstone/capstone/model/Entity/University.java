package com.capstone.capstone.model.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer universityId;

    @Column(name= "university_name")
    private String universityName;

    @Column(name = "university_code")
    private String universityCode;


}
