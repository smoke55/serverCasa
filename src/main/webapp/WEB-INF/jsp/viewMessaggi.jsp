<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="wrapper">
	<a href="messaggiView/sendMex" class="button">Invia nuovo</a>
</div>

<c:url var="url" value="./messaggi" />
<script>
	$(document)
			.ready(
					function() {
						$
								.ajax({
									url : "${url}",
									contentType : 'application/json',
									success : function(data) {
										$
												.each(
														data,
														function(idx, elem) {
															var linko = "messaggi?id="
																	+ elem.id;
															$('div#wrapper')
																	.append(
																			"<div id='extra' class='container'><h2>to:"
																					+ elem.destinatario
																					+ "</h2><span>"
																					+ elem.author
																					+ " - "
																					+ elem.data
																					+ "</span>"
																					+ "<p>"
																					+ elem.content
																					+ "</p>"
																					+ "<a href='messaggiView/modificaMex?idMex="
																					+ elem.id
																					+ "' class='button'>Modifica</a> "
																					+ "<a href="
																					+ "\"javascript:cancellaMex('"+ linko + "')\""
																					+ "class='button'>Cancella</a></div>");
														});
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										alert('An error has occured!2 :-('
												+ jqXHR + textStatus
												+ errorThrown);
									}
								});
					});

	function cancellaMex(url) {
		$.ajax({
			url : url,
			type : 'DELETE',
			data : JSON.stringify($(this).serializeArray()),
			contentType : 'application/json',
			success : function(data) {
				alert(data);
				location.reload();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('An error has occured!! :-(' + jqXHR + textStatus
						+ errorThrown);
			}
		});
	}
</script>

