package com.wj.model;

import java.io.Serializable;

/**
 * @author 
 */
public class THealthReportMale implements Serializable {
    private Integer id;

    private String checkProject;

    private String detail;

    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckProject() {
        return checkProject;
    }

    public void setCheckProject(String checkProject) {
        this.checkProject = checkProject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        THealthReportMale other = (THealthReportMale) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCheckProject() == null ? other.getCheckProject() == null : this.getCheckProject().equals(other.getCheckProject()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCheckProject() == null) ? 0 : getCheckProject().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkProject=").append(checkProject);
        sb.append(", detail=").append(detail);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}