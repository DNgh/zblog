package com.min.zblog.data.entity;

import java.io.Serializable;

public class TsUserRoleKey implements Serializable {
	private Long userId;
	private Long roleId;
	
    public TsUserRoleKey() {
        
    }

    public TsUserRoleKey(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String toString() {
        return String.valueOf(userId) + "|" + String.valueOf(roleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        TsUserRoleKey atk = (TsUserRoleKey) obj;
        return this.roleId.equals(atk.roleId)
        		&&this.userId.equals(atk.userId);
    }

    @Override
    public int hashCode() {
    	final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		
        return hash;
    }
}
