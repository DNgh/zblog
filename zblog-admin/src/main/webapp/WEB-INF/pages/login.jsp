<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  	<%-- <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf_parameter" content="_csrf" /> --%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登陆</title>
</head>
<body>
    <div class="error ${param.error == true ? '' : 'hide'}">
        登陆失败<br>
        ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
    </div>
    <%--
        特别要注意的是form表单的action是提交登陆信息的地址，这是security内部定义好的，
        同时自定义form时，要把form的action设置为/j_spring_security_check。
        注意这里要使用绝对路径，避免登陆页面存放的页面可能带来的问题。
    --%>
    <form name="f" action="<c:url value='login'/>" method="POST">
      <table>
        <tr><td>User:</td><td><input type='text' name='username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='password'></td></tr>
        <tr><td><input type="checkbox" name="remember-me"></td><td>Don't ask for my password for two weeks</td></tr>

        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
      </table>
      <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
    </form>
</body>
</html>