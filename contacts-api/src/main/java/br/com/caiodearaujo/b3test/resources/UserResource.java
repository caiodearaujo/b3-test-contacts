package br.com.caiodearaujo.b3test.resources;

import br.com.caiodearaujo.b3test.dto.UserDTO;
import br.com.caiodearaujo.b3test.exceptions.ErrorResponse;
import br.com.caiodearaujo.b3test.exceptions.InvalidUserDataException;
import br.com.caiodearaujo.b3test.exceptions.UserAlreadyExistsException;
import br.com.caiodearaujo.b3test.exceptions.UserNotFoundException;
import br.com.caiodearaujo.b3test.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserResource {

    private UserService service;

    @Autowired
    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "currentPage", defaultValue = "0", required = false) Integer currentPage,
                                  @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        try {
            return ResponseEntity.ok(this.service.listAll(currentPage, pageSize));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse.from(e));
        }
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity getByUserId(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(this.service.findById(userId));
        } catch (UserNotFoundException unfe) {
            log.warn(unfe.getMessage(), unfe);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.from(unfe));
        } catch (Exception e) {
            log.error(String.format("Error on try finding user with id: %d", userId), e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse.from(e));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getByEmail(@PathVariable("email") String email) {
        try {
            return ResponseEntity.ok(this.service.findByEmail(email));
        } catch (Exception e) {
            log.error(String.format("Error on try finding users with email: %d", email), e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse.from(e));
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDTO payload) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(payload));
        } catch (UserAlreadyExistsException | InvalidUserDataException e) {
            log.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body(ErrorResponse.from(e));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse.from(e));
        }
    }

}
