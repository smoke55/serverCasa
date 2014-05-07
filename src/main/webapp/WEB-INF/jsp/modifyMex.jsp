<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:url value="/js/json.js" var="jsonJs" />
<script src="${jsonJs}"></script>

<c:url value="../messaggi" var="url"></c:url>

<div class="wrapper">
	<div class="extra">
		<form:form id="formID" enctype="application/json" method="POST"
			modelAttribute="mex" action="${url}">

			<form:hidden id="idIdField" path="id" />
			<form:hidden id="idCommand" path="command" />
			<form:hidden id="idData" path="data" />
			<div class="f_sx centrata">
				<div>
					<form:label path="destinatario">Destinatario</form:label>
					<form:input id="idDestinatario" path="destinatario" />
				</div>
				<div>
					<form:label path="author">Mittente</form:label>
					<form:input id="idAutore" path="author" />
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
<div style="clear: both;"></div>

<c:url var="urlGet" value="../messaggi/${idMex}" />

<script>
	$(document).ready(
			function() {
				$.ajax({
					url : "${urlGet}",
					contentType : 'application/json',
					success : function(data) {
						$("#idIdField").val(data.id);
						$("#idDestinatario").val(data.destinatario);
						$("#idAutore").val(data.author);
						$("#idData").val(data.data);
						$("#txtAreaContent").val(data.content);
						$("#idCommand").val(data.command);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(':-( \n' + jqXHR + "\n" + textStatus + "\n"
								+ errorThrown);
					}
				});
			});

	$('#formID').submit(function(event) {
		event.preventDefault();
		$.ajax({
			url : "${url}",
			type : 'PUT',
			data : JSON.stringify(serializeForm($(this))),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				alert("ok" + data);
				location.href("./");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(data +"\n" + textStatus + " ->\n" + errorThrown);
				location.href("./");
			}
		});
		
		return false;
	});
</script>