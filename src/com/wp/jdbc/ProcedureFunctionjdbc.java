package com.wp.jdbc;

public class ProcedureFunctionjdbc 
{
	public static void main(String args[]) throws Exception 
	{
		
		Oracleconnection.ConnectDB();//coonnectivity of procedures
		java.sql.CallableStatement cs = Oracleconnection.getConn()
				.prepareCall("{call Insert_Into_Hospital_Systems(?,?,?,?,?)}");
		cs.setInt(1, 100);
		cs.setString(2, "apollo");
		cs.setString(3, "heart specialist");
		cs.setString(4, "in Indore");
		cs.setString(5, "www.apollo.com");
		cs.execute();
		System.out.println("executed");
		cs.close();
		
//Function using jdbc
		java.sql.CallableStatement cs1 = Oracleconnection.getConn().prepareCall("{?= call checkForPalindrome(?)}");
		cs1.setString(2, "ama");
		cs1.registerOutParameter(1, java.sql.Types.VARCHAR);
		cs1.execute();
		String output = cs1.getString(1);
		System.out.println(output);
		cs1.close();
		Oracleconnection.close();
	}

}
