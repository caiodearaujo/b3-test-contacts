package br.com.caiodearaujo.b3test.services;

import br.com.caiodearaujo.b3test.entities.FileImport;
import br.com.caiodearaujo.b3test.repositories.FileImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FileImportService {

    private FileImportRepository repository;

    @Autowired
    public FileImportService(FileImportRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveIfNews(FileImport fileImport) {
        if (!this.repository.existsByFileName(fileImport.getFileName())) {
            this.repository.save(fileImport);
        }
    }

    @Transactional(readOnly = true)
    public List<FileImport> findAllNotImported() {
        return this.repository.findFilesToImport();
    }

    @Transactional
    public FileImport initImport(FileImport fileImport) {
        fileImport.setInitProcess(new Date());
        return this.repository.saveAndFlush(fileImport);
    }

    @Transactional
    public void save(FileImport fileImport) {
        this.repository.saveAndFlush(fileImport);
    }
}
