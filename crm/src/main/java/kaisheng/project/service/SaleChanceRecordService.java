package kaisheng.project.service;

import java.sql.Timestamp;
import java.util.List;

import kaisheng.project.ao.SaleChanceRecordDao;
import kaisheng.project.entitys.SaleChanceRecord;

public class SaleChanceRecordService {
	SaleChanceRecordDao dao = new SaleChanceRecordDao();
	public void saveRecord(int saleId, String content) {
		SaleChanceRecord saleChanceRecord = new SaleChanceRecord();
		
		saleChanceRecord.setContent(content);
		saleChanceRecord.setSaleId(saleId);
		saleChanceRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		dao.saveRecord(saleChanceRecord);
	}
	public List<SaleChanceRecord> findRecordById(int saleId) {
		return dao.findRecordById(saleId);
	}

}
