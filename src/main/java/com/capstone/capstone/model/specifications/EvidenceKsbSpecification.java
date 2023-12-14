package com.capstone.capstone.model.specifications;

import com.capstone.capstone.model.Entity.EvidenceKsbMapper;
import org.springframework.data.jpa.domain.Specification;

public class EvidenceKsbSpecification {


    public static Specification<EvidenceKsbMapper> hasEvidenceGuid(String evidenceGuid) {
        return (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("evidenceGuid"), evidenceGuid);
    }
}
