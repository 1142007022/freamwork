package kaisheng.project.ao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import kaisheng.project.entitys.SaleChance;
import kaisheng.project.utils.JdbcHelp;

public class SaleChanceDao {

	public void saveSaleChance(SaleChance saleChance) {
		String sql = "insert into t_sale_chance (name,custId,worth,process,content,createTime,lastTime,accountId) value(?,?,?,?,?,?,?,?)";
		JdbcHelp.executeUpdate(sql, saleChance.getName(),saleChance.getCustId(),saleChance.getWorth(),saleChance.getProcess(),saleChance.getContent(),saleChance.getCreateTime(),saleChance.getLastTime(),saleChance.getAccountId());
	}

	public int getSaleChanceCountByAccountId(int accountId) {
		String sql = "select count(*) from t_sale_chance where accountId = ?";
		return JdbcHelp.query(sql, new ScalarHandler<Long>(), accountId).intValue();
	}

	public List<SaleChance> findSaleChanceByPage(int start, int pageSize, int accountId) {
		String sql = "select * from t_sale_chance where accountId = ? limit ?,?";
		return JdbcHelp.query(sql, new BeanListHandler<>(SaleChance.class), accountId,start,pageSize);
	}

	public SaleChance findSaleChanceById(int saleID) {
		String sql = "select * from t_sale_chance where id = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(SaleChance.class), saleID);
	}

	public void UpdateProcessById(int saleId,String process) {
		String sql = "update t_sale_chance set process = ? where id = ?";
		JdbcHelp.executeUpdate(sql, process, saleId);
	}

	public void delSaleChanceById(int saleId) {
		String sql = "delete  from t_sale_chance where id = ?";
		JdbcHelp.executeUpdate(sql, saleId);
	}

	public List<Map<String, Object>> getSaleChanceCountAndschedule() {
		String sql = "select count(*) as count ,process from t_sale_chance group by process";
		return JdbcHelp.query(sql, new ResultSetHandler<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> handle(ResultSet rs) throws SQLException {
				List<Map<String,Object>> lists = new ArrayList<>();
				while(rs.next()){
					Map<String,Object> map = new HashMap<>();
					map.put("count", rs.getInt("count"));
					map.put("process", rs.getString("process"));
					lists.add(map);
				}
				return lists;
			}
		});
	}

}
