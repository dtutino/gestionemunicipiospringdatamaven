<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica municipio</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>

	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty municipioErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${municipioErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

	<main role="main" class="container">

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica municipio</h5>
			</div>
			<div class='card-body'>

				<form class="form-horizontal"
					action="ExecuteModificaMunicipioServlet?idMunicipio=${municipioAttribute.id}"
					method="post">
					<div class="form-group">
						<label class="control-label col-sm-2" for="descrizioneInputId">Descrizione:</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" id="descrizioneInputId"
								name="descrizione" value="${municipioAttribute.descrizione }">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" id="codiceInputId"
								name="codice" value="${municipioAttribute.codice }">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="ubicazioneInputId">Ubicazione:</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" id="ubicazioneInputId"
								name="ubicazione" value="${municipioAttribute.ubicazione }">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-md">Conferma</button>
						</div>
					</div>
				</form>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>