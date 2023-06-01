package classes;
import java.util.*;
public class IssueBean {

    public IssueBean(){
        issueStatus = "";
        reporter = "";
        fixer = "";
        title = "";
        issueDescript = "";
        resolution = "";
        issueId = 0;
    }
    private String issueStatus;
    private String reporter;
    private String fixer;
    private String title;
    private String issueDescript;
    private String resolution;
    private Date dateTimeReport;
    private Date dateTimeResolved;
    private int issueId;

    private String issueCategory;

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    private List<CommentBean> comments;
    //getter methods
    public String getIssueStatus() {
        return issueStatus;
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
    public String getResolution() { return resolution; }
    public String getReporter(){ return reporter; }
    public String getFixer(){ return fixer; }
    public Date getDateTimeReport(){ return dateTimeReport; }
    public Date getDateTimeResolved(){ return dateTimeResolved; }
    public int getIssueId(){ return issueId; }

    //setter methods
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }
    public void setIssueId(int issueId){ this.issueId = issueId; }
    public void setReporter(String reporter){ this.reporter = reporter; }
    public void setFixer(String fixer){this.fixer = fixer; }
    public void setIssueDescript(String issueDescript) {
        this.issueDescript = issueDescript;
    }


    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

}
