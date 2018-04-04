package kaisheng.project.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import kaisheng.project.dao.AccountDao;
import kaisheng.project.ao.CustomerDao;
import kaisheng.project.entitys.Customer;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.utils.Config;
import kaisheng.project.utils.Page;


public class CustomerService {
	
	CustomerDao dao = new CustomerDao(); 
	AccountDao accDao = new AccountDao();
	
	/**
	 * @return 此处将读取顾客的来源个从事行业放在一个方法中，lists是一个大集合，每个子元素有事一个小的集合，
	 *         第一个小集合放的是顾客的来源，第二个小集合放的是顾客的从事行业(每个小集合中都是有多个值的)
	 */
	public List<List<String>> getMessgaes() {
		List<List<String>> lists = new ArrayList<List<String>>();
		lists.add(Arrays.asList(Config.get("customer.source").split(",")));
		lists.add(Arrays.asList(Config.get("customer.trade").split(",")));
		return lists;
	}

	

	public void saveCustomer(String name, String sex, String job, String mobile, String address, String trade,String source,String level,String mark, int acoountId) {
		
		Customer customer = dao.findByMobile(mobile);
		
		if(customer != null) {
			throw new ServiceException("此客户已存在请勿重复添加!");
		} else {
			
			customer = new Customer();
			
			if(acoountId != 0){
				customer.setReminder("员工添加");
			}else {
				customer.setReminder("员工添加至公海");
			}
			
			customer.setName(name);
			customer.setMobile(mobile);
			customer.setAddress(address);
			customer.setSex(sex);
			customer.setJob(job);
			customer.setTrade(trade);
			customer.setSource(source);
			customer.setLevel(level);
			customer.setMark(mark);
			customer.setAccountId(acoountId);
			customer.setCreateTime(new Timestamp(System.currentTimeMillis()));
			customer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			customer.setLastConcatTime(new Timestamp(System.currentTimeMillis()));
			
			dao.save(customer);
		}
	}

	public List<Customer> findCustNumByAccouId(int accountId) {
		return dao.findCustByAccountId(accountId);
	}
	 public Page<Customer> findByPage(int pageNum,int accountId){
		 
		 CustomerService service = new CustomerService();
		 List<Customer> lists = service.findCustNumByAccouId(accountId);//不推荐使用此种方法去获得数量  应使用Count(*)
		 int allCustNum = lists.size();
		 
		 Page<Customer> page = new Page<>(allCustNum, pageNum);
		 
		 List<Customer> custLists = dao.findByPage(accountId,page.getStart(),page.getPageSize());
		 
	/*	 for(int i = 0;i < custLists.size();i++) {
			
			 SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//等价于now.toLocaleString()
			 
			custLists.get(i).setUpdateTime(myFmt2.format(custLists.get(i).getUpdateTime()));
		 }
		 */
		 page.setItems(custLists);
	/*	 for(int i = 0;i<custLists.size();i++){
			 System.out.println("level--------->"+lists.get(i).getLevel());
		 }*/
		 return page;
	 }



	public Customer findCustById(int id) {
		Customer cust = dao.findCustByid(id);
		return cust;
	}



	public void changeCustomerToOthers(int id, int accountId, Timestamp updateTime,String reminder) {
		dao.changeCustomerToOthers(id, accountId, updateTime,reminder);
	}



	public void delCustById(int id) {
		dao.delCustById(id);
	}



	public void changCustToPublicById(int id) {
		dao.changCustToPublicById(id);
		
	}



	public void edit(String custId, String name, String sex, String job, String address, String mobile,String source, String trade, String level, String mark) {
		dao.edit(custId,name,sex,job,address,mobile,source,trade,level,mark);
	}



	public Page<Customer> finaCustByPage(int p) {
		List<Customer> lists = dao.findAllCustByAccId();
		int allNum = lists.size();
		Page<Customer> page = new Page<>(allNum, p);
		List<Customer> pageCust = dao.findByPage(0, page.getStart(), page.getPageSize());
		page.setItems(pageCust);
		return page;
	}



	public List<Map<String, Object>> getLeavelAndCount() {
		return dao.getLeavelAndCount();
		
	}
}
