package uz.pdp.task_2_7_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_7_1.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
