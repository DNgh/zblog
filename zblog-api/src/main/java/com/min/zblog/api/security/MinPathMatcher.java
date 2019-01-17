package com.min.zblog.api.security;

import java.io.Serializable;

import org.springframework.util.AntPathMatcher;

/**
 * 自定义实现序列化
 * @author zhouzm
 *
 */
public class MinPathMatcher extends AntPathMatcher implements Serializable {
	private static final long serialVersionUID = -3361840431316474694L;

}
