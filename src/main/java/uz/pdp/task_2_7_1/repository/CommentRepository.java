package uz.pdp.task_2_7_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_7_1.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long post_id);
}
