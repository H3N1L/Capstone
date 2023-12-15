package com.capstone.capstone.service;

import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.EvidenceKsbMapper;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.model.specifications.EvidenceKsbSpecification;
import com.capstone.capstone.model.specifications.EvidenceSpecification;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceKsbMapperRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceService {

    @Autowired private ApprenticeRepository apprenticeRepository;
    @Autowired private EvidenceRepository evidenceRepository;
    @Autowired private EvidenceKsbMapperRepository evidenceKsbMapperRepository;



    public List<Evidence> retrieveAllEvidenceByApprenticeId(Integer apprenticeId) {
        return evidenceRepository.findAll(Specification.where(
                EvidenceSpecification.hasApprenticeId(apprenticeId)
        ));
    }

    public Evidence uploadSubmittedEvidence(UserEvidence userEvidence, String fileKey) throws Exception {

        Evidence evidence = Evidence.builder()
                .evidenceDescription(userEvidence.getDescription())
                .type(userEvidence.getType())
                .apprenticeId(1)
                .evidenceGuid(userEvidence.getEvidenceId())
                .specialism(userEvidence.getSpecialism())
                .ksbId(userEvidence.getKsbID())
                .s3Guid(fileKey)
                .build();

        try{
            return evidenceRepository.save(evidence);
        }
        catch(Exception exception) {
            throw new Exception("Failed to save submitted evidence" + exception);
        }
    }

    public void uploadEvidenceToKsbMapper(Evidence evidence) throws Exception {
        try {
            evidenceKsbMapperRepository.save(EvidenceKsbMapper.builder()
                    .evidenceGuid(evidence.getEvidenceGuid())
                    .ksbCode(evidence.getKsbId())
                    .build());
        }
        catch(Exception exception) {
            throw new Exception("Failed to insert evidence to ksb Mapper for provided Guid: " +
                    evidence.getEvidenceGuid());
        }
    }

    public String retrieveMediaContentKey(String evidenceGuid) throws Exception {

        Evidence retrievedEvidence = evidenceRepository.findOne(EvidenceSpecification.hasEvidenceGuid(evidenceGuid))
                    .orElseThrow(
                            () ->
                            new Exception("Failed to retrieve evidence with GUID: " + evidenceGuid));

        return retrievedEvidence.getS3Guid();
    }

    public Evidence retrieveSpecificEvidence(String evidenceGuid) throws Exception {
        return evidenceRepository.findOne(EvidenceSpecification.hasEvidenceGuid(evidenceGuid))
                .orElseThrow(
                        () -> new Exception("Failed to retrieve evidence with GUID: " + evidenceGuid));
    }

    public EvidenceKsbMapper retrieveEvidencKsbMap(Evidence evidence) throws Exception {
        return evidenceKsbMapperRepository.findOne(EvidenceKsbSpecification.hasEvidenceGuid(evidence.getEvidenceGuid()))
                .orElseThrow(
                        () -> new Exception("Failed to retrieve mapper for GUID: " + evidence.getEvidenceGuid()));
    }

//    public List<Evidence> retrieveAllMonthlyApprenticeEvidence(Integer apprenticeId) {
//        return evidenceRepository
//                .findByApprenticeIdAndInsertTimestamp(EvidenceSpecification
//                        .hasApprenticeIdAndInsertTimestamp(apprenticeId));
//    }
//
//    public List<Evidence> retrieveApprenticeEvidenceByDate(Integer apprenticeId, LocalDateTime submittedOn) {
//        return evidenceRepository.findByInsertTimestamp(EvidenceSpecification.hasInsertTimestamp(apprenticeId,submittedOn));
//
//    }


}
