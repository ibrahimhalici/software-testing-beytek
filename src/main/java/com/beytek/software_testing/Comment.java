package com.beytek.software_testing;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Comment {

    @Id
    private String id;
    private String content;
    private String commenter;
    private Date createdDate;

    public Comment() {}

    public Comment(String id, String content, String commenter, Date createdDate) {
        this.id = id;
        this.content = content;
        this.commenter = commenter;
        this.createdDate = createdDate;
    }

    public Comment(String content, String commenter, Date createdDate) {
        this.content = content;
        this.commenter = commenter;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Comment[id=%s, content='%s', commenter='%s', createdDate='%s']",
                id, content, commenter, createdDate);
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
