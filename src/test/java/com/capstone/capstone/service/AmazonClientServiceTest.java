package com.capstone.capstone.service;


import com.amazonaws.services.s3.AmazonS3;
import com.capstone.capstone.model.amazon.EvidenceInformation;
import com.capstone.capstone.util.ConvertToFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AmazonClientServiceTest {

    @Mock private AmazonS3 amazonS3;
    @Mock private ConvertToFile convertToFile;
    @InjectMocks private AmazonClientService amazonClientService;


    @Test
    void whenMediaFileAndUserProvided_thenSubmitSuccessfully() {
        MultipartFile multipartFile =
                new MockMultipartFile("Mock File", "payload".getBytes(StandardCharsets.UTF_8));

        when(convertToFile.convertMultiPartFileToFile(multipartFile))
                .thenReturn(new File("String"));

        String result = amazonClientService.uploadFile(multipartFile, "1");
        assertThat(result).isEqualTo("1");
    }







}