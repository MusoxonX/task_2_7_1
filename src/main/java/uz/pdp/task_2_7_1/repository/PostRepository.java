package uz.pdp.task_2_7_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_7_1.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{
}
