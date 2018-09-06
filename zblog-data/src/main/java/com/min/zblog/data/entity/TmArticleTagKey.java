package com.min.zblog.data.entity;

import java.io.Serializable;

public class TmArticleTagKey implements Serializable {
	private Long tagId;
	private Long articleId;
	
    public TmArticleTagKey() {
        
    }

    public TmArticleTagKey(Long tagId, Long articleId) {
        this.tagId = tagId;
        this.articleId = articleId;
    }
    
    public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String toString() {
        return String.valueOf(tagId) + "|" + String.valueOf(articleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        TmArticleTagKey atk = (TmArticleTagKey) obj;
        return this.articleId.equals(atk.articleId)
        		&&this.tagId.equals(atk.tagId);
    }

    @Override
    public int hashCode() {
    	final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.articleId.hashCode();
		hash = hash * prime + this.tagId.hashCode();
		
        return hash;
    }
}
