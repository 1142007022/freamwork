package kaisheng.project.dao;

import kaisheng.project.utils.JdbcHelp;

public class AccountAndDeptDao {

	public void save(int aId, int depId) {
		String sql = "insert into t_account_dept (aid,pid) value (?,?)";
		JdbcHelp.executeUpdate(sql, aId,depId);

	}
}
