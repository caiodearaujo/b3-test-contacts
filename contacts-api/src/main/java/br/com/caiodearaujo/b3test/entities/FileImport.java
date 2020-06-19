package br.com.caiodearaujo.b3test.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@ToString
@AllArgsConstructor
@Table(name = "b3_file_import")
public class FileImport {

    @Id
    @Getter
    private String fileId;
    @Setter
    @Getter
    @Column(name = "file_name")
    private String fileName;
    @Setter
    @Getter
    @Column(name = "processed")
    private Boolean processed;
    @Setter
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "import_date")
    private Date importDate;
    @Setter
    @Getter
    @Column(name = "init_process")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initProcess;
    @Setter
    @Getter
    @Column(name = "finish_process")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishProcess;

    @Builder
    public FileImport() {
        this.fileId = UUID.randomUUID().toString();
        this.processed = false;
    }

    public FileImport(String fileName) {
        this();
        this.fileName = fileName;
    }
}
