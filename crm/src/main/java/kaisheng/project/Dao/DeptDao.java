package kaisheng.project.ao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import kaisheng.project.entitys.Dept;
import kaisheng.project.utils.JdbcHelp;

public class DeptDao {

	public List<Dept> findAll() {
		String sql = "select * from t_dept";
	    return JdbcHelp.query(sql, new BeanListHandler<>(Dept.class));
	}

	public Dept findByName(String deptName) {
		String sql = "select * from t_dept where deptname = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Dept.class), deptName);
	}

	public void saveDept(Dept dept) {
		String sql = "insert into t_dept (pId,deptname) value (?,?)";
		JdbcHelp.executeUpdate(sql, dept.getpId(),dept.getDeptName());
	}

	public List<Dept> findAllChildDept() {
		String sql = "select * from t_dept where pid = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Dept.class), 1);
	}

}
