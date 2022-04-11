package com.project.springbackend.Controller;

import com.project.springbackend.Domain.User;
import com.project.springbackend.Service.UserService;
import com.project.springbackend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class ApiController {
    @Autowired
    private UserService userService;
    private static final String entityApi = "users";
    private Utils utils = new Utils();

    @PostMapping(value = "/api/" + entityApi, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<User> post(@RequestBody User user) {
        user.setPassword(this.utils.encodePassword(user.getPassword()));
        userService.save(user);
        this.utils.cleanUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping (value = "/api/" + entityApi + "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<User> get(@PathVariable String username){
        User user = userService.retrieve(username);
        this.utils.cleanUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/api/" + entityApi + "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<HashMap<String, Boolean>> verifyLoginStatus(@RequestBody User userToValidate){
        User storedUser = userService.retrieve(userToValidate.getUsername());
        boolean loginStatus = this.utils.checkMatch(storedUser.getPassword(), userToValidate.getPassword());
        HashMap response = new HashMap();
        response.put("ValidationStatus", loginStatus);
        return ResponseEntity.ok(response);
    }

}
