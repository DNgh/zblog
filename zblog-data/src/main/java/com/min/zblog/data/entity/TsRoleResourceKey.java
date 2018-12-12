package com.min.zblog.data.entity;

import java.io.Serializable;

public class TsRoleResourceKey implements Serializable {
	private Long roleId;
	private Long resourceId;
	
    public TsRoleResourceKey() {
        
    }

    public TsRoleResourceKey(Long roleId, Long resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
    
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	public String toString() {
        return String.valueOf(roleId) + "|" + String.valueOf(resourceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        TsRoleResourceKey atk = (TsRoleResourceKey) obj;
        return this.roleId.equals(atk.roleId)
        		&&this.resourceId.equals(atk.resourceId);
    }

    @Override
    public int hashCode() {
    	final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.resourceId.hashCode();
		
        return hash;
    }
}
