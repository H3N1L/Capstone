package com.capstone.capstone.repository;

import com.capstone.capstone.model.Entity.Evidence;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {

    // Example usage of findAll with Specification
    List<Evidence> findAll(Specification<Evidence> spec);

    Optional<Evidence> findOne(Specification<Evidence> evidenceSpecification);

//    //Find All Evidence By ApprenticeId and Month
//    List<Evidence>  findByApprenticeIdAndInsertTimestamp(Specification<Evidence> evidenceSpecification);
//
//    //Find by Date
//    List<Evidence> findByInsertTimestamp(Specification<Evidence> evidenceSpecification);
}
