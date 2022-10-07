package epam.songservice.handler;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import epam.songservice.constants.PathVariableParam;
import epam.songservice.domain.SongInfo;
import epam.songservice.service.AwsS3Service;
import epam.songservice.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@Slf4j
public class AwsS3Handler {

    private final AwsS3Service awsS3Service;
    private SongService resourceFileService;

    public AwsS3Handler(AwsS3Service awsS3Service, SongService resourceFileService) {
        this.awsS3Service = awsS3Service;
        this.resourceFileService = resourceFileService;
    }

    @NonNull
    public Mono<ServerResponse> resourceFileFromDB(ServerRequest request) {
        final String fileName = request.pathVariable(PathVariableParam.NAME);
        Flux<SongInfo> fileByName = resourceFileService.getFileByName(fileName);
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(fileByName, SongInfo.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @NonNull
    public Mono<ServerResponse> viewAllFromDB() {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(resourceFileService.findAllFiles(), SongInfo.class);
    }

    @NonNull
    public Mono<ServerResponse> viewFromS3() {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(awsS3Service.listObjects()), S3ObjectSummary.class);
    }

    @NonNull
    public Mono<ServerResponse> uploadToS3(ServerRequest request) {
        return request
                .multipartData()
                .flatMap(
                        pMultiValueMap -> {
                            List<Part> file = pMultiValueMap.get(PathVariableParam.FILES);
                            Flux<SongInfo> uploadResult =
                                    Flux.fromIterable(file)
                                            .cast(FilePart.class)
                                            .flatMap(
                                                    pFilePart -> {
                                                        String fileName = pFilePart.filename();
                                                        return pFilePart
                                                                .content()
                                                                .filter(
                                                                        pDataBuffer ->
                                                                                new Byte[pDataBuffer.readableByteCount()].length > 0)
                                                                .flatMap(
                                                                        pDataBuffer -> {
                                                                            log.info("Upload file '{}' started", fileName);
                                                                            SongInfo resourceFile = new SongInfo();
                                                                            try {
                                                                                byte[] data = new byte[pDataBuffer.readableByteCount()];
                                                                                pDataBuffer.read(data);
                                                                                resourceFile = awsS3Service.uploadFileToS3(fileName, data);
                                                                                log.info("Upload file '{}' finished", fileName);
                                                                            } catch (Exception e) {
                                                                                log.info("Upload file '{}' failed", fileName, e);
                                                                            }
                                                                            return Mono.just(resourceFile);
                                                                        });
                                                    });
                            Flux<SongInfo> resourceFileFlux = resourceFileService.saveSongInfo(uploadResult);
                            return ok().contentType(MediaType.APPLICATION_JSON).body(resourceFileFlux, SongInfo.class);
                        });
    }

    @NonNull
    public Mono<ServerResponse> downloadFromS3(ServerRequest request) {
        String name = request.pathVariable(PathVariableParam.NAME);
        InputStreamResource inputStreamResource =
                new InputStreamResource(awsS3Service.downloadFileFromS3Bucket(name));
        return ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(BodyInserters.fromResource(inputStreamResource));
    }
}
