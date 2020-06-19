package br.com.caiodearaujo.b3test.repositories;

import br.com.caiodearaujo.b3test.entities.FileImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileImportRepository extends JpaRepository<FileImport, String> {

    @Query("FROM FileImport WHERE initProcess is null ORDER BY importDate ASC")
    List<FileImport> findFilesToImport();

    Boolean existsByFileName(String fileName);

}
