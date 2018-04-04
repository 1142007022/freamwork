package kaisheng.project.entitys;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;

public class Task {
	private String name;
	private Date finishTime;
	private Timestamp createTime;
	private int id;
	private int accountId;
	private int status;
	public int getStatus() {
		return status;
	}
	
	public boolean isOverTime(){
		DateTime date = new DateTime(getFinishTime());
		return date.isBeforeNow();
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
