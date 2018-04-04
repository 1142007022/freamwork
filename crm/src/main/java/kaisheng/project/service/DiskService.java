package kaisheng.project.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import kaisheng.project.ao.DiskDao;
import kaisheng.project.entitys.Disk;
import kaisheng.project.utils.Config;

public class DiskService {
	DiskDao dao = new DiskDao();
	public List<Disk> getDiskListByPid(int pId) {
		return dao.getDiskListByPid(pId);
	}
	public void addDisk(String pid, int accId,String name) {
		Disk disk = new Disk();
		disk.setAccountId(accId);
		disk.setName(name);
		disk.setType("dir");
		disk.setpId(Integer.parseInt(pid));
		dao.addDisk(disk);
	}
	public void addDisk(Disk disk){
		dao.addDisk(disk);
		
	}
	public Disk findDiskById(int parseInt) {
		return dao.findDiskById(parseInt);
	}
	public void delById(String id) {
		Disk diskDel = dao.findDiskById(Integer.parseInt(id));
		List<Disk> lists = dao.findDisksByPid(id);
		if(lists.isEmpty()){
			for(Disk disk : lists){
				delById(String.valueOf(disk.getId()));
			}
		}
		dao.delById(id);
		
		//删除服务器文件
		if("file".equals(diskDel.getType())){
			File file = new File(Config.get("file.upload.path"),diskDel.getSaveName());
			file.delete();
		}
	}
/*	public void delBypId(String pid) {
		dao.delBypId(pid);
	}*/
	public void reName(String id, String name) {
		Disk disk = dao.findDiskById(Integer.parseInt(id));
		if(disk.getType().equals("file")){
			name = name + disk.getName().substring(disk.getName().lastIndexOf("."));
		}
		dao.reName(id,name);
	}
	public void saveFile(InputStream input,String name, String pid, long fiileSize, int accId,String md5) {
		//封装disk对象
		Disk disk = new Disk();
		disk.setName(name);
		disk.setpId(Integer.parseInt(pid));
		disk.setAccountId(accId);
		disk.setMd5(md5);
		disk.setFileSize(FileUtils.byteCountToDisplaySize(fiileSize));
		disk.setDownloadCount(Disk.DISK_DOWNLOAD_COUNT);
		disk.setType(Disk.DISK_TYPE_FILE);
		//文件上传
		
		String saveName = UUID.randomUUID() + name.substring(name.lastIndexOf("."));
		String filePath = Config.get("file.upload.path");
		
		try {
			
			OutputStream out = new FileOutputStream(new File(filePath,saveName));
			IOUtils.copy(input, out);
			input.close();
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		disk.setSaveName(saveName);
		dao.addDisk(disk);
		
	}
	public Disk findDiskByMd5(String md5) {
		return dao.findDiskByMd5(md5);
	}

}
