package com.wj.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 
 */
public class TUser implements Serializable {
    private Integer id;

    /**
     * 鐢ㄦ埛鍚嶇О
     */
    private String userName;

    /**
     * 鐪熷疄鍚嶇О
     */
    private String realName;

    /**
     * 濮撳悕
     */
    private Byte sex;

    /**
     * 鐢佃瘽
     */
    private String mobile;

    /**
     * 閭??
     */
    private String email;

    /**
     * 澶囨敞
     */
    private String note;

    private TPosition position;


    private List<TRole> roles;

    private List<HealthReport> healthReports;


    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TPosition getPosition() {
        return position;
    }

    public void setPosition(TPosition position) {
        this.position = position;
    }

    public List<TRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TRole> roles) {
        this.roles = roles;
    }

    public List<HealthReport> getHealthReports() {
        return healthReports;
    }

    public void setHealthReports(List<HealthReport> healthReports) {
        this.healthReports = healthReports;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                ", position=" + position +
                ", roles=" + roles +
                ", healthReports=" + healthReports +
                '}';
    }
}