package com.capstone.capstone.model.specifications;

import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.Entity.Evidence;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EvidenceSpecification {

    public static Specification<Evidence> hasEvidenceCategory(String evidenceCategory) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("evidence_category"), evidenceCategory);
    }

    public static Specification<Evidence> hasApprenticeId(Integer apprenticeId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("apprentice_id"), apprenticeId);
    }

    public static Specification<Evidence> hasS3Guid(String s3Guid) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("s3_guid"), s3Guid);
    }

    public static Specification<Evidence> hasUniversityCode(String universityCode) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("university_code"), universityCode);
    }

    public static Specification<Evidence> hasApprenticeIdAndInsertTimestamp(Integer apprenticeId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("apprentice_id"), apprenticeId));

            // Getting the current month's start and end date
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime endOfMonth = now
                    .withDayOfMonth(now.getMonth().maxLength()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);

            // Adding the condition for the timestamp within the current month
            predicates.add(criteriaBuilder.between(root.get("insert_timestamp"), startOfMonth, endOfMonth));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Evidence> hasInsertTimestamp(Integer apprenticeId, LocalDateTime submittedOn) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("apprentice_id"), apprenticeId));
            LocalDateTime startOfDay = submittedOn.withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime endOfDay = submittedOn.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
            predicates.add(criteriaBuilder.between(root.get("insert_timestamp"), startOfDay, endOfDay));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }








}
