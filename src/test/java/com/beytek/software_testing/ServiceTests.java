package com.beytek.software_testing;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTests {

    @InjectMocks
    private CommentService targetService;

    @Mock
    private CommentRepository repository;

    @Test
    public void getAllTestCommentsReturnsOnlyTestComments() {

        //arrange
        List<Comment> allComments = new ArrayList<>();
        Comment comment = new Comment("abc", "integrationTest", new Date());
        Comment testComment = new Comment("test comment", "integrationTest", new Date());
        allComments.add(comment);
        allComments.add(testComment);

        when(repository.findAll()).thenReturn(allComments);

        //act
        List<Comment> actual = targetService.getAllTestComments();
        System.out.println(actual);

        //assert
        verify(repository).findAll();
        assertTrue(!actual.isEmpty());
    }

    @Test
    public void addTestCommentReturnsComment() {
        //arrange
        when(repository.save(any(Comment.class))).thenReturn(new Comment());
        //act
        targetService.addTestComments();

        //assert
        verify(repository).deleteAll();
        verify(repository,times(5)).save(any());

    }

}
