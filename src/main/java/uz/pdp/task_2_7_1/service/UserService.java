package uz.pdp.task_2_7_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_7_1.entity.Role;
import uz.pdp.task_2_7_1.entity.User;
import uz.pdp.task_2_7_1.exception.ResourceNotFoundException;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.UserDto;
import uz.pdp.task_2_7_1.repository.RoleRepository;
import uz.pdp.task_2_7_1.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addUser(UserDto userDto){
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()){
            return  new ApiResponse("user already exists",false);
        }
        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user = new User(userDto.getFullName(),userDto.getUsername(),userDto.getPassword(),role,true);
        userRepository.save(user);
        return new ApiResponse("User saved: ",true,user);
    }

    public ApiResponse editUser(Long id,UserDto userDto) {
        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(role);
        userRepository.save(user);
        return new ApiResponse("User edit",true,user);
    }

    public ApiResponse deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
        userRepository.deleteById(id);
        return new ApiResponse("User deleted",true);
    }

    public ApiResponse getAllUser() {
        return new ApiResponse("Users List: ",true,userRepository.findAll());
    }

    public ApiResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
        return new ApiResponse("User: ",true,user);
    }
}
