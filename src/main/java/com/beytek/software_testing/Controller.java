package com.beytek.software_testing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class Controller {

    private final CommentService commentService;

    public Controller(CommentService commentService) {
        this.commentService = commentService;
//        commentService.addTestComments();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/comments/test")
    public ResponseEntity<List<Comment>> getAllTestComments() {
        return new ResponseEntity<>(commentService.getAllTestComments(), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id) {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Optional<Comment>> getCommentWithId(@PathVariable String id) {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @GetMapping("/commentsOf/{commenter}")
    public ResponseEntity<List<Comment>> getAllCommentsOf(@PathVariable String commenter) {
        return new ResponseEntity<>(commentService.findCommentsByCommenter(commenter), HttpStatus.OK);
    }

}
