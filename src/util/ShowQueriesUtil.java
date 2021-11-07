package util;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.*;

import db.DBTasks;

public class ShowQueriesUtil {

    public static PreparedStatement stmt;
    public static ResultSet rs;

    public static List<String[]> executeQuery1()
    {
        try
        {
            String query = "select  \n"
                    + "        c.customer_id, c.name, c.address, c.contact_number \n"
                    + "from \n"
                    + "        customers c\n"
                    + "minus\n"
                    + "select \n"
                    + "        c.customer_id, c.name, c.address, c.contact_number\n"
                    + "from \n"
                    + "        customers_to_brands cb, customers c, brands b\n"
                    + "where \n"
                    + "        cb.customer_id = c.customer_id\n"
                    + "        and b.name = 'BRAND02'\n"
                    + "        and b.brand_id = cb.brand_id";


            stmt = DBTasks.conn.prepareStatement(query);
            rs = stmt.executeQuery();

            List<String[]> result = new ArrayList<>();

            String[] columns = new String[4];
            columns[0] = "customer_id"; columns[1] = "customer_name";
            columns[2] = "address"; columns[3] = "contact_number";

            result.add(columns);

            if(rs == null || !rs.next()) return result;

            do
            {
                String[] values = new String[4];
                values[0] = String.valueOf(rs.getInt(1));
                values[1] = rs.getString(2);
                values[2] = rs.getString(3);
                values[3] = String.valueOf(rs.getInt(4));

                result.add(values);
            }while(rs.next());

            return result;

        }
        catch(Exception e)
        {
            return null;

        }

    }

    public static List<String[]> executeQuery2()
    {
        try
        {
            String query = "SELECT\n"
                    + "    cb.customer_id        AS customerid,\n"
                    + "    lp.loyalty_program_id AS loyaltyprogramid\n"
                    + "FROM\n"
                    + "    customers_to_brands      cb,\n"
                    + "    regular_loyalty_programs lp\n"
                    + "WHERE\n"
                    + "        cb.brand_id = lp.brand_id\n"
                    + "        AND cb.customer_id NOT IN (\n"
                    + "            SELECT\n"
                    + "                cblpa.customer_id\n"
                    + "            FROM\n"
                    + "                customers_to_blp_activities cblpa\n"
                    + "            where \n"
                    + "                cblpa.brand_id = lp.loyalty_program_id\n"
                    + "        )";


            stmt = DBTasks.conn.prepareStatement(query);
            rs = stmt.executeQuery();

            List<String[]> result = new ArrayList<>();

            String[] columns = new String[2];
            columns[0] = "customer_id"; columns[1] = "loyalty_program_id";

            result.add(columns);

            if(rs == null || !rs.next()) return result;

            do
            {
                String[] values = new String[4];
                values[0] = String.valueOf(rs.getInt(1));
                values[1] = String.valueOf(rs.getInt(2));

                result.add(values);
            }while(rs.next());

            return result;

        }
        catch(Exception e)
        {
            return null;

        }
    }

    public static List<String[]> executeQuery3()
    {
        return null;
    }

    public static List<String[]> executeQuery4()
    {
        return null;
    }

    public static List<String[]> executeQuery5()
    {
        return null;
    }

    public static List<String[]> executeQuery6()
    {
        return null;
    }

    public static List<String[]> executeQuery7()
    {
        return null;
    }

    public static List<String[]> executeQuery8()
    {
        return null;
    }

    public static void printQueryResult(List<String[]> result)
    {
        if(result == null || result.size() == 0) return;

        String formattedColumns = getFormatted(result.get(0));

        System.out.println(formattedColumns);

        System.out.println(getDottedLines(result.get(0).length));

        for(int i=1; i<result.size(); i++)
        {
            System.out.println(getFormatted(result.get(i)));
        }

        return;
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
