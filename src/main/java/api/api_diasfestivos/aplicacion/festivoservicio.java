package api.api_diasfestivos.aplicacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import api.api_diasfestivos.core.Dominio.festivo;
import api.api_diasfestivos.core.interfaces.repositorio.Ifestivorepository;
import api.api_diasfestivos.core.interfaces.servicios.IfestivoServicio;

@Service
public class festivoservicio implements IfestivoServicio {

    private Ifestivorepository repositorio;
    private CalcularFestivo calcularfes;
    private festivo festivo;

    public festivoservicio(Ifestivorepository repositorio, CalcularFestivo calcularfes) {
        this.repositorio = repositorio;
        this.calcularfes = calcularfes;
    }

    @Override
    public festivo buscar(int dia, int mes) {
        Optional<festivo> festivo = repositorio.findByDiaAndMes(dia, mes);
        return festivo.isEmpty() ? null : festivo.get();

    }

    @Override
    public List<festivo> listar() {

        return repositorio.findAll();
    }

    @Override
    public festivo obtener(int id) {
        Optional<festivo> festivo = repositorio.findById(id);

        return festivo.isEmpty() ? null : festivo.get();
    }

    @Override
    public String validar(int anio, int mes, int dia) {
        // Validacion de si la fecha es valida o no, forzando con getTime la validacion
        // si es valida entra a calcular si es o no festivo, si no, devuelve cual es la
        // variable ingresada incorrecta
        String respuesta = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        try {
            calendar.set(anio, mes - 1, dia);
            calendar.getTime();

            festivo = buscar(dia, mes);
            Date fecha = new Date(anio - 1900, mes - 1, dia);

            if (festivo != null && festivo.getTipo() == 1) {
                SimpleDateFormat f1 = new SimpleDateFormat("EEEE dd/MM/yyyy");
                respuesta = "la fecha " + f1.format(fecha) + " Si es festivo";
            } else {

                respuesta = calcularfes.calcular(anio, mes, dia);
            }

        } catch (IllegalArgumentException e) {

            respuesta = "la fecha: " + e.getMessage() + " no es valida";
        }

        return respuesta;
    }

    @Override
    public List<Festivofecha> listarfes(int anio) {
        return calcularfes.listarfes(anio);
    }
}
