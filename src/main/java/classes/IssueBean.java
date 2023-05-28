package classes;

public class IssueBean {
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

    private String issueStatus, title, issueDescript, resolution;
}
