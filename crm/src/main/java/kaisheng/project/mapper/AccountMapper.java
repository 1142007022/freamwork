package kaisheng.project.mapper;

import kaisheng.project.entitys.Account;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface AccountMapper {

    Account findByMobile(String mobile);

    void save(Account account);

    List<Account> findAllAccount(@Param("accId") int accId);

    List<Account> findAllByDeptId(Map<String, Integer> map);

    List<Account> finByPage(Map<String, Object> map);

    Account findEditAccById(int id);

    List<Account> findAll();

    Account findById(int id);

    void changeKey(Map<String, Object> map);

    void editAccount(Map<String, Object> map);

    void delAccAnddeptByAccId(int id);

    void delById(int id);

    int getCountByDeptId(@Param("deptId") String deptId);

}
