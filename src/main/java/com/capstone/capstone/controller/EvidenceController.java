package com.capstone.capstone.controller;


import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.model.mongodb.EvidenceMongo;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.service.AmazonClientService;
import com.capstone.capstone.service.ApprenticeService;
import com.capstone.capstone.service.TextEvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
public class EvidenceController {

    @Autowired private TextEvidenceService textEvidenceService;
    @Autowired private ApprenticeRepository apprenticeRepository;

    @Autowired private ApprenticeService apprenticeService;
    @Autowired private AmazonClientService amazonClientService;
    @Autowired private EvidenceRepository evidenceRepository;



    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/addApprentice")
    public Apprentice  addApprentice() {
        return apprenticeRepository.save(
                Apprentice.builder()
                        .email("henil.soni13@gmail.com")
                        .firstName("Fenil")
                        .lastName("Soni")
                        .build());
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
        Evidence evidence =
                Evidence.builder()
                        .evidenceCategory(userEvidence.getType())
                        .evidenceDescription("First Complete Evidence Flow")
                        .s3Guid(fileKey)
                .build();

        return evidenceRepository.save(evidence);

//        log.info("Evidence ------------> " + userEvidence);
//
//        return "Evidence Submitted successfully and " + fileKey;
    }

//    @GetMapping("/retrieveAllApprenticeEvidence")
//    public List<UserEvidence> retrieveAllUserEvidenceByApprenticeId(Integer apprenticeId) {
//        List<Evidence> evidences = evidenceRepository
//                .findAll(Specification.where(EvidenceSpecification.hasApprenticeId(apprenticeId)));
//
//        List<UserEvidence> userEvidences = new ArrayList<>();
//
//        if(!evidences.isEmpty()){
//            evidences.forEach(evidence -> {
//
//            });
//
//        }
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
