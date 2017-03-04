package org.parma.web.rest;

import org.parma.domain.User;
import org.parma.repository.UserRepository;
import org.parma.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserResource(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * GET /users : get all users.
     * @param pageable
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable) {
        final Page<User> page = userService.getAllUsers(pageable);
        return new ResponseEntity<List<User>>(page.getContent(), HttpStatus.OK);
    }
}
