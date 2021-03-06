<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza elemento</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>

	<main role="main" class="container">
	
	<%@ include file="../header.jsp"%>

		<div class='card'>
			<div class='card-header'>Visualizza dettaglio</div>

			<div class='card-body'>

				<dl class="row">
					<dt class="col-sm-3 text-right">Descrizione:</dt>
					<dd class="col-sm-9">${show_municipio_attr.descrizione}</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Codice:</dt>
					<dd class="col-sm-9">${show_municipio_attr.codice}</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Ubicazione:</dt>
					<dd class="col-sm-9">${show_municipio_attr.ubicazione}</dd>
				</dl>

			</div>
		</div>

		<div class='card-footer'>
			<a href="ExecuteSearchMunicipioServlet" class='btn btn-outline-secondary'
				style='width: 80px'> <i class='fa fa-chevron-left'></i> Back
			</a>
		</div>

	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>