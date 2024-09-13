package com.beytek.software_testing;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private static Comment comment;
    private static List<Comment> commentList;

    @BeforeAll
    public static void setUp() {
        commentList = new ArrayList<>();
        comment = new Comment("test", "controller testing", new Date());
        commentList.add(comment);
        comment = new Comment("test2", "controller testing", new Date());
        commentList.add(comment);
    }


    @Test
    void getAllCommentsReturnsAllCommentsAndMakesExpectedCalls() throws Exception {

        when(commentService.getAllComments()).thenReturn(commentList);
        this.mockMvc.perform(get("/api/v1/comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(comment.getContent())))
                .andExpect(content().string(containsString(comment.getCommenter())));

        verify(commentService).getAllComments();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    @DisplayName("Parametrized Deleting Operation")
    void deleteMakesTheExpectedCalls(String id) throws Exception {

        this.mockMvc.perform(delete("/api/v1/comments/" + id))
                .andExpect(status().isNoContent());

        verify(commentService,times(1)).deleteCommentById(anyString());
    }
}
