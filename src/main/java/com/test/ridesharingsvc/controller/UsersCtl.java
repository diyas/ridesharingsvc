package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.Paginate;
import com.test.ridesharingsvc.model.payload.RegisterResponse;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UsersCtl {

    @Autowired
    HttpServletResponse httpResponse;

    @Autowired
    UsersRepo users;

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
        HttpStatus status;
        if (users.existsByUserName(user.getUserName())){
            status = HttpStatus.BAD_REQUEST;
            Response resp = new Response();
            resp.setCode(status.value());
            resp.setMessage("UserID sudah digunakan!");
            return new ResponseEntity(resp, status);
        }
        //user.setPassword(new en(user.getPassword()));
        status = HttpStatus.OK;
        User result = users.save(user);
        Response resp = new Response();
        resp.setCode(status.value());
        resp.setMessage("");
        resp.setData(new RegisterResponse(result.getUserId()));
        return new ResponseEntity(resp, status);
    }

    @PutMapping("/users/{id}")
    public Response updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody User user){
        User result = users.findById(id).orElseThrow(()-> new NotFound("Not Found"));
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setStsUsr(user.getStsUsr());
        User updateUser = users.save(result);
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(updateUser);
        return resp;
    }

    @PutMapping("/users/{userid}/{status}")
    public Response updateStatusUser(@PathVariable(value = "userid") Long userId, @PathVariable(value = "status") String status){
        User result = users.findByUserId(userId);
        result.setStsUsr(status);
        User updateUser = users.save(result);
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(updateUser);
        return resp;
    }

    @GetMapping("/users")
    public Response getAllUsers(@RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(value = "page", defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<User> result = users.findAll(pageable);
        long totalData = result.getTotalElements();
        long totalPage = totalData / 10;
        Paginate paginate = new Paginate();
        paginate.setLimit(limit);
        paginate.setPage(page);
        paginate.setTotalData(totalData);
        paginate.setTotalPage(totalPage);
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(result.getContent());
        resp.setPaginate(paginate);
        return resp;
    }

    @GetMapping("/users/{username}")
    public Response getUserByUserId(@PathVariable(value = "username") String userName){
        User result = users.findByUserName(userName).orElseThrow(()-> new NotFound("Not Found"));
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(result);
        return resp;
    }

    @DeleteMapping("/users/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userid") String userId){
        User result = users.findByUserName(userId).orElseThrow(()-> new NotFound("Not Found"));
        users.delete(result);
        HttpStatus status = HttpStatus.OK;
        Response resp = new Response();
        resp.setCode(status.value());
        resp.setMessage("Deleted UserID " + result.getUserId());
        resp.setData(result);
        return new ResponseEntity(resp, status);
    }
}
