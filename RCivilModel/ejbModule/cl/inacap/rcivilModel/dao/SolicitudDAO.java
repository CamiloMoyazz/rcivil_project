package cl.inacap.rcivilModel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import cl.inacap.rcivilModel.dto.Solicitud;

/**
 * Session Bean implementation class SolicitudDAO
 */
@Stateless
@LocalBean
public class SolicitudDAO implements SolicitudDAOLocal {
	
	private static List<Solicitud> solicitudes = new ArrayList<>(); //AQUI TENEMOS UNA LISTA ESTATICA DE LAS SOLICITUDES

    /**
     * Default constructor. 
     */
    public SolicitudDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void save(Solicitud solicitud) {
		solicitudes.add(solicitud);
		
	}

	@Override
	public List<Solicitud> getAll() {
		// TODO Auto-generated method stub
		return solicitudes;
	}

	@Override
	public void delete(Solicitud solicitud) {
		solicitudes.remove(solicitud);
	}

	@Override
	public List<Solicitud> filterByType(String tipo) {
		
		//TENEMOS UN STREAM QUE RECORRE LA LISTA SOLICITUDES, FILTRA OBTENIENDO EL TIPO Y COMPARANDOLO CON EL TIPO RECIBIDO, SI ES QUE LO CONTIENE, SE AGREGA 
		// A UNA COLECCION.
		
		return solicitudes.stream().filter(s->s.getTipo().toLowerCase().contains(tipo.toLowerCase())).collect(Collectors.toList());
	}

	@Override
	public List<Solicitud> filterByName(String nombre) {
		
		return solicitudes.stream().filter(n->n.getNombre().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList()) ;
	}

}
