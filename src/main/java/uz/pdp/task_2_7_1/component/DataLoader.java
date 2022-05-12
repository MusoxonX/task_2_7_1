package uz.pdp.task_2_7_1.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.task_2_7_1.entity.Role;
import uz.pdp.task_2_7_1.entity.User;
import uz.pdp.task_2_7_1.entity.enums.Permission;
import uz.pdp.task_2_7_1.repository.RoleRepository;
import uz.pdp.task_2_7_1.repository.UserRepository;
import uz.pdp.task_2_7_1.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.task_2_7_1.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),
                    "Owner of system"
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "Simple user"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true));
        }
    }
}
