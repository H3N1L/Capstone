package com.capstone.capstone.model.specifications;

import com.capstone.capstone.model.Entity.Apprentice;
import org.springframework.data.jpa.domain.Specification;

public class ApprenticeSpecification {


    public static Specification<Apprentice> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("first_name"), firstName);
    }

    public static Specification<Apprentice> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("last_name"), lastName);
    }

    public static Specification<Apprentice> hasDegreeCode(String degreeCode) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("degree_code"), degreeCode);
    }

    public static Specification<Apprentice> hasUniversityCode(String universityCode) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("university_code"), universityCode);
    }


}
