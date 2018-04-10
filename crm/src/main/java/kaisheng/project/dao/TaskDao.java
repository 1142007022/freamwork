package kaisheng.project.ao;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import kaisheng.project.entitys.Task;
import kaisheng.project.utils.JdbcHelp;

public class TaskDao {

	public void addTask(Task task) {
		String sql = "insert into t_task (name,finishTime,accountId,status) values (?,?,?,?)";
		JdbcHelp.executeUpdate(sql, task.getName(),task.getFinishTime(),task.getAccountId(),task.getStatus());
	}

	public List<Task> findTasksByAccId(int accId) {
		String sql = "select * from t_task where accountId = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Task.class), accId);
	}

	public Task getTaskById(int tId) {
		String sql = "select * from t_task where id = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Task.class), tId);
	}

	public void edit(String taskId, String name, Date finishDate) {
		String sql = "update t_task set name = ?,finishTime = ? where id = ?";
		JdbcHelp.executeUpdate(sql, name,finishDate,taskId);
	}

	public void delById(String taskId) {
		String sql = "delete  from t_task where id = ?";
		JdbcHelp.executeUpdate(sql, taskId);
	}

	public void changeStatus(String taskId, String status) {
		String sql = "update t_task set status = ? where id = ?";
		JdbcHelp.executeUpdate(sql, status,taskId);
	}

	public List<Task> findUnDoneTaskById(int accId) {
		String sql = "select * from t_task where accountId = ? and status = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Task.class),accId,0);
	}

	public Task findTskBuid(String taskId) {
		String sql = "select * from t_task where id = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Task.class), taskId);
	}

}
