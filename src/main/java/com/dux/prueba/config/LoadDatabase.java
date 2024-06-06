package com.dux.prueba.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dux.prueba.dominios.Equipo;
import com.dux.prueba.dominios.Usuario;
import com.dux.prueba.repositorios.EquipoRepository;
import com.dux.prueba.repositorios.UsarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDatabase {
	 @Bean
	    CommandLineRunner initDatabase(EquipoRepository repository, UsarioRepository userRepository, PasswordEncoder passwordEncoder) {
	        return args -> {
	            repository.save(new Equipo("Real Madrid", "La Liga", "España"));
	            repository.save(new Equipo("FC Barcelona", "La Liga", "España"));
	            repository.save(new Equipo("Manchester United", "Premier League", "Inglaterra"));
	            repository.save(new Equipo("Liverpool FC", "Premier League", "Inglaterra"));
	            repository.save(new Equipo("Juventus FC", "Serie A", "Italia"));
	            repository.save(new Equipo("AC Milan", "Serie A", "Italia"));
	            repository.save(new Equipo("Bayern Munich", "Bundesliga", "Alemania"));
	            repository.save(new Equipo("Borussia Dortmund", "Bundesliga", "Alemania"));
	            repository.save(new Equipo("Paris Saint-Germain", "Ligue 1", "Francia"));
	            repository.save(new Equipo("Olympique de Marseille", "Ligue 1", "Francia"));
	            repository.save(new Equipo("FC Porto", "Primeira Liga", "Portugal"));
	            repository.save(new Equipo("Sporting CP", "Primeira Liga", "Portugal"));
	            repository.save(new Equipo("Ajax Amsterdam", "Eredivisie", "Países Bajos"));
	            repository.save(new Equipo("Feyenoord", "Eredivisie", "Países Bajos"));
	            repository.save(new Equipo("Celtic FC", "Scottish Premiership", "Escocia"));
	            repository.save(new Equipo("Rangers FC", "Scottish Premiership", "Escocia"));
	            repository.save(new Equipo("Galatasaray SK", "Süper Lig", "Turquía"));
	            repository.save(new Equipo("Fenerbahçe SK", "Süper Lig", "Turquía"));
	            repository.save(new Equipo("FC Zenit Saint Petersburg", "Premier League Rusa", "Rusia"));
	            repository.save(new Equipo("Spartak Moscow", "Premier League Rusa", "Rusia"));
	            repository.save(new Equipo("SL Benfica", "Primeira Liga", "Portugal"));
	            repository.save(new Equipo("Besiktas JK", "Süper Lig", "Turquía"));
	            repository.save(new Equipo("SSC Napoli", "Serie A", "Italia"));
	            repository.save(new Equipo("Atlético Madrid", "La Liga", "España"));
	            userRepository.save(new Usuario("test",  passwordEncoder.encode("12345")));
	        };
	    }
}
