<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../header.jsp" />
		<title>modifica elemento</title>
		
		<!-- style per le pagine diverse dalla index -->
	    <link href="./assets/css/global.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica abitante</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
					<form method="post" action="ExecuteModificaAbitanteServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" value="${abitante_attribute.nome}">
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger">*</span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" value="${abitante_attribute.cognome}">
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Residenza <span class="text-danger">*</span></label>
								<input type="text" class="form-control" name="residenza" id="residenza" value="${abitante_attribute.residenza}">
							</div>
							
							<div class="form-group col-md-3">
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${abitante_attribute.dataDiNascita}' />
								<label>Data Di Nascita <span class="text-danger">*</span></label>
                        		<input class="form-control" id="dataDiNascita" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataDiNascita" required 
                            		value="${parsedDate}" >
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label for="regista.id">Municipio</label>
							    <select class="form-control" id="municipio.id" name="municipio.id">
							      	<c:forEach items="${list_municipio_attr }" var="municipioItem">
							      		<option value="${municipioItem.id}" ${abitante_attribute.municipio.id == municipioItem.id?'selected':''} >${municipioItem.descrizione }</option>
							      	</c:forEach>
							    </select>
							</div>
						</div>
						<p>
				 
						<div class="form-group col-md-6">	
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							<input type="hidden" name="idAbitante" value="${abitante_attribute.id}">
							 <a href="ExecuteListAbitanteServlet" class='btn btn-outline-secondary' style='width:80px'>
			                <i class='fa fa-chevron-left'></i> Back
			      			  </a>
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