package com.dux.prueba.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dux.prueba.servicios.EquipoService;
import com.dux.prueba.dominios.Equipo;
import com.dux.prueba.requests.EquipoRequest;

@RestController
@RequestMapping
public class EquipoController {
    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAll(){
    	List<Equipo> equipos = equipoService.getAll();
    	return new ResponseEntity<>(equipos, HttpStatus.OK);
    }
    
    @GetMapping("/equipos/{id}")
    public ResponseEntity<Optional<Equipo>> findById(@PathVariable Long id){
    	Optional<Equipo> foundEquipo = equipoService.findById(id);
    	
    	return new ResponseEntity<>(foundEquipo, HttpStatus.OK);
    }
    
    @GetMapping("/equipos/buscar")
    public ResponseEntity<List<Equipo>> findByNombre(@RequestParam String nombre){
    	List<Equipo> equipos = equipoService.findByNombre(nombre);
    	
    	return new ResponseEntity<>(equipos, HttpStatus.OK);
    }
    
    @PostMapping("/equipos")
    public ResponseEntity<Equipo> save(@RequestBody EquipoRequest request){
        Equipo equipo = new Equipo(request.getNombre(), request.getLiga(), request.getPais());
        Equipo createdEquipo = equipoService.save(equipo);
        
        return new ResponseEntity<>(createdEquipo, HttpStatus.CREATED);
    }
    
    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> update(@PathVariable Long id , @RequestBody EquipoRequest request){
        Equipo equipo = new Equipo(request.getNombre(), request.getLiga(), request.getPais());
        Equipo updatedEquipo = equipoService.update(equipo, id);
        
        return new ResponseEntity<>(updatedEquipo, HttpStatus.OK);
    }
    
    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        equipoService.delete(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
