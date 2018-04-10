package kaisheng.project.ao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import kaisheng.project.entitys.SaleChanceRecord;
import kaisheng.project.utils.JdbcHelp;

public class SaleChanceRecordDao {

	public void saveRecord(SaleChanceRecord saleChanceRecord) {
		String sql = "insert into t_sale_chance_record (content,saleId,createTime) value (?,?,?)";
		JdbcHelp.executeUpdate(sql, saleChanceRecord.getContent(),saleChanceRecord.getSaleId(),saleChanceRecord.getCreateTime());
	}

	public List<SaleChanceRecord> findRecordById(int saleId) {
		String sql = "select * from t_sale_chance_record where saleId = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(SaleChanceRecord.class), saleId);
	}

}
