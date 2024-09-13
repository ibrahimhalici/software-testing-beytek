package com.beytek.software_testing;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> findById(String id);

    List<Comment> findCommentByCommenter(String commenter);

}
