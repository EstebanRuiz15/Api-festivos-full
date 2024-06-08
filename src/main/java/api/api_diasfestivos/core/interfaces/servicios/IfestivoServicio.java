package api.api_diasfestivos.core.interfaces.servicios;

import java.util.List;

import api.api_diasfestivos.aplicacion.Festivofecha;
import api.api_diasfestivos.core.Dominio.festivo;

public interface IfestivoServicio {

    public List<festivo> listar();

    public festivo obtener(int id);

    public festivo buscar(int mes, int dia);

    public String validar(int anio, int mes, int dia);

    public List<Festivofecha> listarfes(int anio);
}
