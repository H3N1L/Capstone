package com.capstone.capstone.controller;


import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.EvidenceKsbMapper;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.model.mongodb.EvidenceMongo;
import com.capstone.capstone.model.specifications.EvidenceSpecification;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.repository.EvidenceKsbMapperRepository;
import com.capstone.capstone.service.AmazonClientService;
import com.capstone.capstone.service.ApprenticeService;
import com.capstone.capstone.service.TextEvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class EvidenceController {

    @Autowired private TextEvidenceService textEvidenceService;
    @Autowired private ApprenticeRepository apprenticeRepository;
    @Autowired private ApprenticeService apprenticeService;
    @Autowired private AmazonClientService amazonClientService;
    @Autowired private EvidenceRepository evidenceRepository;
    @Autowired private EvidenceKsbMapperRepository evidenceToKsbRepository;


    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


//    @GetMapping("/Connected")
//    public ResponseEntity<String> connect() {
//        return new ResponseEntity<>("Connected", HttpStatus.CREATED);
//    }

    @PostMapping("/updateApprenticeFirstName")
    public Apprentice updateApprenticeName(){
        return apprenticeService.updateFirstName(1, "Henil");
    }

    @GetMapping("/allEvidence")
    public List<EvidenceMongo> getAllEvidence() {
        return textEvidenceService.getAllEvidence();
    }

    @GetMapping("/allUsers")
    public List<Apprentice> getAllUsers() {
        return apprenticeRepository.findAll();
    }

//    @GetMapping("/updateUserKey")
//    public UserInformation updateUserKey() {
//
//        UserInformation userInformation =  userInformationRepository.findByFistName("Henil");
//        userInformation.setKey(UUID.randomUUID().toString());
//        return userInformationRepository.save(userInformation);
//    }

    @PostMapping("/AddEvidence")
    public Evidence addFrontEndEvidence(
           @RequestBody UserEvidence userEvidence) {

        String fileKey = amazonClientService.uploadEvidencePayload(userEvidence.getSubmittedContent());
        Evidence evidence = Evidence.builder()
                .evidenceDescription(userEvidence.getDescription())
                .type(userEvidence.getType())
                .apprenticeId(1)
                .specialism(userEvidence.getSpecialism())
                .s3Guid(fileKey)
                .build();

        Evidence retrievedEvidence = evidenceRepository.save(evidence);

        evidenceToKsbRepository.save(EvidenceKsbMapper.builder()
                .evidenceId(retrievedEvidence.getEvidenceId())
                .ksbCode(userEvidence.getKsbID())
                .build());

        log.info("#### Inserted Evidence: " + retrievedEvidence);
        return retrievedEvidence;

    }

    @GetMapping("/retrieveSubmittedContent")
    public String retrieveSubmittedContent(@RequestParam Long evidenceId) {

        Optional<Evidence> evidenceOptional = evidenceRepository.findById(evidenceId.intValue());
        if(evidenceOptional.isPresent()) {
            return amazonClientService.retrieveSubmittedContent(
                    evidenceOptional
                            .get()
                            .getS3Guid());
        }
        else
            return "No Evidence Found for evidenceID: " + evidenceId;
    }

    @GetMapping("/retrieve")
    public String retrieve(@RequestParam String s3Key) {
        return amazonClientService.retrieveSubmittedContent(s3Key);
    }

    @GetMapping("/retrieveAllApprenticeEvidence")
    public List<UserEvidence> retrieveAllApprenticeEvidence(@RequestParam Long userId) {
        Specification<Evidence> evidenceSpecification = EvidenceSpecification.hasApprenticeId(userId.intValue());
        List<Evidence> evidences = evidenceRepository.findAll(evidenceSpecification);
        List<UserEvidence> userEvidenceList = new ArrayList<>();
        evidences.forEach(evidence -> {
            userEvidenceList.add(UserEvidence.builder()
                    .evidenceId(evidence.getEvidenceId().toString())
                    .dateCreated(evidence.getInsertTimestamp().toString())
                    .specialism(evidence.getSpecialism())
                    .type(evidence.getType())
                    .description(evidence.getEvidenceDescription())
                    .build());
        });
        return userEvidenceList;
    }

//    @GetMapping("/retrieveAllApprenticeContentBySpecialism")
//    public List<UserEvidence> retrieveAllContentBySpecialism(
//            @RequestParam Long userId,
//            @RequestParam String specialism) {
//
//        List<UserEvidence> userEvidenceList = new ArrayList<>();
//        List<Evidence> evidenceList = evidenceRepository.findAll(EvidenceSpecification.hasSpecialism())
//
//
//    }
    

//    @PostMapping("/addUniveristySeedData")
//    public List<University> addUniversitySeedData(){
//        List<University> universityList = UniversitySeedData.insertUniversitySeedData();
//        universityList.forEach(university -> {
//            universityRepository.insert(university);
//        });
//        return universityList;
//    }
}
