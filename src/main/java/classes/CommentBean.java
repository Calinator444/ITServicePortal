package classes;
import java.util.*;
public class CommentBean {
    public List<ReplyBean> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyBean> replies) {
        this.replies = replies;
    }

    private List<ReplyBean> replies;


    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    private String userUsername, commentText;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    private int commentId;

}
