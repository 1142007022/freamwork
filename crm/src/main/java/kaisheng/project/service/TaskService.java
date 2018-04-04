package kaisheng.project.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kaisheng.project.ao.TaskDao;
import kaisheng.project.entitys.Task;

public class TaskService {
	
	Task task = new Task();
	TaskDao dao = new TaskDao();
	public void addTask(String name, String finishTime, int accId) {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date finishDate = date.parse(finishTime);
			task.setAccountId(accId);
			task.setFinishTime(finishDate);
			task.setName(name);
			task.setStatus(0);
			
			dao.addTask(task);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Task> findTasksByAccId(int accId,String show) {
		if(StringUtils.isNotEmpty(show)){
			return dao.findUnDoneTaskById(accId);
		}else{
			return dao.findTasksByAccId(accId);
		}
	}
	public Task getTaskById(String taskId) {
		int tId = Integer.parseInt(taskId);
		return dao.getTaskById(tId);
	}
	public void edit(String taskId, String name, String finishTime) {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date finishDate = date.parse(finishTime);
			dao.edit(taskId,name,finishDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void delById(String taskId) {
		dao.delById(taskId);
	}
	public void changeStatus(String taskId, String status) {
		dao.changeStatus(taskId,status);
	}
	public Task findDiskById(String taskId) {
		return dao.findTskBuid(taskId);
	}
	
	

}
