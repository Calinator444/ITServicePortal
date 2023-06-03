package classes;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;
import java.util.*;
import java.time.*;
public class ITManagerAction extends ActionSupport implements SessionAware {



    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map)
    {
        sessionMap = (SessionMap) map;
    }
    @Override
    public String execute()
    {
        try {
            List<IssueBean> issues = DatabaseInterface.getissuesWithStatus("new");
            List<UserBean> staff = DatabaseInterface.getUsersWithRole("ITStaff");
            List<CategoryNumberBean> categoryNumber = DatabaseInterface.getCategoryNumbers();
            List<StatusNumberBean> statusNumber = DatabaseInterface.getNumberOfIssuesInEachStatus();
            List<fixerBean> fixerNumber = DatabaseInterface.getIssuesItStaff();
            List<topFiveUnresolved> topFive = DatabaseInterface.topFiveUnresolved();
            List<IssueLast30Days> issue30Days = DatabaseInterface.avgResolvedTime();
            int runningTotal = 0;
            int i = 0;
            for(IssueLast30Days avgDays : issue30Days)
            {
                runningTotal += Duration.between(avgDays.getBeginDate().toInstant(), avgDays.getEndDate().toInstant()).toDays();
                i++;
            }
            int averageOver30Days = 1;
            if(i > 0){
                 averageOver30Days = runningTotal/i;
                System.out.println(averageOver30Days);
            }else{
                 averageOver30Days = 5;
                 System.out.println(averageOver30Days);
            }

            AverageBean avg = new AverageBean();
            avg.setAverage(averageOver30Days);

            List<AverageBean> averageBeanList = new ArrayList<>();
            averageBeanList.add(avg);
            for(IssueBean issue : issues)
            {
                System.out.println(issue.getTitle());
            }
            sessionMap.put("NewIssues", issues);
            sessionMap.put("ITStaff", staff);
            sessionMap.put("Category", categoryNumber);
            sessionMap.put("status", statusNumber);
            sessionMap.put("fixer", fixerNumber);
            sessionMap.put("topFiveName", topFive);
            sessionMap.put("averageOver30Days", averageBeanList);
//            for (Map.Entry<String, Object> entry : sessionMap.entrySet()) {
//                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//           }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return SUCCESS;
    }
}
