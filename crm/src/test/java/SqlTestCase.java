import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;

public class SqlTestCase {

    @Test
    public void changeTest(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection collection = DriverManager.getConnection("jdbc:mysql:///test","root","625255");
            String sql = "{call changePrice(?,?)}";
            CallableStatement callableStatement = collection.prepareCall(sql);
            callableStatement.setString("pid","ANV01");
            callableStatement.setFloat("price",10.66F);
            callableStatement.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void countTest(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///test","root","625255");
            String sql = "{call getCount(?,?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setFloat("price",10F);
            callableStatement.registerOutParameter("count",2);
            callableStatement.execute();
            int count = callableStatement.getInt("count");
            System.out.println(count);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
