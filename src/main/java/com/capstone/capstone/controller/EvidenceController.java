package com.capstone.capstone.controller;


import com.capstone.capstone.model.Entity.Apprentice;
import com.capstone.capstone.model.Entity.Evidence;
import com.capstone.capstone.model.Entity.UserEvidence;
import com.capstone.capstone.repository.ApprenticeRepository;
import com.capstone.capstone.repository.Archive.MongoEvidenceRepository;
import com.capstone.capstone.repository.Archive.MongoUniversityRepository;
import com.capstone.capstone.repository.Archive.MongoUserInformationRepository;
import com.capstone.capstone.model.mongodb.EvidenceMongo;
import com.capstone.capstone.model.mongodb.UserInformation;
import com.capstone.capstone.repository.EvidenceRepository;
import com.capstone.capstone.service.AmazonClientService;
import com.capstone.capstone.service.ApprenticeService;
import com.capstone.capstone.service.TextEvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
public class EvidenceController {

    @Autowired private TextEvidenceService textEvidenceService;
    @Autowired private MongoUserInformationRepository userInformationRepository;
    @Autowired private MongoUniversityRepository universityRepository;
    @Autowired private ApprenticeRepository apprenticeRepository;

    @Autowired private ApprenticeService apprenticeService;
    @Autowired private AmazonClientService amazonClientService;
    @Autowired private EvidenceRepository evidenceRepository;



//    @ApiIgnore
//    @RequestMapping(value="/")
//    public void redirect(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/swagger-ui.html");
//    }

    @PostMapping("/addApprentice")
    public Apprentice  addApprentice() {
        return apprenticeRepository.save(
                Apprentice.builder()
                        .email("henil.soni13@gmail.com")
                        .firstName("Fenil")
                        .lastName("Soni")
                        .build());
    }

    @GetMapping("/Connected")
    public ResponseEntity<String> connect() {
        return new ResponseEntity<>("Connected", HttpStatus.CREATED);
    }

    @PostMapping("/updateApprenticeFirstName")
    public Apprentice updateApprenticeName(){
        return apprenticeService.updateFirstName(1, "Henil");
    }

    @GetMapping("/evidence")
    public List<EvidenceMongo> getAllEvidence() {
        return textEvidenceService.getAllEvidence();
    }

    @GetMapping("/users")
    public List<UserInformation> getAllUsers() {
        return userInformationRepository.findAll();
    }

    @GetMapping("/updateUserKey")
    public UserInformation updateUserKey() {

        UserInformation userInformation =  userInformationRepository.findByFistName("Henil");
        userInformation.setKey(UUID.randomUUID().toString());
        return userInformationRepository.save(userInformation);
    }

    @PostMapping("/AddEvidence")
    public String addFrontEndEvidence(
           @RequestBody UserEvidence userEvidence) {

        String fileKey = amazonClientService.uploadEvidencePayload(userEvidence.getSubmittedContent());
        Evidence evidence =
                Evidence.builder()
                        .evidenceCategory(userEvidence.getType())
                        .evidenceDescription("First Complete Evidence Flow")
                        .s3Guid(fileKey)
                .build();

        evidenceRepository.save(evidence);

        log.info("Evidence ------------> " + userEvidence);

        return "Evidence Submitted successfully and " + fileKey;
    }

//    @PostMapping("/addMarvin")
//    public ResponseEntity<String> addMarvin () {
//        return new ResponseEntity<>(makeMarvin(), HttpStatus.ACCEPTED);
//    }
//
//    public String makeMarvin() {
//        UserInformation userInformation =
//                UserInformation.builder()
//                        .firstName("Marvin")
//                        .lastName("Mbasu")
//                        .email("marvin@marvin.com")
//                        .id("2")
//                        .universityCode("NUC2023")
//                        .degreeCode("SE2022")
//                        .key(UUID.randomUUID().toString())
//                        .specialKsb(new ArrayList<>(Collections.singleton("K1")))
//                        .build();
//
//        userInformationRepository.insert(userInformation);
//
//        return "Marvin is in the building";
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
