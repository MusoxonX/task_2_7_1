package uz.pdp.task_2_7_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_7_1.entity.Role;
import uz.pdp.task_2_7_1.exception.ResourceNotFoundException;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.RoleDto;
import uz.pdp.task_2_7_1.repository.RoleRepository;

import java.util.Optional;


@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto){
        if(roleRepository.existsByName(roleDto.getName())){
            return new ApiResponse("role already exists",false);
        }
        Role role = new Role(roleDto.getName(),roleDto.getPermissionList(),roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("role changed",true);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found for the id: " + id));
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setPermissionList(roleDto.getPermissionList());
        roleRepository.save(role);
        return new ApiResponse("Role edited",true);
    }

    public ApiResponse getAllRoles(){
        return new ApiResponse("List of Roles: ",true,roleRepository.findAll());
    }

    public ApiResponse getRoleById(Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()){
            return new ApiResponse("Role not found",false);
        }
        return new ApiResponse("Role: ", true,optionalRole.get());
    }

    public ApiResponse deleteRole(Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()){
            return new ApiResponse("role not found",false);
        }
        roleRepository.deleteById(id);
        return new ApiResponse("role deleted",true);
    }
}
