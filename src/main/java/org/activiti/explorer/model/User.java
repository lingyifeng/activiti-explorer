package org.activiti.explorer.model;


import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  用户
 * </p>
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 账号（系统中昵称，唯一）
     */
    private String account;

    /**
     * 用户编号
     */
    private String code;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 部门ID（关联）
     */
    private Integer deptId;

    /**
     * 部门全称
     */
    private String deptFullName;

    /**
     * 部门简称
     */
    private String deptShortName;

    /**
     * 部门编号
     */
    private String deptCode;

    /**
     * 性别：0女，1男
     */
    private Integer sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 有限
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 职位
     */
    private String position;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String tips;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除
     */
    private Integer isDel;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 验证码
     */
    private String kaptcha;


    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptFullName() {
        return deptFullName;
    }

    public void setDeptFullName(String deptFullName) {
        this.deptFullName = deptFullName;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", account=" + account +
        ", code=" + code +
        ", password=" + password +
        ", salt=" + salt +
        ", deptId=" + deptId +
        ", deptFullName=" + deptFullName +
        ", deptShortName=" + deptShortName +
        ", deptCode=" + deptCode +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", email=" + email +
        ", phone=" + phone +
        ", avatarUrl=" + avatarUrl +
        ", position=" + position +
        ", status=" + status +
        ", tips=" + tips +
        ", createTime=" + createTime +
        ", isDel=" + isDel +
        ", version=" + version +
        "}";
    }
}
