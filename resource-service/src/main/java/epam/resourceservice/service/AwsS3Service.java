package epam.resourceservice.service;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import epam.resourceservice.config.AwsS3Config;
import epam.resourceservice.domain.ResourceFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AwsS3Service {
    private final AwsS3Config awsS3Config;
    private final AmazonS3 amazonS3;

    public AwsS3Service(AwsS3Config awsS3Config, AmazonS3 amazonS3) {
        this.awsS3Config = awsS3Config;
        this.amazonS3 = amazonS3;
    }

    public ResourceFile uploadFileToS3(String fileName, byte[] fileData) {
        log.info("Uploading file '{}' to bucket: '{}' ", fileName, awsS3Config.getBucketName());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileData.length);
        String fileUrl =
                awsS3Config.getS3EndpointUrl() + "/" + awsS3Config.getBucketName() + "/" + fileName;
        PutObjectResult putObjectResult =
                amazonS3.putObject(
                        awsS3Config.getBucketName(), fileName, byteArrayInputStream, objectMetadata);
        return new ResourceFile(fileName, fileUrl, Objects.nonNull(putObjectResult));
    }

    public S3ObjectInputStream downloadFileFromS3Bucket(final String fileName) {
        log.info("Downloading file '{}' from bucket: '{}' ", fileName, awsS3Config.getBucketName());
        final S3Object s3Object = amazonS3.getObject(awsS3Config.getBucketName(), fileName);
        return s3Object.getObjectContent();
    }

    public List<S3ObjectSummary> listObjects() {
        log.info("Retrieving object summaries for bucket '{}'", awsS3Config.getBucketName());
        ObjectListing objectListing = amazonS3.listObjects(awsS3Config.getBucketName());
        return objectListing.getObjectSummaries();
    }
}
