package com.core.webapi.controller;

import com.core.webapi.model.User;
import com.core.webapi.services.RoleService;
import com.core.webapi.services.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "username", required = false) String username)
    {
        List<User> users = this.userService.getUsers(username);
        if(!CollectionUtils.isEmpty(users))
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            throw new ResourceNotFoundException();
    }


}
