package com.project.springbackend.Controller;

import com.project.springbackend.Domain.User;
import com.project.springbackend.Model.Model;
import com.project.springbackend.Service.UserService;
import com.project.springbackend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {
    @Autowired
    private UserService userService;
    private static final String entityApi = "users";
    private Utils utils = new Utils();

    @PostMapping(value = "/api/" + entityApi, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Model post(@RequestBody User user) {
        user.setPassword(this.utils.encodePassword(user.getPassword()));
        userService.save(user);
        this.utils.cleanUser(user);
        return user;
    }

    @GetMapping (value = "/api/" + entityApi + "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Model get(@PathVariable String username){
        User user = userService.retrieve(username);
        this.utils.cleanUser(user);
        return user;
    }

}
