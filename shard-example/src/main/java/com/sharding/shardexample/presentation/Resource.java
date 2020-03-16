package com.sharding.shardexample.presentation;

import com.sharding.shardexample.model.shard.User;
import com.sharding.shardexample.model.UserRequest;
import com.sharding.shardexample.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shardTest")
public class Resource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/usersByfirstName")
    public List<User> getUsersByFirstName(@RequestParam("firstName") String firstName) {
        return service.getUser(firstName);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public void addUser(@RequestBody UserRequest userRequest) {
        service.addUser(userRequest);
    }
}
