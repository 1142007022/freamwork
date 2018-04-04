package kaisheng.project.utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 
 * 缓存的名字是唯一的 根据名字获取不同的缓存
 * 得到缓存后若是想要获取缓存中存的值还要根据key 调用get(key)
 * 根据key获取得到的是一个Element 要想获得value还要调用getObjectValue()方法
 * @author 姜阿东
 *
 */
public class CacheUtils {
	
	private static CacheManager cacheManager = new CacheManager();
	private String cacheName;
	private Ehcache cache;
	
	public CacheUtils(String cacheName) {
		this.cacheName = cacheName;
	}
	
	public Ehcache getehcache() {
		if(cache == null){
			cache = cacheManager.getEhcache(cacheName);
		}
		return cache;
	}
	
	public Object getValues(Object key) {
		Element element = getehcache().get(key);
		return element == null ? null : element.getObjectValue();
	}
	
	public void setCache(Object key,Object value) {
		Element elemen = new Element(key,value);
		getehcache().put(elemen);
	}
	
	public void del(Object key) {
		getehcache().remove(key);
	}
	
	public void delAll(String name) {
		cacheManager.removeCache(name);
	}
}
