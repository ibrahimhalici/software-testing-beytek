package com.beytek.software_testing;


import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public void addTestComments() {
        repository.deleteAll();
        Comment comment;
        for (int i = 1; i <= 5; i++) {
            comment = new Comment(
                    String.valueOf(i),
                    String.format("test comment - %s", i)
                    , "program"
                    , new Date());
            repository.save(comment);
        }
    }

    public List<Comment> findCommentsByCommenter(String commenter) {
        return repository.findCommentByCommenter(commenter);
    }

    public Comment createComment(Comment comment) {
        comment.setCreatedDate(new Date());
        return repository.save(comment);
    }

    public Optional<Comment> getCommentById(String id) {
        return repository.findById(id);
    }

    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    public void deleteCommentById(String id) {
        repository.deleteById(id);
    }

    public List<Comment> getAllTestComments() {
        List<Comment> comments = repository.findAll();

        comments.removeIf(comment -> !comment.getContent().contains("test"));
        return comments;
    }
}
