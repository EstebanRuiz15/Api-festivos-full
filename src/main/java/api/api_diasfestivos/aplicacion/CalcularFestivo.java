package api.api_diasfestivos.aplicacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalcularFestivo {

    public String calcular(int anio, int mes, int dia) {
        String respuesta = "";

        Date fecha = new Date(anio - 1900, mes - 1, dia);
        Date[] vectorF = new Date[13];

        vectorF = diasfestivos(fecha);
        respuesta = comparar(vectorF, fecha);

        return respuesta;
    }

    public List<Festivofecha> listarfes(int anio) {
        Date fecha = new Date(anio - 1900, 1, 1);
        Festivofecha[] vectorF = new Festivofecha[19];

        vectorF = listar(fecha);
        List<Festivofecha> lista = new ArrayList<>();

        for (Festivofecha festivo : vectorF) {

            lista.add(festivo);
        }
        return lista;

    }

    public static Date getDomingoRamos(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;

        return new Date(año - 1900, mes - 1, dia);
    }

    public static Date agregarDias(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }

    public static Date siguienteLunes(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        if (calendario.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
            fecha = agregarDias(fecha, 9 - calendario.get(Calendar.DAY_OF_WEEK));
        } else {
            if (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                fecha = fecha;
            } else {
                fecha = agregarDias(fecha, 1);
            }
        }
        return fecha;
    }

    public static Date[] diasfestivos(Date fecha) {
        Date[] fechass = new Date[13];
        Date santoreyes = siguienteLunes(new Date(fecha.getYear(), 0, 6));
        fechass[0] = santoreyes;
        Date sanjose = siguienteLunes(new Date(fecha.getYear(), 2, 19));
        fechass[1] = sanjose;
        int anio = (fecha.getYear() + 1900);

        Date domingoRamos = getDomingoRamos(anio);
        Date domingoPascua = agregarDias(domingoRamos, 7);

        Date juevessanto = agregarDias(domingoPascua, -3);
        fechass[2] = juevessanto;
        Date viernesSanto = agregarDias(domingoPascua, -2);
        fechass[3] = viernesSanto;
        fechass[4] = domingoPascua;
        Date ascencion = agregarDias(domingoPascua, 40);
        Date ascenciondelSenior = siguienteLunes(ascencion);
        fechass[5] = ascenciondelSenior;
        Date Corpus = siguienteLunes(agregarDias(domingoPascua, 61));
        fechass[6] = Corpus;
        Date sagradoCorazon = siguienteLunes(agregarDias(domingoPascua, 68));
        fechass[7] = sagradoCorazon;
        Date sanPedroyPablo = siguienteLunes(new Date(fecha.getYear(), 5, 29));
        fechass[8] = sanPedroyPablo;
        Date asuncionVirgen = siguienteLunes(new Date(fecha.getYear(), 7, 15));
        fechass[9] = asuncionVirgen;
        Date diaRaza = siguienteLunes(new Date(fecha.getYear(), 9, 12));
        fechass[10] = diaRaza;
        Date todosSantos = siguienteLunes(new Date(fecha.getYear(), 10, 1));
        fechass[11] = todosSantos;
        Date independenciaCartagena = siguienteLunes(new Date(fecha.getYear(), 10, 11));
        fechass[12] = independenciaCartagena;

        return fechass;
    }

    public static String comparar(Date[] fechas, Date fecha) {
        SimpleDateFormat f1 = new SimpleDateFormat("EEEE dd/MM/yyyy");
        String respuesta = "la fecha " + f1.format(fecha) + " No es festivo";

        for (int i = 0; i < fechas.length; i++) {

            if (fecha.equals(fechas[i])) {

                respuesta = "la fecha " + f1.format(fecha) + " Si es Festivo";
            }
        }

        return respuesta;

    }

    public static Festivofecha[] listar(Date fecha) {
        Date f = new Date(fecha.getYear(), 1, 1);
        Festivofecha festivo = new Festivofecha();
        festivo.setNombre("Año nuevo");
        festivo.setFecha(new Date(fecha.getYear(), 0, 1));
        Festivofecha[] fechass = new Festivofecha[19];
        fechass[0] = festivo;

        Festivofecha festivo1 = new Festivofecha();
        Date santoreyes = siguienteLunes(new Date(fecha.getYear(), 0, 6));
        festivo1.setNombre("Santo reyes");
        festivo1.setFecha(santoreyes);
        fechass[1] = festivo1;

        Festivofecha festivo2 = new Festivofecha();
        Date sanjose = siguienteLunes(new Date(fecha.getYear(), 2, 19));
        festivo2.setNombre("San Jose");
        festivo2.setFecha(sanjose);
        fechass[2] = festivo2;
        int anio = (fecha.getYear() + 1900);
        Date domingoRamos = getDomingoRamos(anio);
        Date domingoPascua = agregarDias(domingoRamos, 7);

        Festivofecha festivo3 = new Festivofecha();
        Date juevessanto = agregarDias(domingoPascua, -3);
        festivo3.setNombre("Jueves Santo");
        festivo3.setFecha(juevessanto);
        fechass[3] = festivo3;

        Festivofecha festivo4 = new Festivofecha();
        Date viernesSanto = agregarDias(domingoPascua, -2);
        festivo4.setNombre("Viernes Santo");
        festivo4.setFecha(viernesSanto);
        fechass[4] = festivo4;

        Festivofecha festivo5 = new Festivofecha();
        festivo5.setNombre("Domingo pascua");
        festivo5.setFecha(domingoPascua);
        fechass[5] = festivo5;

        Festivofecha festivo6 = new Festivofecha();
        festivo6.setNombre("Dia del trabajo");
        festivo6.setFecha(new Date(fecha.getYear(), 4, 1));
        fechass[6] = festivo6;

        Festivofecha festivo7 = new Festivofecha();
        Date ascencion = agregarDias(domingoPascua, 40);
        Date ascenciondelSenior = siguienteLunes(ascencion);
        festivo7.setNombre("Ascencion del señor");
        festivo7.setFecha(ascenciondelSenior);
        fechass[7] = festivo7;

        Festivofecha festivo8 = new Festivofecha();
        Date Corpus = siguienteLunes(agregarDias(domingoPascua, 61));
        festivo8.setNombre("Corpus Christi");
        festivo8.setFecha(Corpus);
        fechass[8] = festivo8;

        Festivofecha festivo9 = new Festivofecha();
        Date sagradoCorazon = siguienteLunes(agregarDias(domingoPascua, 68));
        festivo9.setNombre("Sagrado corazon");
        festivo9.setFecha(sagradoCorazon);
        fechass[9] = festivo9;

        Festivofecha festivo10 = new Festivofecha();
        Date sanPedroyPablo = siguienteLunes(new Date(fecha.getYear(), 5, 29));
        festivo10.setNombre("San Pedro y Pablo");
        festivo10.setFecha(sanPedroyPablo);
        fechass[10] = festivo10;

        Festivofecha festivo11 = new Festivofecha();
        festivo11.setNombre("Independencia de Colombia");
        festivo11.setFecha(new Date(fecha.getYear(), 6, 20));
        fechass[11] = festivo11;

        Festivofecha festivo12 = new Festivofecha();
        festivo12.setNombre("Batalla de boyaca");
        festivo12.setFecha(new Date(fecha.getYear(), 7, 7));
        fechass[12] = festivo12;

        Festivofecha festivo13 = new Festivofecha();
        Date asuncionVirgen = siguienteLunes(new Date(fecha.getYear(), 7, 15));
        festivo13.setNombre("Asuncion de la Virgen");
        festivo13.setFecha(asuncionVirgen);
        fechass[13] = festivo13;

        Festivofecha festivo14 = new Festivofecha();
        Date diaRaza = siguienteLunes(new Date(fecha.getYear(), 9, 12));
        festivo14.setNombre("Dia de la Raza");
        festivo14.setFecha(diaRaza);
        fechass[14] = festivo14;

        Festivofecha festivo15 = new Festivofecha();
        Date todosSantos = siguienteLunes(new Date(fecha.getYear(), 10, 1));
        festivo15.setNombre("Todos los santos");
        festivo15.setFecha(todosSantos);
        fechass[15] = festivo15;

        Festivofecha festivo16 = new Festivofecha();
        Date independenciaCartagena = siguienteLunes(new Date(fecha.getYear(), 10, 11));
        festivo16.setNombre("Independencia de Cartagena");
        festivo16.setFecha(independenciaCartagena);
        fechass[16] = festivo16;

        Festivofecha festivo17 = new Festivofecha();
        festivo17.setNombre("Inmaculada concepcion");
        festivo17.setFecha(new Date(fecha.getYear(), 11, 8));
        fechass[17] = festivo17;

        Festivofecha festivo18 = new Festivofecha();
        festivo18.setNombre("Navidad");
        festivo18.setFecha(new Date(fecha.getYear(), 11, 25));
        fechass[18] = festivo18;

        return fechass;
    }
}
