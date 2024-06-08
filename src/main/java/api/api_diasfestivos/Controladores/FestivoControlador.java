package api.api_diasfestivos.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.api_diasfestivos.aplicacion.Festivofecha;
import api.api_diasfestivos.core.Dominio.festivo;
import api.api_diasfestivos.core.interfaces.servicios.IfestivoServicio;

@RestController
@RequestMapping("api/festivo")
public class FestivoControlador {

    private IfestivoServicio servicio;

    public FestivoControlador(IfestivoServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<festivo> listar() {
        System.out.println("Hola");
        return servicio.listar();
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public festivo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{dia}/{mes}", method = RequestMethod.GET)
    public festivo buscar(@PathVariable int dia, @PathVariable int mes) {
        return servicio.buscar(dia, mes);
    }

    @RequestMapping(value = "/esfestivo/{anio}/{mes}/{dia}", method = RequestMethod.GET)
    public String esfestivo(@PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        return servicio.validar(anio, mes, dia);
    }

    @RequestMapping(value = "/listarfes/{anio}", method = RequestMethod.GET)
    public List<Festivofecha> listarfes(@PathVariable int anio) {
        return servicio.listarfes(anio);
    }

}