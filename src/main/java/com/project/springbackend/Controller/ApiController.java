package com.project.springbackend.Controller;

import com.project.springbackend.Controller.ApiResponse.ApiResponse;
import com.project.springbackend.Domain.User;
import com.project.springbackend.Service.UserService;
import com.project.springbackend.Session.UserSessionSingleton;
import com.project.springbackend.Utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class ApiController {
    @Autowired
    private UserService userService;
    private PasswordUtils passwordUtils = new PasswordUtils();
    private UserSessionSingleton session;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<?extends ApiResponse> post(@RequestBody User user) {
        user.setPassword(this.passwordUtils.encodePassword(user.getPassword()));
        userService.save(user);
        this.passwordUtils.cleanUser(user);
        System.out.println();
        ResponseEntity<?extends ApiResponse> responseEntity = new ResponseEntity<> (new ApiResponse("", true, user), HttpStatus.OK, user);
        System.out.println(responseEntity);
        return responseEntity;
    }

    @PostMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<?extends  ApiResponse> verifyLoginStatus(@RequestBody User userToValidate){
        User storedUser = userService.retrieve(userToValidate.getUsername());
        boolean loginStatus = this.passwordUtils.checkMatch(storedUser.getPassword(), userToValidate.getPassword());
        String message = "";

        if (loginStatus) {
            session = UserSessionSingleton.getInstance(storedUser.getUsername());
            message = "User was successfully authenticated.";
        } else {
            message = "User couldn't be authenticated.";
        }

        return ResponseEntity.ok(new ApiResponse(message, loginStatus, new Object()));
    }

    @GetMapping (value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<?extends  ApiResponse> getLoginStatus(@PathVariable String username){
        User user = userService.retrieve(username);
        this.passwordUtils.cleanUser(user);
        return ResponseEntity.ok(new ApiResponse("", true, user));
    }

    @GetMapping (value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ResponseEntity<?extends  ApiResponse> getLoggedUser(){
        return ResponseEntity.ok(new ApiResponse(session.username.equals("") ? "Welcome back!" : "Session has expired", session.username.equals(""), session.username));
    }
}
