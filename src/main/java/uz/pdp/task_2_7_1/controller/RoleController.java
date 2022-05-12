package uz.pdp.task_2_7_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_7_1.aop.CheckPermission;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.RoleDto;
import uz.pdp.task_2_7_1.payload.UserDto;
import uz.pdp.task_2_7_1.service.RoleService;
import uz.pdp.task_2_7_1.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> AddRole(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @CheckPermission(value = "EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id,@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.editRole(id,roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(value = "VIEW_ROLES")
    @GetMapping
    public HttpEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @CheckPermission(value = "VIEW_ROLES")
    @GetMapping("/{id}")
    public HttpEntity<?> getRoleById(@PathVariable Long id){
        ApiResponse apiResponse = roleService.getRoleById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @CheckPermission(value = "DELETE_ROLE")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id){
        ApiResponse apiResponse = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 409:404).body(apiResponse);
    }
}
