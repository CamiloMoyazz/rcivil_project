package cl.inacap.rcivilModel.dao;

import java.util.List;

import javax.ejb.Local;

import cl.inacap.rcivilModel.dto.Solicitud;

@Local
public interface SolicitudDAOLocal {
	
	//AQUI DEFINIMOS LOS METODOS QUE SE IMPLEMENTARAN EN EL DAO
	
	void save(Solicitud solicitud);
	List<Solicitud> getAll();
	void delete(Solicitud solicitud);
	List<Solicitud>filterByName(String nombre);
	
}
