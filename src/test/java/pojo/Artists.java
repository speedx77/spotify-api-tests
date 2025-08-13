package pojo;

import java.util.List;
import java.util.Map;

public class Artists {
	
	private String href;
	private int limit;
	private String next;
	private int offset;
	private String previous;
	private int total;
	private List<Items> items;
	
	//for tracks pojo
	private Map<String, String> external_urls;
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public Map<String, String> getExternal_urls() {
		return external_urls;
	}
	public void setExternal_urls(Map<String, String> external_urls) {
		this.external_urls = external_urls;
	}
	
	
}
