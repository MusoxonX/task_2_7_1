package uz.pdp.task_2_7_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_7_1.entity.User;
import uz.pdp.task_2_7_1.exception.ResourceNotFoundException;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.LoginDto;
import uz.pdp.task_2_7_1.payload.RegisterDto;
import uz.pdp.task_2_7_1.repository.RoleRepository;
import uz.pdp.task_2_7_1.repository.UserRepository;
import uz.pdp.task_2_7_1.utils.AppConstants;

import javax.validation.Valid;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(@Valid RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())) {
            return new ApiResponse("dont match passwords", false);
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse("user already exist", false);
        }
        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role not found")),
                true
        );
        userRepository.save(user);
        return new ApiResponse("successfully registered", true);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
