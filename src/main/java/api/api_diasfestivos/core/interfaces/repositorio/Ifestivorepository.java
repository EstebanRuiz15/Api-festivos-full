package api.api_diasfestivos.core.interfaces.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.api_diasfestivos.core.Dominio.festivo;

@Repository
public interface Ifestivorepository extends JpaRepository<festivo, Integer> {

    Optional<festivo> findByDiaAndMes(int dia, int mes);
}
