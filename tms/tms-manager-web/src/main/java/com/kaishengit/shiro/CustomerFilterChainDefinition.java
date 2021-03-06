package com.kaishengit.shiro;

import com.kaishengit.entitys.Power;
import com.kaishengit.service.PowerService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

public class CustomerFilterChainDefinition {


    @Autowired
    private PowerService powerService;
    private String filterChainDefinitions;
    private AbstractShiroFilter shiroFilter;

    public String getFilterChainDefinitions() {
        return filterChainDefinitions;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public AbstractShiroFilter getShiroFilter() {
        return shiroFilter;
    }

    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

    //初始化注解  加锁
    @PostConstruct
    public synchronized void innit() {
        getDefaultFilterChainManager().getFilterChains().clear();
        load();
    }

    public void update() {
        getDefaultFilterChainManager().getFilterChains().clear();
        load();
    }

    public synchronized void load() {

        /*
        * Ini是一个map
        * */
        Ini ini = new Ini();
        ini.load(filterChainDefinitions);
        List<Power> powerList = powerService.findAllPower();
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        for (Power power : powerList) {
            section.put(power.getUrl(), "perms[" + power.getPowerCode() + "]");
        }
        //user这一i组的键值对必须放在section的 最后
        section.put("/**", "user");
        DefaultFilterChainManager defaultFilterChainManager = getDefaultFilterChainManager();
        for (Map.Entry<String, String> entry : section.entrySet()) {
            defaultFilterChainManager.createChain(entry.getKey(), entry.getValue());
        }
    }

    private DefaultFilterChainManager getDefaultFilterChainManager() {
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        return (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
    }
}
