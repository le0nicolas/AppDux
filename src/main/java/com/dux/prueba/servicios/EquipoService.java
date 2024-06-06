package com.dux.prueba.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dux.prueba.dominios.Equipo;
import com.dux.prueba.repositorios.EquipoRepository;
import com.dux.prueba.exceptions.CustomException;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> getAll() {
    	try {
            List<Equipo> equipos = equipoRepository.findAll();
            return equipos;
    	}catch(Exception e) {
    		throw new CustomException("Ocurrió un error inesperado. (OPERACIÓN - GET ALL)", HttpStatus.BAD_REQUEST);
    	}
    }
    
    public Optional<Equipo> findById(Long id) {
    	try {
            Optional<Equipo> equipo = equipoRepository.findById(id);
            if(equipo.isEmpty()) {
            	throw new CustomException("Equipo no encontrado", HttpStatus.NOT_FOUND);
            }
            return equipo;
    	}catch (CustomException e) {
            throw e; 
        }catch(Exception e) {
    		throw new CustomException("Ocurrió un error inesperado. (OPERACIÓN - FIND BY ID)", HttpStatus.BAD_REQUEST);
    	}
    }
    
    public List<Equipo> findByNombre(String nombre) {
    	try {
    		List<Equipo> equipos = equipoRepository.findByNombreContainingIgnoreCase(nombre);
            if(equipos.isEmpty()) {
            	throw new CustomException("Equipos no encontrados", HttpStatus.NOT_FOUND);
            }
            return equipos;
    	}catch (CustomException e) {
            throw e; 
        }catch(Exception e) {
    		throw new CustomException("Ocurrió un error inesperado. (OPERACIÓN - FIND BY NOMBRE)", HttpStatus.BAD_REQUEST);
    	}
    }
    
    public Equipo save(Equipo equipo) {
    	try {
    		if(equipo.getNombre().isEmpty() || equipo.getLiga().isEmpty() || equipo.getPais().isEmpty() ) {
    			throw new CustomException("La solicitud es inválida.", HttpStatus.BAD_REQUEST);
    		}
            return equipoRepository.save(equipo);    		
    	}catch (CustomException e) {
            throw e; 
        }catch (NullPointerException e) {
        	throw new CustomException("La solicitud es inválida.", HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
    		throw new CustomException("Ocurrió un error inesperado. (OPERACIÓN - SAVE)", HttpStatus.BAD_REQUEST);
    	}

    }
    
    public Equipo update(Equipo equipoRequest, Long id) {
    	try {
			 Equipo equipoFound = equipoRepository.findById(id)
	    		            .orElseThrow(() -> new CustomException("Equipo no encontrado.", HttpStatus.NOT_FOUND));
	
			 equipoFound.setNombre(equipoRequest.getNombre());
			 equipoFound.setLiga(equipoRequest.getLiga());
			 equipoFound.setPais(equipoRequest.getPais());
	
	         return equipoRepository.save(equipoFound); 		
    	}catch (CustomException e) {
            throw e; 
        }catch(Exception e) {
    		throw new CustomException("Ocurrió un error inesperado. (OPERACIÓN - UPDATE)", HttpStatus.BAD_REQUEST);
    	}

    }
    
    public void delete(Long id) {
    	try {
			 Equipo equipoFound = equipoRepository.findById(id)
	    		            .orElseThrow(() -> new CustomException("Equipo no encontrado.", HttpStatus.NOT_FOUND));
	
	         equipoRepository.delete(equipoFound); 		
    	}catch (CustomException e) {
            throw e; 
        }catch(Exception e) {
    		throw new CustomException("Ocurrió un error inesperado. (OPERACIÓN - DELETE)", HttpStatus.BAD_REQUEST);
    	}

    }
}
