package com.wj.model;

import java.io.Serializable;

/**
 * @author 
 */
public class TPosition implements Serializable {
    private Integer id;

    private String postName;

    private String note;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TPosition{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}