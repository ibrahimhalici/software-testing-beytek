package com.beytek.software_testing;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RepositoryTests extends AbstractBaseIntegration {

    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void givenCommentRepository_whenSaveAndRetrieveComment_thenOK() {
        Comment comment = new Comment("1", "test", "integration", new Date());
        Comment createdComment = commentRepository.save(comment);

        Optional<Comment> optionalComment = commentRepository.findById(createdComment.getId());
        assertThat(optionalComment.isPresent()).isTrue();

        Comment retrievedComment = optionalComment.get();
        assertThat(retrievedComment.getId()).isEqualTo(comment.getId());
    }

}
