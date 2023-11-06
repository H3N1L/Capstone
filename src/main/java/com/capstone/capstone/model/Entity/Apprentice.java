package com.capstone.capstone.model.Entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Data
public class Apprentice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apprentice_id")
    private Integer apprenticeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "apprentice_email")
    private String email;

    @Column(name = "university_code")
    private String universityCode;

    @Column(name = "degree_code")
    private String degreeCode;

}