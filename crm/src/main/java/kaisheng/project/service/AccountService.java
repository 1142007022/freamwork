package kaisheng.project.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import kaisheng.project.dao.AccountAndDeptDao;
import kaisheng.project.dao.AccountDao;
import kaisheng.project.entitys.Account;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.utils.CacheUtils;
import kaisheng.project.utils.Config;
import kaisheng.project.utils.Page;
import net.sf.ehcache.Ehcache;

public class AccountService {
    AccountAndDeptDao adDao = new AccountAndDeptDao();
    AccountDao dao = new AccountDao();
    CacheUtils cacheUtils = new CacheUtils("account");
    String salt = Config.get("user.password.salt");
    Ehcache ehcahe = cacheUtils.getehcache();

    public Account findAccountById(int id) {
        Account acc = (Account) cacheUtils.getValues(id);
        if (acc == null) {
            acc = dao.findById(id);
            cacheUtils.setCache(id, acc);
        }
        return acc;
    }

    public Account findByMobileAndPassword(String mobile, String password) {
        Account acc = (Account) cacheUtils.getValues(mobile);
        if (acc == null) {
            acc = dao.findByMobile(mobile);
            cacheUtils.setCache(mobile, acc);
        }
        String saltpassword = DigestUtils.md5Hex(salt + password);
        if (acc != null && saltpassword.equals(acc.getPassword())) {
            return acc;
        } else {
            return null;
        }
    }

    public void saveAccountDept(String username, String password, String mobile, String[] depIds) {
        Account acc = (Account) cacheUtils.getValues(mobile);
        if (acc == null) {
            acc = dao.findByMobile(mobile);
            cacheUtils.setCache(mobile, acc);
        }
        if (acc != null) {
            throw new ServiceException("员工已存在请勿重复添加!");
        } else {
            acc = new Account();
            acc.setUsername(username);
            password = DigestUtils.md5Hex(salt + password);
            acc.setPassword(password);
            acc.setMobile(mobile);
            acc.setCreateTime(new Timestamp(System.currentTimeMillis()));
            acc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            dao.save(acc);

            Account account = dao.findByMobile(mobile);
            int aId = account.getId();
            // 保存成功之后在建立account和dept的关系
            for (int i = 0; i < depIds.length; i++) {
                int depId = Integer.parseInt(depIds[i]);
                adDao.save(aId, depId);
            }

        }

    }

    public int getAllNumByDeptId(String deptId, int accId) {
        Integer num = (Integer) cacheUtils.getValues(deptId);
        if (num == null) {
            num = dao.findAllByDeptId(deptId,accId).size();
            cacheUtils.setCache(deptId, num);
        }
        //int adminId = Integer.parseInt(Config.get("adminId"));
        return num;
    }

    public Page<Account> findByPage(String deptId, int pNum, HttpServletRequest req) {

        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("account");

        int accId = acc.getId();
        int allNum = getAllNumByDeptId(deptId, accId);

        Page<Account> page = new Page<>(allNum, pNum);
        List<Account> lists = dao.finByPage(deptId, page.getStart(), page.getPageSize(), accId);

        page.setItems(lists);
        return page;
    }


    public List<Account> findAll() {
        List<Account> lists = (List<Account>) cacheUtils.getValues("allAcc");
        if (lists == null) {
            lists = dao.findAll();
            cacheUtils.setCache("allAcc", lists);
        }
        return lists;
    }

    public void changeKey(String newPassword, int id) {
        String password = DigestUtils.md5Hex(salt + newPassword);
        Account acc = dao.findById(id);
        if (acc.getPassword().equals(password)) {
            throw new ServiceException("新密码不能和旧密码重复！");
        }

        dao.changeKey(password, id);
    }

    public void delById(int accId) {
        dao.delById(accId);
    }

    public Account finEditAccById(int accId) {
        return dao.findEditAccById(accId);
    }

    public void editAccount(String username, String mobile, int id) {
        dao.editAccount(username, mobile, id);
    }

    public void delAccAnddeptByAccId(String id) {
        dao.delAccAnddeptByAccId(Integer.parseInt(id));
    }

    public void saveAccountAndDept(String id, String depId) {
        int aid = Integer.parseInt(id);
        int pid = Integer.parseInt(depId);
        adDao.save(aid, pid);
    }

}
