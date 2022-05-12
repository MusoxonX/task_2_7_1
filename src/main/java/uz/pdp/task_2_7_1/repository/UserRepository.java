package uz.pdp.task_2_7_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_7_1.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
