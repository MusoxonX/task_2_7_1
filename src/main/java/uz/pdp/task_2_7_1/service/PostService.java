package uz.pdp.task_2_7_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_7_1.entity.Post;
import uz.pdp.task_2_7_1.exception.ResourceNotFoundException;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.PostDto;
import uz.pdp.task_2_7_1.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDto postDto) {
        Post post = new Post(postDto.getTitle(),postDto.getText(),postDto.getUrl());
        Post savedPost = postRepository.save(post);
        return new ApiResponse("post added",true,savedPost);
    }

    public ApiResponse editPost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post Edited",true,post);
    }

    public ApiResponse getAllPost() {
        List<Post> all = postRepository.findAll();
        return new ApiResponse("Post list",true,all);
    }

    public ApiResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post Id =" + id + " is not found"));
        return new ApiResponse("Post: ",true,post);
    }

    public ApiResponse deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post Id="+id+" is not found"));
        postRepository.deleteById(id);
        return new ApiResponse("Post deleted",true);
    }
}
