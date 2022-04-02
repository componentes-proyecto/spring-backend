package com.project.springbackend.Controller;

import com.project.springbackend.Domain.User;
import com.project.springbackend.Model.Model;
import com.project.springbackend.Model.Response;
import com.project.springbackend.Service.UserService;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
    @Autowired
    UserService userService;
    private static final String entityApi = "users";

    @PostMapping(value = "/api/" + entityApi, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Model post(@RequestBody User user) {
        userService.save(user);
        return user;
    }
}
