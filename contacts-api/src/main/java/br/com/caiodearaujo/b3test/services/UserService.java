package br.com.caiodearaujo.b3test.services;

import br.com.caiodearaujo.b3test.dto.UserDTO;
import br.com.caiodearaujo.b3test.entities.User;
import br.com.caiodearaujo.b3test.exceptions.InvalidUserDataException;
import br.com.caiodearaujo.b3test.exceptions.UserAlreadyExistsException;
import br.com.caiodearaujo.b3test.exceptions.UserNotFoundException;
import br.com.caiodearaujo.b3test.factories.UserFactory;
import br.com.caiodearaujo.b3test.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) throws UserNotFoundException {
        return this.repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<User> findByEmail(String email) {
        Example<User> example = Example.of(User.builder().email(email).build(),
                ExampleMatcher.matchingAll().withMatcher("email",
                        ExampleMatcher.GenericPropertyMatchers.exact()));
        return this.repository.findAll(example);
    }

    @Transactional(readOnly = true)
    public Page<User> listAll(Integer currentPage, Integer pageSize) {
        return this.repository.findAll(PageRequest.of(currentPage, pageSize));
    }

    @Transactional
    public User save(UserDTO payload) throws InvalidUserDataException, UserAlreadyExistsException {
        User user = UserFactory.getInstance().toEntity(payload);
        if (this.repository.findByCompanyIdAndEmail(user.getCompanyId(), user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(user);
        }
        this.repository.save(user);
        log.info("Saved user {}", user.getUserId());
        return user;
    }
}
