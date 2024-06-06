package com.dux.prueba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.dux.prueba.repositorios.EquipoRepository;
import com.dux.prueba.servicios.EquipoService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.dux.prueba.exceptions.CustomException;
import com.dux.prueba.dominios.Equipo;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

	@Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;
	    
    @Test
    public void testFindByIdExistente() {
        // Guardo un equipo en memoria
        Long id = 1L;
        Equipo equipo = new Equipo();
        equipo.setId(id);
        equipo.setNombre("Barcelona");
        equipo.setLiga("La Liga");
        equipo.setPais("España");

        //cuando busqe el equipo por la 1, me tiene que devolver los datos anteriores
        when(equipoRepository.findById(id)).thenReturn(Optional.of(equipo));

        Optional<Equipo> resultado = equipoService.findById(id);
        //comparo que los datos traidos sean iguales
        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        assertEquals("Barcelona", resultado.get().getNombre());
        assertEquals("La Liga", resultado.get().getLiga());
        assertEquals("España", resultado.get().getPais());
    }
    
    @Test
    public void testFindByIdNoExistente() {
        Long id = 1L;
        //cuando busque un equipo por id 1, me tiene que devolver vacío
        when(equipoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> {
            equipoService.findById(id);
        });
    }
}
