package kaisheng.project.ao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import kaisheng.project.entitys.Customer;
import kaisheng.project.utils.JdbcHelp;

public class CustomerDao {

	public Customer findByMobile(String mobile) {
		String sql = "select * from t_customer where mobile = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Customer.class), mobile);
	}

	public void save(Customer customer) {
		String sql = "insert into t_customer (name,mobile,sex,address,job,trade,source,level,mark,accountId,createTime,lastConcatTime,updateTime,reminder) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	//	System.out.println(customer.getLevel()+"------level");
		JdbcHelp.executeUpdate(sql, customer.getName(),customer.getMobile(),customer.getSex(),customer.getAddress(),customer.getJob(),customer.getTrade(),customer.getSource(),customer.getLevel(),customer.getMark(),customer.getAccountId(),customer.getCreateTime(),customer.getLastConcatTime(),customer.getUpdateTime(),customer.getReminder());
	}

	public List<Customer> findCustByAccountId(int accountId) {
		String sql = "select * from t_customer where accountId = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Customer.class), accountId);
	}

	public List<Customer> findByPage(int accountId, int start, int pageSize) {
		String sql = "select * from t_customer where accountId = ? limit ?,?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Customer.class),accountId, start,pageSize);
	}

	public Customer findCustByid(int id) {
		String sql = "select *from t_customer where id = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Customer.class), id);
	}

	public void changeCustomerToOthers(int id, int accountId,Timestamp updateTime,String reminder) {
		String sql = "update t_customer set accountId = ?,updateTime = ?,reminder = ? where id = ?";
		JdbcHelp.executeUpdate(sql, accountId,updateTime,reminder,id);
	}

	public void delCustById(int id) {
		String sql = "delete from t_customer where id = ?";
		JdbcHelp.executeUpdate(sql, id);
	}

	public void changCustToPublicById(int id) {
		String sql = "update t_customer set accountId = 0 where id = ?";
		JdbcHelp.executeUpdate(sql, id);
	}

	public void edit(String custId, String name, String sex, String job, String address, String mobile,String source, String trade, String level, String mark) {
		String sql = "update t_customer set name = ?,sex = ?,job = ?,address = ?,mobile = ?,source = ?,trade = ?,level = ?,mark = ? where id = ?";
		JdbcHelp.executeUpdate(sql, name,sex,job,address,mobile,source,trade,level,mark,custId);
		
	}

	public List<Customer> findAllCustByAccId() {
		String sql = "select * from t_customer where accountId = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Customer.class),0);
	}

	public List<Map<String, Object>> getLeavelAndCount() {
		String sql = "select count(*) as count,level from t_customer group by level";
		return JdbcHelp.query(sql, new ResultSetHandler<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> handle(ResultSet rs) throws SQLException {
				List<Map<String,Object>> lists = new ArrayList<>();
				while(rs.next()){
					Map<String, Object> maps = new HashMap<>();
					maps.put("count", rs.getInt("count"));
					maps.put("level", rs.getString("level"));
					lists.add(maps);
				}
				return lists;
			}
		});
	}

}
