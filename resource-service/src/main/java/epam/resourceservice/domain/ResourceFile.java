package epam.resourceservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Component
@Table("resource_file")
public class ResourceFile {
    @Id
    private Long id;
    private String fileName;
    private String fileUrl;
    private boolean isUploadSuccessful;

    public ResourceFile(String fileName, String fileUrl, boolean isUploadSuccessful) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.isUploadSuccessful = isUploadSuccessful;
    }
}
