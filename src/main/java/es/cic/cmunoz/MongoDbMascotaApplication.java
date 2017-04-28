package es.cic.cmunoz;

import es.cic.cmunoz.backend.dominio.Curvas;
import es.cic.cmunoz.backend.repository.CurvasRepository;
import es.cic.cmunoz.backend.util.Conector;
import es.cic.cmunoz.backend.util.Utilidades;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDbMascotaApplication implements CommandLineRunner {

    private static final Logger LOG = Logger.getLogger(MongoDbMascotaApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(MongoDbMascotaApplication.class, args);
    }
    @Autowired
    private CurvasRepository repository;

    @Autowired
    private Utilidades utilidad;

    @Autowired
    private Conector conector;

    @Override
    public void run(String... args) {
//        guardarMillon();
//        verCincoCups();
//        verCincoFechas();
//        verSeisIdCurvas();
selecionarPorPatron();
    }

    private void verCincoFechas() {
        String[] arregloFechas = utilidad.generarCincoFechas();

        System.out.println("Inicio Select Cinco Fechas");

        long antes = Utilidades.conseguirHora();

        for (String cadenaSacada : arregloFechas) {

            List<Curvas> arregloFechasMismoDia = repository.encontrarFechas(cadenaSacada);

            for (Curvas curvasSacadas : arregloFechasMismoDia) {
                System.out.println(curvasSacadas.toString());
            }

        }

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        System.out.println("Han pasado " + tiempoSacado / 1000 + " Segundos");
    }

    private void verSeisIdCurvas() {
        int[] arregloIds = utilidad.generarArregloIds();
        System.out.println("Inicio Select Seis Id Curvas");

        long antes = Utilidades.conseguirHora();

        for (long idSacado : arregloIds) {
            try {
                System.out.println(repository.encontrarIdCurva(idSacado).toString());
            } catch (NullPointerException npe) {
                System.out.println("Ese Id De Curva No Existe");
            }
        }
        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        System.out.println("Despues" + despues + "- Antes: " + antes + " = " + tiempoSacado);

        System.out.println("Han pasado " + tiempoSacado / 1000 + " Segundos");
    }

    private void verCincoCups() {
        List<String> listaCups = utilidad.generarArreglosCups();
        System.out.println("Inicio Select Cinco Cups");

        long antes = Utilidades.conseguirHora();

        for (String cupsSacado : listaCups) {
            System.out.println("----------------------------------------" + cupsSacado);
            System.out.println(repository.encontrarCups(cupsSacado).toString());
        }

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        System.out.println("Han pasado " + tiempoSacado / 1000 + " Segundos");
    }

    private void guardarMillon() {

        System.out.println("Inicio Inser Millon Objetos");

        List<String> listaFechas = utilidad.generarFechas();
        List<Integer> listaId = utilidad.generarId();

        List<Curvas> listaCurvasGuardar = new ArrayList<>();

        for (int i = 1; i < 1000001; i++) {

            Curvas curva = new Curvas();

            curva.setIdCurva(listaId.get(i - 1));
            curva.setCups(utilidad.generarCups(i));
            curva.setMagnitud(7);
            curva.setValores(utilidad.generarValores());
            curva.setFecha(listaFechas.get(i - 1));
            curva.setFlag(utilidad.generarFlags());

            listaCurvasGuardar.add(curva);
        }

        long antes = Utilidades.conseguirHora();
        repository.save(listaCurvasGuardar);

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        System.out.println("Han pasado " + tiempoSacado / 1000 + " Segundos");
    }

    private void selecionarPorPatron() {
        List<Curvas> a = repository.encontrarCurvasPorPattern("1678", "1678", "1", "0");
        
        for (Curvas object : a) {
            System.out.println(object.toString());
        }
    }

}
