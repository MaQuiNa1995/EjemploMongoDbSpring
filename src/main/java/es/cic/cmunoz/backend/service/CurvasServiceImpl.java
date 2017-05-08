package es.cic.cmunoz.backend.service;

import es.cic.cmunoz.backend.dominio.Curvas;
import es.cic.cmunoz.backend.repository.CurvasRepository;
import es.cic.cmunoz.backend.util.Utilidades;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase usada para el llamado a métodos del repository registrando los segundos
 */
@Service
public class CurvasServiceImpl implements CurvasService {

    /**
     * Objeto que contiene todos los métodos necesarios para el tratamiento de
     * la base de datos
     */
    @Autowired
    private CurvasRepository repository;

    /**
     * Objeto que tiene utilitarios necesarios para la generacion de datos
     */
    @Autowired
    private Utilidades utilidad;

    /*
     * Método usado para el select de curvas seleccionandolas por fechas
     *
     * @return arregloFechasMismoDia - Lista de fechas del mismo dia
     */
    @Override
    public List<Curvas> verCincoFechas() {
        String[] arregloFechas = utilidad.generarCincoFechas();

        long antes = Utilidades.conseguirHora();

        List<Curvas> arregloFechasMismoDia=null;
        
        for (String cadenaSacada : arregloFechas) {
            try {
                arregloFechasMismoDia = repository.encontrarFechas(cadenaSacada);
            } catch (NullPointerException e) {
                System.out.println("Esa Fecha NoExiste");
            }
        }
        
        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad(
                "verCincoFechas: " + tiempoSacado / 1000 + " Segundos"
        );
        
        return arregloFechasMismoDia;
    }

    /**
     * Método usado para el select de 6 curvas seleccionandolas por Id Curva
     */
    @Override
    public void verSeisIdCurvas() {
        int[] arregloIds = utilidad.generarArregloIds();

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

        utilidad.registrarActividad(
                "verSeisIdCurvas: " + tiempoSacado / 1000 + " Segundos"
        );
    }

    /**
     * Método usado para la seleccion de curvas seleccionandolos por cups
     *
     * @since 1.8
     */
    @Override
    public void verCincoCups() {
        List<String> listaCups = utilidad.generarArreglosCups();

        long antes = Utilidades.conseguirHora();

        // for (String cupsSacado : listaCups) {
        listaCups.stream().forEach((cupsSacado) -> {
            repository.encontrarCups(cupsSacado).toString();
        });

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad(
                "verCincoCups: " + tiempoSacado / 1000 + " Segundos"
        );
    }

    /**
     * Método usado para el guardado de un millon de registros en base de datos
     */
    @Override
    public void guardarMillon() {

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

        utilidad.registrarActividad(
                "guardarMillon: " + tiempoSacado / 1000 + " Segundos"
        );
    }

    /**
     * Método usado para la seleccion de curvas por patrón
     * @return listaCurvas - Lista de curvas que cumplen el patrón
     */
    @Override
    public List<Curvas> selecionarPorPatron() {

        long antes = Utilidades.conseguirHora();

        List<Curvas> listaCurvas;
        listaCurvas = repository.encontrarCurvasPorPattern("1678", "1");

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad(
                "selecionarPorPatron: " + tiempoSacado / 1000 + " Segundos"
        );

        return listaCurvas;
    }

    /**
     * Método usado para la recuperación de una curva que tenga un cups
     * determinado
     */
    @Override
    public void recuperarUpdate() {

        long antes = Utilidades.conseguirHora();

        repository.encontrarCups("ES0027700000000000000010F");

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad(
                "recuperarUpdate: " + tiempoSacado / 1000 + " Segundos"
        );

    }

    /**
     * Método usado para la actualización de curvas que tengan un Id Curva menor
     * de 100.000
     */
    @Override
    public void updateMenoresDeCienMil() {

        long antes = Utilidades.conseguirHora();

        repository.updateCurvas();

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("updateMenoresDeCienMil: " + tiempoSacado / 1000 + " Segundos");
    }

    /**
     * Método usado para la actualización de una curva
     */
    @Override
    public void updateUnaCurva() {

        long antes = Utilidades.conseguirHora();

        repository.updateUnaCurva();

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad(
                "updateUnaCurva: " + tiempoSacado / 1000 + " Segundos"
        );
    }

}
