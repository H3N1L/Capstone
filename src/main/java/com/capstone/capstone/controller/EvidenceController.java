package com.capstone.capstone.controller;


import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.EvidenceKsbMapper;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.model.specifications.EvidenceSpecification;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.repository.EvidenceKsbMapperRepository;
import com.capstone.capstone.service.AmazonClientService;
import com.capstone.capstone.service.ApprenticeService;
import com.capstone.capstone.service.EvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class EvidenceController {

    @Autowired private ApprenticeRepository apprenticeRepository;
    @Autowired private ApprenticeService apprenticeService;
    @Autowired private AmazonClientService amazonClientService;
    @Autowired private EvidenceRepository evidenceRepository;
    @Autowired private EvidenceKsbMapperRepository evidenceToKsbRepository;
    @Autowired private EvidenceService evidenceService;


    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

//    @GetMapping("/Connected")
//    public ResponseEntity<String> connect() {
//        return new ResponseEntity<>("Connected", HttpStatus.CREATED);
//    }


    @GetMapping("/allUsers")
    public List<Apprentice> getAllUsers() {
        return apprenticeRepository.findAll();
    }


    @PostMapping("/AddEvidence")
    public Evidence addFrontEndEvidence(
           @RequestBody UserEvidence userEvidence) throws Exception {

        String fileKey = amazonClientService.uploadEvidencePayload(userEvidence.getSubmittedContent());
        Evidence retrievedEvidence = evidenceService.uploadSubmittedEvidence(userEvidence, fileKey);
        evidenceService.uploadEvidenceToKsbMapper(retrievedEvidence);
        log.info("#### Inserted Evidence: " + retrievedEvidence);
        return retrievedEvidence;
    }

    @GetMapping("/retrieveSubmittedContent")
    public String retrieveSubmittedContent(@RequestParam String evidenceId) throws Exception {
        return amazonClientService.retrieveSubmittedContent(
                evidenceService.retrieveMediaContentKey(evidenceId));
    }

    @GetMapping("/retrieveAllApprenticeEvidence")
    public List<UserEvidence> retrieveAllApprenticeEvidence(@RequestParam Integer userId) {
        Specification<Evidence> evidenceSpecification = EvidenceSpecification.hasApprenticeId(userId);
        List<Evidence> evidences = evidenceRepository.findAll(evidenceSpecification);
        List<UserEvidence> userEvidenceList = new ArrayList<>();
        evidences.forEach(evidence -> {
            userEvidenceList.add(UserEvidence.builder()
                    .evidenceId(evidence.getEvidenceGuid())
                    .specialism(evidence.getSpecialism())
                    .type(evidence.getType())
                    .description(evidence.getEvidenceDescription())
                    .dateCreated(evidence.getInsertTimestamp().toLocalDate().toString())
                    .ksbID(evidence.getKsbId())
                    .build());
        });
        return userEvidenceList;
    }

    @GetMapping("/retrieveEvidenceSpecific")
    public UserEvidence retrieveSpecificEvidence(@RequestParam String evidenceGuid) throws Exception {
        Evidence evidence1 = evidenceService.retrieveSpecificEvidence(evidenceGuid);

        log.info("######" + evidence1);
        EvidenceKsbMapper evidenceKsbMapper1 = evidenceService.retrieveEvidencKsbMap(evidence1);
        log.info("######" + evidenceKsbMapper1);
        return UserEvidence.builder()
                .evidenceId(evidence1.getEvidenceGuid())
                .dateCreated(evidence1.getInsertTimestamp().toLocalDate().toString())
                .specialism(evidence1.getSpecialism())
                .description(evidence1.getEvidenceDescription())
                .ksbID(evidenceKsbMapper1.getKsbCode())
                .type(evidence1.getType())
                .build();
    }

    @GetMapping("/retrieveAllApprenticeContentBySpecialism")
    public List<UserEvidence> retrieveAllContentBySpecialism(
            @RequestParam Long userId,
            @RequestParam String specialism) throws Exception {

        List<UserEvidence> userEvidenceList = new ArrayList<>();
        Specification<Evidence> evidenceSpecification = Specification.where(null);
        evidenceSpecification.and(EvidenceSpecification.hasApprenticeId(userId.intValue()));
        evidenceSpecification.and(EvidenceSpecification.hasSpecialism(specialism));
        List<Evidence> evidenceList = evidenceRepository.findAll(evidenceSpecification);
        if(!evidenceList.isEmpty()){
            evidenceList.forEach(
                    evidence -> {
                        EvidenceKsbMapper evidenceKsbMapper1 = null;
                        try {
                            evidenceKsbMapper1 = evidenceService.retrieveEvidencKsbMap(evidence);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        log.info("######" + evidenceKsbMapper1);
                        UserEvidence userEvidence = UserEvidence.builder()
                                .evidenceId(evidence.getEvidenceGuid())
                                .dateCreated(evidence.getInsertTimestamp().toLocalDate().toString())
                                .specialism(evidence.getSpecialism())
                                .description(evidence.getEvidenceDescription())
                                .ksbID(evidenceKsbMapper1.getKsbCode())
                                .type(evidence.getType())
                                .build();
                        userEvidenceList.add(userEvidence);
                    });
        }
        else {
            throw new Exception("No evidence objects found for ApprenticeID: " + userId.toString() +
                    " and specialism: " + specialism);
        }
        return userEvidenceList;
    }
    

//    @PostMapping("/addUniveristySeedData")
//    public List<University> addUniversitySeedData(){
//        List<University> universityList = UniversitySeedData.insertUniversitySeedData();
//        universityList.forEach(university -> {
//            universityRepository.insert(university);
//        });
//        return universityList;
//    }
}
