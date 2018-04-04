package kaisheng.project.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import kaisheng.project.ao.SaleChanceDao;
import kaisheng.project.entitys.Customer;
import kaisheng.project.entitys.SaleChance;
import kaisheng.project.utils.Page;

public class SaleChanceService {
	
	SaleChanceDao dao = new SaleChanceDao();
	CustomerService custometService = new 	CustomerService();
	
	public void saveSaleChance(String name, int custId, float worth, String process, String content, int accountId) {
		
		SaleChance saleChance = new SaleChance();
		
		saleChance.setAccountId(accountId);
		saleChance.setContent(content);
		saleChance.setCreateTime(new Timestamp(System.currentTimeMillis()));
		saleChance.setCustId(custId);
		saleChance.setLastTime(saleChance.getCreateTime());
		saleChance.setName(name);
		saleChance.setProcess(process);
		saleChance.setWorth(worth);
		
		dao.saveSaleChance(saleChance);
	}

	public Page<SaleChance> findByPage(int pageNum, int accountId) {
		
		int allNum = dao.getSaleChanceCountByAccountId(accountId);
		
		Page<SaleChance> page = new Page<>(allNum, pageNum);
		List<SaleChance> lists = dao.findSaleChanceByPage(page.getStart(),page.getPageSize(),accountId);
		for(int i = 0;i < lists.size();i++) {
			int cusId = lists.get(i).getCustId();
			Customer cust = custometService.findCustById(cusId);
			lists.get(i).setCustomer(cust);
		}
		
		page.setItems(lists);
		
		return page;
	}

	public SaleChance findSaleChanceById(int saleID) {
		Customer cust = custometService.findCustById(dao.findSaleChanceById(saleID).getCustId());
		SaleChance saleChance = dao.findSaleChanceById(saleID);
		saleChance.setCustomer(cust);
		return saleChance;
	}

	public SaleChance UpdateProcessById(int saleId,String process) {
		Customer cust = custometService.findCustById(dao.findSaleChanceById(saleId).getCustId());
		dao.UpdateProcessById(saleId,process);
		SaleChance salechance= findSaleChanceById(saleId);
		salechance.setCustomer(cust);
		return salechance;
	}

	public void delSaleChanceById(int saleId) {
		dao.delSaleChanceById(saleId);
	}

	public List<Map<String, Object>> getSaleChanceCountAndschedule() {
		return dao.getSaleChanceCountAndschedule();
	}

	
}
