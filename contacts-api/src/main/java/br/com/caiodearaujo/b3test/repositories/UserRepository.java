package br.com.caiodearaujo.b3test.repositories;

import br.com.caiodearaujo.b3test.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCompanyIdAndEmail(Long companyId, String email);

    Page<User> findAllByEmail(String email, Pageable page);

}
