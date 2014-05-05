<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div >
	<form:form id="formID" enctype='application/json' method="POST"
		commandName="MexForm" action="../messaggi">

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
				<form:textarea rows="5" cols="50" id="txtAreaContent" path="content"></form:textarea>
			</div>
		</div>
		<div class="f_dx submit">
			<input type="submit" class="button" value="Manda messaggio" />
		</div>

	</form:form>
</div>
<script>
	$('#formID').submit(
			function() {
				$.ajax({
					url : $(this).attr('action'),
					type : 'POST',
					data : JSON.stringify($(this).serializeArray()),
					contentType : 'application/json',
					success : function(data) {
						alert('data');
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(data);
						alert('An error has occured!! :-(' + jqXHR + textStatus
								+ errorThrown);
					}
				});

				return false;
			});
</script>