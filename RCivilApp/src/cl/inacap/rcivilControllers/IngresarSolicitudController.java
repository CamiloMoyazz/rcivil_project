package cl.inacap.rcivilControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.inacap.rcivilModel.dao.SolicitudDAOLocal;
import cl.inacap.rcivilModel.dto.Solicitud;

/**
 * Servlet implementation class IngresarSolicitudController
 */
@WebServlet("/IngresarSolicitudController.do")
public class IngresarSolicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitudDAOLocal solicitudesDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IngresarSolicitudController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/vistas/ingresarSolicitud.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	// DECLARAMOS LA VARIABLE ATOMICINTEGER
	AtomicInteger atencion = new AtomicInteger(001);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> errores = new ArrayList<String>();

		// VALIDACION DE FORMULARIO

		// VALIDACION DE NOMBRE
		String nombre = request.getParameter("nombre-txt");
		if (!nombre.isEmpty()) {
			if (!nombre.trim().contains(" ")) {
				errores.add("El Nombre debe contener un espacio que separe el Apellido");
			}
		} else {
			errores.add("Debe Ingresar el Nombre en un Formato Correcto");
		}

		// VALIDACION DEL TIPO DE SOLICITUD SELECCIONADA
		String tipo = request.getParameter("tipo-select");
		if (tipo.isEmpty()) {
			errores.add("Selecciones una Solicitud Válida");
		}
		if(tipo.equalsIgnoreCase("Retiro de Cedula de Identidad")) {
			String num = request.getParameter("numero-txt");
			if(num.trim().equals("")) {
				errores.add("Debe Ingresar el Numero de su Solicitud Original");
			}
		}

		// VALIDACION SI LA SOLICITUD NO
		Solicitud s = new Solicitud();
		s.setNombre(nombre);
		s.setTipo(tipo);

		String rut = request.getParameter("rut-txt");
		if (rut.isEmpty()) {
			errores.add("Debe Ingresar un Rut");
		} else {
			
			
			
//-----------------              VERIFICACION  SI EL RUT ES VALIDO               --------------------------------------------------------- //			
			try {
				// ELIMINARMOS CUALQUIER CARACTER QUE NO NOS IMPORTE
				rut = rut.toUpperCase();
				rut = rut.replace(".", "");
				rut = rut.replace("-", "");

				// INVERTIMOS LA CADENA PARA PODER EMPEZAR A MULTIPLICAR DESDE EL ULTIMO DIGITO
				// PERO PRIMERO VALIDAMOS SI EL RUT COMIENZA SOLO CON 1 DIGITO
				System.out.println(rut.length());
				if (rut.length() > 8) {
					String rutVerificado = rut.substring(0, 8);
					char verificador = rut.charAt(8);
					String veri = String.valueOf(verificador);

					char[] rutArray = rutVerificado.toCharArray();
					Object[] invertir = new Object[rutArray.length];
					int tope = rutArray.length;

					for (int i = 0; i < rutArray.length; i++) {

						invertir[tope - 1] = rutArray[i];
						tope--;
					}

					// MULTIPLICAMOS LOS VALORES DESDE EL ULTIMO AL PRIMERO POR 2,3,4,5,6,7
					int secuencia = 2;
					int suma = 0;
					int mult;

					for (int i = 0; i < invertir.length; i++) {
						mult = 0;
						mult = Integer.parseInt(String.valueOf(invertir[i])) * secuencia;
						suma += mult;

						if (secuencia == 7) {
							secuencia = 1;
						}
						secuencia++;
					}

					// CALCULAMOS EL MODULO 11
					int resto = suma % 11;
					String digito = String.valueOf(11 - resto);

					// VALIDAMOS QUE EL DIGITO REL RUT INGRESADO CORRESPONDE AL RESTO QUE SE
					// CUALCULO
					if (digito.equals("11")) {
						digito = "0";
						if (!digito.equals(veri)) {
							errores.add("Debe Ingresar un Rut Válido");
						}
					} else if (digito.equals("10")) {
						digito = "K";
						if (!digito.equals(veri)) {
							errores.add("Debe Ingresar un Rut Válido");
						}
					}
					if (digito.contentEquals(veri)) {
						// AGREGAMOS EL GUION AL FINAL PARA HACERLO MAS LEIBLE
						StringBuilder division = new StringBuilder(rut);
						division.insert(8, "-");
						rut = division.toString();

						// SETEAMOS EL RUT EN LA CLASE SOLICITUD
						s.setRut(rut);
					} else {
						errores.add("Debe Ingresar un Rut Válido");
					}

			// ESTA PARTE ES SIMILAR A LA DE ARRIBA SOLO QUE VERIFICA A LOS RUT DE PERSONAS MAS ANTIGUAS QUE CUENTAN CON UN RUT CON MENOS DIGITOS
				} else {
					String rutVerificado = rut.substring(0, 7);
					char verificador = rut.charAt(7);
					String veri = String.valueOf(verificador);

					char[] rutArray = rutVerificado.toCharArray();
					Object[] invertir = new Object[rutArray.length];
					int tope = rutArray.length;

					for (int i = 0; i < rutArray.length; i++) {

						invertir[tope - 1] = rutArray[i];
						tope--;
					}

					// MULTIPLICAMOS LOS VALORES DESDE EL ULTIMO AL PRIMERO POR 2,3,4,5,6,7
					int secuencia = 2;
					int suma = 0;
					int mult;

					for (int i = 0; i < invertir.length; i++) {
						mult = 0;
						mult = Integer.parseInt(String.valueOf(invertir[i])) * secuencia;
						suma += mult;

						if (secuencia == 7) {
							secuencia = 1;
						}
						secuencia++;
					}

					// CALCULAMOS EL MODULO 11
					int resto = suma % 11;
					String digito = String.valueOf(11 - resto);

					// VALIDAMOS QUE EL DIGITO REL RUT INGRESADO CORRESPONDE AL RESTO QUE SE
					// CUALCULO
					if (digito.equals("11")) {
						digito = "0";
						if (!digito.equals(veri)) {
							errores.add("Debe Ingresar un Rut Válido");
						}
					} else if (digito.equals("10")) {
						digito = "K";
						if (!digito.equals(veri)) {
							errores.add("Debe Ingresar un Rut Válido");
						}
					}
					if (digito.contentEquals(veri)) {
						// AGREGAMOS EL GUION AL FINAL PARA HACERLO MAS LEIBLE
						StringBuilder division = new StringBuilder(rut);
						division.insert(7, "-");
						rut = division.toString();

						// SETEAMOS EL RUT EN LA CLASE SOLICITUD
						s.setRut(rut);
					} else {
						errores.add("Debe Ingresar un Rut Válido");
					}

				}

			} catch (Exception ex) {
				errores.add("Debe Ingresar un Rut Válido");
			}
		}

		if (errores.isEmpty()) {

			// NUMERO DE SOLICITUD AUTOMATICO UTILIZANDO ATOMICINTEGER LO OBTIENE DESDE LA
			// VARIABLE ATENCION DECLARA FUERA DEL METODO POST
			// LUEGO LA INCREMENTA EN 1
			int numAtencion = atencion.getAndAdd(1);
			s.setNumAtencion(numAtencion);

			solicitudesDAO.save(s);
			RequestDispatcher redireccion;
			redireccion = request.getRequestDispatcher("AtenderSolicitudController.do");
			redireccion.forward(request, response);
		} else {
			request.setAttribute("errores", errores);
		}

		doGet(request, response);
	}

}
