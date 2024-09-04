package edu.example.util;

import edu.example.dbConnection.DBConnection;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String SQl,Object... val) throws SQLException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(SQl);
        if(SQl.startsWith("SELECT") || SQl.startsWith("select")){
            return (T) pstm.executeQuery();
        }else{
            for (int i=0 ; i < val.length ; i++){
                pstm.setObject(i+1,val[i]);
            }
            return (T) (Boolean) (pstm.executeUpdate()>0);
        }
    }
}


