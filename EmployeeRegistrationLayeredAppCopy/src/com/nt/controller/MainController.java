package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeMgmtService;
import com.nt.service.EmployeeMgmtServiceImpl;
import com.nt.vo.EmployeeVO;

public class MainController extends HttpServlet {
	private EmployeeMgmtService service;
	
	@Override
	public void init(){
		service=new EmployeeMgmtServiceImpl();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=null;
		String eName=null,addrs=null,doj=null,basicSal=null,result=null;
		EmployeeVO vo=null;
		EmployeeDTO dto=null;
		SimpleDateFormat sfdoj=null;
		java.util.Date udoj=null;
		java.sql.Date sqdoj=null;
		long ms=0;
		
		//get printWriter object
		out=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//read form data and store into VO class object
		eName=req.getParameter("ename");
		addrs=req.getParameter("eadd");
		doj=req.getParameter("doj");
		basicSal=req.getParameter("bsal");
		
		vo=new EmployeeVO();
		vo.seteName(eName);
		vo.setAddrs(addrs);
		vo.setDoj(doj);
		vo.setBasicSal(basicSal);
		
	try {	
		//all values convert VO object to DTO object
		dto=new EmployeeDTO();
		dto.seteName(vo.geteName());
		dto.setAddrs(vo.getAddrs());
			
		//convert the form date into db date format
		sfdoj=new SimpleDateFormat("MM-dd-yyyy");
		if(sfdoj!=null)
			udoj=sfdoj.parse(vo.getDoj());
			
		if(udoj!=null)
			ms=udoj.getTime();
			sqdoj=new java.sql.Date(ms);	
		dto.setDoj(sqdoj);	
		//convert basicSal into double 
		dto.setBasicSal(Double.parseDouble(vo.getBasicSal()));
		
		result=service.register(dto);
		out.println("<h1 style='text-align:center'>"+result+"</h1>");
		out.println("<br><br><a href='EmployeeRegister.html'>Home</a>");
	}
		catch(ParseException pe) {
			pe.printStackTrace();
		}
		catch(Exception e) {
			out.println("Internal Problem");
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
