<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:body>
<form:form method="PUT" model="mex" action="/messaggi/scrivi" >

        <form:label path="destinatario">Destinatario</form:label>
       	<form:input path="destinatario" />
       	 
       	<form:input type="hidden" path="id" />
   			
   		<form:label path="content">Messaggio</form:label>
		<textarea rows="20" cols="10" id="txtAreaContent" path="content">Scrivi qui il tuo messaggio</textarea>
	<input type="submit" value="Manda messaggio"/>
</form:form>

<script type="text/javascript">
	$("#txtAreaContent").onChange(function(){
		this.value="";
	});
</script>
</jsp:body>
