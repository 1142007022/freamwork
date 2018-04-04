package kaisheng.project.entitys;

import java.sql.Timestamp;
import java.util.List;

public class Account {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    private int id;
    private String username;
    private String password;
    private String mobile;
    private Timestamp createTime;
    private Timestamp updateTime;
    private List<String> deptName;

    public List<String> getDeptName() {
        return deptName;
    }

    public void setDeptName(List<String> deptName) {
        this.deptName = deptName;
    }
}
