<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<jsp:include page="../templates/header.jsp"></jsp:include>
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
	
	<main class="container">
		<div class="columns is-centered">
			<div class="column is-6 mt-5">
			<form action="AtenderSolicitudController.do">
				<div class="field">
					<label class="label">Tipo de Solicitud</label>
					<div class="select">
						<select name="filtro-select" id="filtro-select">
							<option>Todas</option>
							<option>Solicitud de Cedula de Identidad</option>
							<option>Retiro de Cedula de Identidad</option>
							<option>Solicitud de Certificado de Nacimiento</option>	
							<option>Solicitud de Certificado de Defuncion</option>
						</select>
					</div>
					
					<div class="field mt-2">
						<button type="submit" class="button is-primary">Filtrar</button>
					</div>
				</div>	
			</form>
			</div>
		</div>
		<div class="columns is-centered">
			<div class="column is-8">
				<table class="table is-fullwidth is-hoverable">
					<thead class="has-background-danger">
						<tr>
							<th>Nro de Atención</th>
							<th>Nombre Cliente</th>
							<th>Tipo de Solicitud</th>
							<th>Atender</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="solicitudes" items="${solicitudes}">
							<tr>
								<td>${solicitudes.numAtencion }</td>
								<td>${solicitudes.nombre }</td>
								<td>${solicitudes.tipo}</td>
								<td><a href="AtenderSolicitudController.do?EliminarAtender=${solicitudes.nombre}" 
								class="has-text-success">Atender</a></td>
							</tr>
						
					</c:forEach>
					</tbody>
				</table>
			</div>
		
		</div>
	
	</main>

</body>
</html>