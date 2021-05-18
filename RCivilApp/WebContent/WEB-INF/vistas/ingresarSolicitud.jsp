<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../templates/header.jsp"></jsp:include>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<main class="container">

	<div class="container mt-4">
		<c:if test="${errores != null }">
			<div class="columns is-centered mt-4 mb-1">
				<div class="column  mt-4 is-6">
					<div class="notification is-warning mt-4">
						<h6>Existen errores en el formulario</h6>
						<div class="content">
							<ul>
								<c:forEach var="error" items="${errores}">
									<li>${error}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>

	<div class="container mt-6"></div>
	<div class="columns is-centered">
		<div class="column is-4">
			<form method="POST" action="IngresarSolicitudController.do">
					<!-- COMIENZO DEL CARD -->
				<div class="card mt-6 ">
					<div class="card-header has-background-primary ">
						<h1 class="card-header-title">Ingresar Solicitud</h1>
					</div>
					<!-- CONTENIDO DEL CARD , AQUI ESTAN LOS ELEMENTOS DEL FORMULARIO -->
					<div class="card-content has-text-centered">
						<!-- RUT CLIENTE -->
						<div class="field">
							<label class="label" for="rut-txt">Rut</label>
							<div class="control">
								<input class="input has-text-centered" type="text" id="rut-txt" name="rut-txt" placeholder="10762886-7" >
							</div>
						</div>
						<!-- NOMBRE CLIENTE -->
						<div class="field">
							<label class="label" for="nombre-txt">Nombre</label>
							<div class="control">
								<input class="input has-text-centered" type="text" name="nombre-txt" id="nombre-txt" placeholder="Nombre Apellido">
							</div>
						</div>
						<!-- TIPO SOLICITUD -->
						<div class="field">
							<label class="label" for="tipo-select">Tipo de Solicitud</label>
							<div class="control">
								<div class="select">
									<select name="tipo-select" id="tipo-select">
										<option>Solicitud de Cedula de Identidad</option>
										<option>Retiro de Cedula de Identidad</option>
										<option>Solicitud de Certificado de Nacimiento</option>	
										<option>Solicitud de Certificado de Defuncion</option>								
									</select>
								</div>
							</div>
						</div>
					</div>
					<!-- FOOTER PARA ALOJAR EL BOTON -->
					<div class="card-footer has-text-centered">
						<div class="card-footer-item">
							<button class="button is-primary" type="submit">Ingresar Solicitud</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</main>

</body>
</html>