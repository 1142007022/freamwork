package kaisheng.project.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kaisheng.project.ao.DeptDao;
import kaisheng.project.entitys.Dept;
import kaisheng.project.exception.ServiceException;

public class DeptService {
	
	DeptDao dao = new DeptDao();
	
	public List<Dept> findAll() {
		return dao.findAll();
	}

	public Dept addDept(String deptName) {
		
		if(StringUtils.isEmpty(deptName)) {
			throw new ServiceException("部门名称不能为空!");
		}
		
		Dept dept =  dao.findByName(deptName);
		if(dept == null) {
			dept = new Dept();
			dept.setpId(1);
			dept.setDeptName(deptName);
			dao.saveDept(dept);
			return dept;
		}else {
			throw new ServiceException("部门名已存在!");
		}
	}

	public List<Dept> findAllChildDept() {
		return dao.findAllChildDept();
	}

}
