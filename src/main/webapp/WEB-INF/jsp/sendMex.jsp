<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:url value="/js/json.js" var="jsonJs"/>
<script src="${jsonJs}"></script>

<c:url value="../messaggi" var="url"></c:url>

<div class="wrapper">
	<div class="extra">
		<form:form id="formID" method="POST" modelAttribute="mex"
			action="${url}">

			<form:hidden path="id" />
			<form:hidden path="command" />
			<form:hidden path="data" />
			<div class="f_sx centrata">
				<div>
					<form:label path="destinatario">Destinatario</form:label>
					<form:input path="destinatario" />
				</div>
				<div>
					<form:label path="author">Mittente</form:label>
					<form:input path="author" />
				</div>
				<div>
					<form:label path="content">Messaggio</form:label>
					<form:textarea rows="5" cols="50" id="txtAreaContent"
						path="content"></form:textarea>
				</div>
			</div>
			<div class="f_dx submit">
				<input type="submit" class="button" value="Manda messaggio" />
			</div>

		</form:form>
	</div>
	
</div>
<div style="clear: both;">
</div>

<script>
$('#formID').submit(function(event) {
	event.preventDefault();
	$.ajax({
		url : "${url}",
		type : 'POST',
		data : JSON.stringify(serializeForm($(this))),
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			alert('OK');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(textStatus + " ->\n" + errorThrown);
		}
	});
	return false;
});
</script>