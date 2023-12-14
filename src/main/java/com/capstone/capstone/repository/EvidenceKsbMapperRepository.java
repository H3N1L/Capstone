package com.capstone.capstone.repository;

import com.capstone.capstone.model.Entity.EvidenceKsbMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvidenceKsbMapperRepository extends JpaRepository<EvidenceKsbMapper, Integer> {

     Optional<EvidenceKsbMapper> findOne(Specification<EvidenceKsbMapper> evidenceKsbMapperSpecification);
}
