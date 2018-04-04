package kaisheng.project.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.dbutils.ResultSetHandler;

import kaisheng.project.entitys.Account;

public class Screen implements ResultSetHandler<Object> {

	List<Account> lists = new ArrayList<>();
	
	@Override
	public List<Account> handle(ResultSet rs) throws SQLException {
		
		while (rs.next()) {

			Account acc = new Account();
			acc.setUsername(rs.getString("username"));
			acc.setMobile(rs.getString("mobile"));
			acc.setId(rs.getInt("id"));

			Account account = cheack(acc);

			if (account != null) {
				List<String> deptNameList = account.getDeptName();
				deptNameList.add(rs.getString("deptName"));
				account.setDeptName(deptNameList);
				/*account.setDeptName(account.getDeptName() + " " + rs.getString("deptName"));*/
				// System.out.println(rs.getString("deptName")+"-------deptname");
			} else {
				List<String> deptLists = new ArrayList<>();
				deptLists.add(rs.getString("deptName"));
				acc.setDeptName(deptLists);
				/*acc.setDeptName(rs.getString("deptName"));*/
				// System.out.println(rs.getString("deptName")+"-------else
				// deptname");
				lists.add(acc);
			}
		}

		return lists;
	}

	private Account cheack(Account acc) {
		for (Account acced : lists) {
			if (acced.equals(acc)) {
				return acced;
			}
		}
		return null;
	}

}
