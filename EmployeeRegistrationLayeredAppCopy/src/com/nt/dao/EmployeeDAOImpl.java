package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.EmployeeBO;

public class EmployeeDAOImpl implements EmployeeDAO {
		private static final String INSERT_EMPLOYEE_QUERY="INSERT INTO LAYERED_EMPLOYEE VALUES(EMPNO_SEQ.NEXTVAL,?,?,?,?,?,?)";

	private Connection getPooledConection()throws Exception{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		
		//create initial context object
		ic=new InitialContext();
		//get dataSource obj
		ds=(DataSource)ic.lookup("DBJndi");
		//get connection object
		con=ds.getConnection();
		
		return con;
	}
	@Override
	public int insert(EmployeeBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		
		//get pooledConnection Object
		con=getPooledConection();
		//get preparedStatement object
		ps=con.prepareStatement(INSERT_EMPLOYEE_QUERY);
		//set query parameter
		ps.setString(1,bo.geteName());
		ps.setString(2,bo.getAddrs());
		ps.setDate(3,bo.getDoj());
		ps.setDouble(4,bo.getBasicSal());
		ps.setDouble(5,bo.getGrossSal());
		ps.setDouble(6,bo.getNetSal());
		//	process the result 
		count=ps.executeUpdate();
		
		ps.close();
		con.close();
		
		return count;
	}

}
