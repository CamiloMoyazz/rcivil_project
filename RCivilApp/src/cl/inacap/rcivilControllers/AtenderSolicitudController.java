package cl.inacap.rcivilControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.inacap.rcivilModel.dao.SolicitudDAOLocal;
import cl.inacap.rcivilModel.dto.Solicitud;

/**
 * Servlet implementation class AtenderSolicitudController
 */
@WebServlet("/AtenderSolicitudController.do")
public class AtenderSolicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitudDAOLocal solicitudesDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtenderSolicitudController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("EliminarAtender") != null) {
			String nombre = request.getParameter("EliminarAtender".trim());
			List<Solicitud> busqueda = solicitudesDAO.filterByName(nombre);
			
			Solicitud SolicitudAEliminar = busqueda.isEmpty()? null:busqueda.get(0);
			if(SolicitudAEliminar != null) {
				solicitudesDAO.delete(SolicitudAEliminar);
			}
		}
		
		List<Solicitud> solicitudes = solicitudesDAO.getAll();
		List<Solicitud> solicitudesFiltradas =  new ArrayList<Solicitud>();
		
		if(request.getParameter("filtro-select") != null) {
	
			System.out.println("Entrando a Filtro");
			
			for(Solicitud solicitudTemp : solicitudes) {
				if(request.getParameter("filtro-select").equalsIgnoreCase(solicitudTemp.getTipo())) {
					solicitudesFiltradas.add(solicitudTemp);
					System.out.println("Se agregó una solicitud filtrada");
				}
			}
			System.out.println("Salimos del filtro");
			
			request.setAttribute("solicitudes", solicitudesFiltradas);
		}else {
			request.setAttribute("solicitudes", solicitudes);
		}
		
		
		request.getRequestDispatcher("WEB-INF/vistas/atenderSolicitud.jsp").forward(request, response);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
