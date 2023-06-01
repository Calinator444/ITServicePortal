package classes;
import java.util.*;
import com.sun.source.doctree.CommentTree;

import javax.xml.stream.events.Comment;

public class IssueBean {


    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    private List<CommentBean> comments;

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssueDescript() {
        return issueDescript;
    }

    public void setIssueDescript(String issueDescript) {
        this.issueDescript = issueDescript;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


    public IssueBean[] getIssues() {
        return issues;
    }
    public void setIssues(IssueBean[] issues)
    {
        this.issues = issues;
    }

    private IssueBean[] issues;


    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    private int issueId;
    private String issueStatus, title, issueDescript, resolution;
}
