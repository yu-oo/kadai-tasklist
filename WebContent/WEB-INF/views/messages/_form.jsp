<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${error != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="erorr" items="${error}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>


<label for="content">タイトル</label><br />
<input type="text" name="content" value="${message.content}"/>
<br /><br />

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">投稿</button>
