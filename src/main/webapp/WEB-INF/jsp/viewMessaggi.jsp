<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="wrapper">
	<a href="messaggiView/sendMex" class="button">Invia nuovo</a>
	<c:forEach var="mex" items="${mexList}">
		<div id="extra" class="container">
			<h2>TO: ${mex.destinatario }</h2>
			<span>${mex.author } - ${mex.data}</span>
			<p>${mex.content}</p>
			<a href="#" class="button">Modifica</a> <a href="#" class="button">Cancella</a>
		</div>
	</c:forEach>
</div>

