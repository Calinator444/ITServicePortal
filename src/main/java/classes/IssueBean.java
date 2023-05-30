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
        issueID = 0;
    }
    private String issueStatus;
    private String reporter;
    private String fixer;
    private String title;
    private String issueDescript;
    private String resolution;
    private Date dateTimeReport;
    private Date dateTimeResolved;
    private int issueID;

    //getter methods
    public String getIssueStatus() {
        return issueStatus;
    }
    public String getTitle() {
        return title;
    }
    public String getIssueDescript() {
        return issueDescript;
    }
    public String getResolution() { return resolution; }
    public String getReporter(){ return reporter; }
    public String getFixer(){ return fixer; }
    public Date getDateTimeReport(){ return dateTimeReport; }
    public Date getDateTimeResolved(){ return dateTimeResolved; }
    public int getIssueID(){ return issueID; }

    //setter methods
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }
    public void setIssueID(int issueID){ this.issueID = issueID; }
    public void setReporter(String reporter){ this.reporter = reporter; }
    public void setFixer(String fixer){this.fixer = fixer; }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setIssueDescript(String issueDescript) {
        this.issueDescript = issueDescript;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    public void setDateTimeReport(Date dateTimeReport){ this.dateTimeReport = dateTimeReport; }
    public void setDateTimeResolved(Date dateTimeResolved){ this.dateTimeResolved = dateTimeResolved;}
}
