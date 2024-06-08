package api.api_diasfestivos.aplicacion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Festivofecha {

    private String nombre = "";

    private String fecha = "";


    public String getNombre() {
        return nombre;
    }

    public void setFecha(Date fecha) {
        SimpleDateFormat f1 = new SimpleDateFormat("EEEE dd/MM/yyyy");
        this.fecha = f1.format(fecha);
    }

    public String getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
