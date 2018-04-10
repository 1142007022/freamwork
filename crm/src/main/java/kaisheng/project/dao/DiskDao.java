package kaisheng.project.ao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import kaisheng.project.entitys.Disk;
import kaisheng.project.utils.JdbcHelp;

public class DiskDao {

	public List<Disk> getDiskListByPid(int pId) {
		String sql = "select * from t_disk where pId = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Disk.class), pId);
	}

	public void addDisk(Disk disk) {
		String sql = "insert into t_disk (name,pId,type,accountId,fileSize,downloadCount,saveName,md5) value (?,?,?,?,?,?,?,?)";
		JdbcHelp.executeUpdate(sql, disk.getName(),disk.getpId(),disk.getType(),disk.getAccountId(),disk.getFileSize(),disk.getDownloadCount(),disk.getSaveName(),disk.getMd5());
	}

	public Disk findDiskById(int parseInt) {
		String sql = "select * from t_disk where id = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Disk.class), parseInt);
	}

	public void delById(String id) {
		String sql = "delete from t_disk where id = ?";
		JdbcHelp.executeUpdate(sql, id);
	}

	public void delBypId(String pid) {
		String sql = "delete from t_disk where pId = ?";
		JdbcHelp.executeUpdate(sql, pid);
	}

	public void reName(String id, String name) {
		String sql = "update t_disk set name = ? where id = ?";
		JdbcHelp.executeUpdate(sql, name,id);
	}

	public List<Disk> findDisksByPid(String id) {
		String sql = "select * from t_disk where pId = ?";
		return JdbcHelp.query(sql, new BeanListHandler<>(Disk.class), id);
	}

	public Disk findDiskByMd5(String md5) {
		String sql = "select * from t_disk where md5 = ?";
		return JdbcHelp.query(sql, new BeanHandler<>(Disk.class), md5);
	}

}
