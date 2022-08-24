package com.theverygroup.lf.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.theverygroup.lf.steps.GlobalContext;

public class DBoperations {

	private Connection connection;
	ResultSet resultSet = null;
	
	public Connection connectToFIMdb() throws SQLException {
		String DB_DRIVER = "jdbc:postgresql://pipe3-fim-aurora-cluster.cluster-c7moiww9yarz.eu-west-1.rds.amazonaws.com:5432/pipe3FulfilmentInventoryManager";
		String DB_USER = GlobalContext.getCredentials("pipe3.fim-db.user");
		String DB_PASS = GlobalContext.getCredentials("pipe3.fim-db.password");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(
	        		DB_DRIVER, DB_USER, DB_PASS);
			System.out.println("Connected to FIM DB...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connection;
	}
	public void exeQuery(String SQLquery) throws SQLException {
		
		if (connection != null) {
        	Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
        		    ResultSet.CONCUR_READ_ONLY);
		    resultSet = statement.executeQuery(SQLquery);
        }
        else           
		    System.out.println("Not Connected to FIM DB..");
	}
	
	public String valueExistinResultSet(String ExpectedColumnID, String ExpectedColumnValue) throws SQLException {
		String ActualColumnValue=null;
		resultSet.beforeFirst();
		while(resultSet.next()) {
			ActualColumnValue = resultSet.getString(ExpectedColumnID);
			if(ActualColumnValue.equals(ExpectedColumnValue) ) {
				return ActualColumnValue;
			}
		}
		return ActualColumnValue;
	}


}
