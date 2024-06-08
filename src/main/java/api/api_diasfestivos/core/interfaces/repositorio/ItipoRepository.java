package api.api_diasfestivos.core.interfaces.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.api_diasfestivos.core.Dominio.tipo_festivo;

@Repository
public interface ItipoRepository extends CrudRepository<tipo_festivo, Integer> {

}
