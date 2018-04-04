package kaisheng.project.utils;

import java.util.List;

public class Page<T> {

	private int totalpage;
	
	private int pageNo;
	
	private List<T> items;
	
	private int pageSize = 3;
	
	private int start;

	public Page(int total, int pageNo) {
		
		this.totalpage = total / pageSize; 

		if (total % pageSize != 0) {
			this.totalpage++;
		}
		
		if(totalpage <1) {
			totalpage = 1;
		}
		
		if(pageNo > totalpage) {
			pageNo = totalpage;
		}
		
		
		if(pageNo < 1) {
			pageNo = 1;
		}
		
		
		this.pageNo = pageNo;

		
		this.start = (pageNo - 1) * pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
