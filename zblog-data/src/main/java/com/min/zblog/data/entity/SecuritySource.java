package com.min.zblog.data.entity;

//访问资源所需角色权限
public class SecuritySource {
	
	private String url;//资源访问的地址

    private String role;//所需要的权限

    public SecuritySource(String url, String role) {
        this.url = url;
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
