package com.capstone.capstone.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.capstone.capstone.model.amazon.EvidenceInformation;
import com.capstone.capstone.util.ConvertToFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class AmazonClientService {


    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    TextEvidenceService textEvidenceService;

    @Value("${amazon.s3.endpoint}")
    private String url;

    // bucket name.
    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    // Getters for parents.
    protected AmazonS3 getClient() {
        return amazonS3;
    }

    protected String getUrl() {
        return url;
    }

    protected String getBucketName() {
        return bucketName;
    }


    public String uploadFile(MultipartFile multipartFile, String userID) {
        File file = ConvertToFile.convertMultiPartFileToFile(multipartFile);
        PutObjectResult putObjectResult = amazonS3.putObject(bucketName, userID, file);
        file.delete();
        return userID;
    }

    public String submitMediaEvidence(MultipartFile multipartFile, EvidenceInformation evidenceInformation) {
        String fileKey = UUID.randomUUID().toString();
        File file = ConvertToFile.convertMultiPartFileToFile(multipartFile);
        amazonS3.putObject(bucketName, fileKey, file);
        textEvidenceService.addEvidenceLink(evidenceInformation);
        textEvidenceService.insertEvidence(evidenceInformation, fileKey);
        file.delete();
        return "Evidence submitted successfully";
    }

    public String uploadEvidencePayload(String evidence) {
        String fileKey = UUID.randomUUID().toString();
        amazonS3.putObject(bucketName,fileKey, evidence);
        return fileKey;
    }

    public String retrieveSubmittedContent(String s3Guid) {
        return amazonS3.getObjectAsString(bucketName, s3Guid);
    }












}

