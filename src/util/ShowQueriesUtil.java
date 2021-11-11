package util;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.*;

import db.DBTasks;

public class ShowQueriesUtil {

    public static PreparedStatement stmt;
    public static ResultSet rs;

    public static String executeQuery1(String brandUserName)
    {
        try
        {
            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_1_6(?,?,?,?)}");

            stmt.setString(1,"1");
            stmt.setString(2,brandUserName);

            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.registerOutParameter(4  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(3);

            return result;

        }
        catch(Exception e)
        {

        }
        return null;
    }

    public static String executeQuery2()
    {
        try
        {
            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_2(?,?,?)}");

            stmt.setString(1,"2");

            stmt.registerOutParameter(2  , Types.VARCHAR);
            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(2);

            return result;
        }
        catch(Exception e)
        {

        }
        return null;

    }

    public static String executeQuery3(String customerUserName)
    {
        try{

            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_3(?,?,?,?)}");

            stmt.setString(1,"3");
            stmt.setString(2,customerUserName);

            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.registerOutParameter(4  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(3);

            return result;

        }
        catch(Exception e)
        {
            System.out.println(e.toString()+" "+e.getMessage());
        }
        return null;
    }

    public static String executeQuery4(String activityCode)
    {
        try{

            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_4(?,?,?,?)}");

            stmt.setString(1,"4");
            stmt.setString(2,activityCode);

            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.registerOutParameter(4  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(3);

            return result;

        }
        catch(Exception e)
        {
            System.out.println(e.toString()+" "+e.getMessage());
        }
        return null;
    }

    public static String executeQuery5(String brandUserName)
    {
        try{

            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_5(?,?,?,?)}");

            stmt.setString(1,"5");
            stmt.setString(2,brandUserName);

            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.registerOutParameter(4  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(3);

            return result;

        }
        catch(Exception e)
        {
            System.out.println(e.toString()+" "+e.getMessage());
        }
        return null;
    }

    public static String executeQuery6(String brandUserName)
    {
        try{

            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_1_6(?,?,?,?)}");

            stmt.setString(1,"6");
            stmt.setString(2,"tiktok");

            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.registerOutParameter(4  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(3);

            return result;

        }
        catch(Exception e)
        {
            System.out.println(e.toString()+" "+e.getMessage());
        }
        return null;
    }

    public static String executeQuery7(int pointsThreshold)
    {
        try{

            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_7(?,?,?,?)}");

            stmt.setString(1,"7");
            stmt.setInt(2,pointsThreshold);

            stmt.registerOutParameter(3  , Types.VARCHAR);
            stmt.registerOutParameter(4  , Types.VARCHAR);
            stmt.executeQuery();

            String result = stmt.getString(3);

            return result;

        }
        catch(Exception e)
        {
            System.out.println(e.toString()+" "+e.getMessage());
        }
        return null;
    }

    public static void executeQuery8(String brandName, String customerName, String startDate, String endDate)
    {
        try{

            CallableStatement stmt = DBTasks.conn.prepareCall(
                    "{call SHOW_QUERIES_PROCEDURES.GET_RESULTS_FOR_OPTION_8(?,?,?,?,?,?,?,?)}");

            stmt.setString(1,"8");
            stmt.setString(2,brandName);
            stmt.setString(3,customerName);
            stmt.setString(4,startDate);
            stmt.setString(5,endDate);

            stmt.registerOutParameter(6  , Types.VARCHAR);
            stmt.registerOutParameter(7  , Types.VARCHAR);
            stmt.registerOutParameter(8 , Types.INTEGER);
            stmt.executeQuery();

            System.out.println("No of activities:  "+ stmt.getInt(8));

        }
        catch(Exception e)
        {
            System.out.println(e.toString()+" "+e.getMessage());
        }
        return;
    }

    public static void printQueryResult(String result)
    {
        if(result == null || result.length() == 0) return;

        String[] arr = result.split("#");

        List<String[]> list = getList(arr);

        System.out.println(getDottedLines(list.get(0).length));

        System.out.println(getFormatted(list.get(0)));

        System.out.println(getDottedLines(list.get(0).length));

        for(int i=1; i<list.size(); i++)
        {
            String val = getFormatted(list.get(i));

            System.out.println(val);
        }

        System.out.println(getDottedLines(list.get(0).length));

        return;
    }

    public static List<String[]> getList(String[] arr)
    {
        List<String[]> list = new ArrayList<>();

        Set<String> set = new HashSet<>();

        for(int i=0; i<arr.length; i++)
        {
            if(!set.contains(arr[i])) {

                String[] str = arr[i].split("\\|");

                set.add(arr[i]);

                list.add(str);
            }
        }

        return list;
    }

    public static String getDottedLines(int len)
    {
        StringBuffer sbuf = new StringBuffer("");
        for(int i=0; i< len * 25; i++)
        {
            sbuf.append("-");
        }
        return sbuf.toString();
    }

    public static String getFormatted(String[] str)
    {
        StringBuffer sbuf = new StringBuffer("");

        for(int i=0; i<str.length; i++)
        {
            sbuf.append(str[i]);
            for(int j=str[i].length(); j<25; j++)
            {
                sbuf.append(" ");
            }
        }

        return sbuf.toString();
    }
}
