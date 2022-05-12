package uz.pdp.task_2_7_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_7_1.aop.CheckPermission;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.UserDto;
import uz.pdp.task_2_7_1.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @CheckPermission(value = "ADD_USER")
    @PostMapping()
    public HttpEntity<?> AddUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable(value = "id") Long id,@RequestBody UserDto userDto){
        ApiResponse apiResponse =userService.editUser(id,userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(value = "DELETE_USER")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable(value = "id") Long id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?409:404).body(apiResponse);
    }

    @CheckPermission(value = "VIEW_USER")
    @GetMapping
    public HttpEntity<?> getAllUser(){
        ApiResponse apiResponse = userService.getAllUser();
        return ResponseEntity.ok(apiResponse);
    }

    @CheckPermission(value = "VIEW_USER")
    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable(value = "id") Long id){
        ApiResponse apiResponse = userService.getUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
