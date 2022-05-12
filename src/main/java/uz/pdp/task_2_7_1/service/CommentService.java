package uz.pdp.task_2_7_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_7_1.entity.Comment;
import uz.pdp.task_2_7_1.entity.Post;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.CommentDto;
import uz.pdp.task_2_7_1.repository.CommentRepository;
import uz.pdp.task_2_7_1.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent()){
            return new ApiResponse("post not found",false);
        }
        Comment comment = new Comment(commentDto.getText(),optionalPost.get());
        commentRepository.save(comment);
        return new ApiResponse("comment added",true,comment);
    }

    public ApiResponse editComment(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()){
            return new ApiResponse("comment not found",false);
        }
        Comment comment = optionalComment.get();
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return new ApiResponse("comment edited",true,comment);
    }

    public ApiResponse deleteComment(Long id) {
        if(!commentRepository.findById(id).isPresent()){
            return new ApiResponse("comment not found",false);
        }
        commentRepository.deleteById(id);
        return new ApiResponse("Comment deleted",true);
    }

    public ApiResponse getAllCommentById(Long id) {
        return new ApiResponse("Comment ",true,commentRepository.findAllByPostId(id));
    }

    public ApiResponse getAllComment() {
        return new ApiResponse("comment list",true,commentRepository.findAll());
    }
}
