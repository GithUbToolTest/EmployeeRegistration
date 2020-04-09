package com.nt.service;

import com.nt.bo.EmployeeBO;
import com.nt.dao.EmployeeDAO;
import com.nt.dao.EmployeeDAOImpl;
import com.nt.dto.EmployeeDTO;

public class EmployeeMgmtServiceImpl implements EmployeeMgmtService {
	private EmployeeDAO dao = null;

	public EmployeeMgmtServiceImpl() {
		dao = new EmployeeDAOImpl();
	}

	@Override
	public String register(EmployeeDTO dto) throws Exception {
		double grossSal = 0.0;
		double netSal = 0.0;
		EmployeeBO bo = null;
		int count = 0;

		// write Blogic
		grossSal = dto.getBasicSal() + (dto.getBasicSal() * 0.04);
		netSal = grossSal - (grossSal * 0.02);
		// create BO class object having persistable data
		bo = new EmployeeBO();
		bo.seteName(dto.geteName());
		bo.setAddrs(dto.getAddrs());
		bo.setDoj(dto.getDoj());
		bo.setBasicSal(dto.getBasicSal());
		bo.setGrossSal(grossSal);
		bo.setNetSal(netSal);
		// use DAO
		count = dao.insert(bo);
		// process the result
		if (count == 0)
			return "Register Failed";
		else
			return "Register Succesful";
	}

}
