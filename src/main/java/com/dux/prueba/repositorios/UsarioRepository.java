package com.dux.prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dux.prueba.dominios.Usuario;
@Repository
public interface UsarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsername(String username);
}
