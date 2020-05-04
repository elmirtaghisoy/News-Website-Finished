package kadr25.model;

import java.util.Date;

public class Comment {
    private String username;
    private String user_comment;
    private Date comment_day;
    private Integer active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public Date getComment_day() {
        return comment_day;
    }

    public void setComment_day(Date comment_day) {
        this.comment_day = comment_day;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
