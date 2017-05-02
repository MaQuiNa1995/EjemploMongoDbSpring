/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.cmunoz.backend.service;

import es.cic.cmunoz.backend.dominio.Curvas;
import es.cic.cmunoz.backend.repository.CurvasRepository;
import es.cic.cmunoz.backend.util.Conector;
import es.cic.cmunoz.backend.util.Utilidades;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurvasServiceImpl implements CurvasService {

    @Autowired
    public CurvasRepository repository;

    @Autowired
    public Conector conector;

    @Autowired
    public Utilidades utilidad;

    public CurvasServiceImpl() {

    }

    @Override
    public void verCincoFechas() {
        String[] arregloFechas = utilidad.generarCincoFechas();

        long antes = Utilidades.conseguirHora();

        for (String cadenaSacada : arregloFechas) {

            List<Curvas> arregloFechasMismoDia = repository.encontrarFechas(cadenaSacada);

            for (Curvas curvasSacadas : arregloFechasMismoDia) {
                System.out.println(curvasSacadas.toString());
            }

        }

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("verCincoFechas: " + tiempoSacado / 1000 + " Segundos");
    }

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

        utilidad.registrarActividad("verSeisIdCurvas: " + tiempoSacado / 1000 + " Segundos");
    }

    @Override
    public void verCincoCups() {
        List<String> listaCups = utilidad.generarArreglosCups();
        System.out.println("Inicio Select Cinco Cups");

        long antes = Utilidades.conseguirHora();

        for (String cupsSacado : listaCups) {
            System.out.println("----------------------------------------" + cupsSacado);
            System.out.println(repository.encontrarCups(cupsSacado).toString());
        }

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("verCincoCups: " + tiempoSacado / 1000 + " Segundos");
    }

    @Override
    public void guardarMillon() {

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

        utilidad.registrarActividad("guardarMillon: " + tiempoSacado / 1000 + " Segundos");
    }

    @Override
    public void selecionarPorPatron() {

        long antes = Utilidades.conseguirHora();

        List<Curvas> listaCurvas = repository.encontrarCurvasPorPattern("1678", "1");

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("selecionarPorPatron: " + tiempoSacado / 1000 + " Segundos");
        
    }

    @Override
    public void recuperarUpdate() {

        long antes = Utilidades.conseguirHora();

        repository.encontrarCups("ES0027700000000000000010F");

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("recuperarUpdate: " + tiempoSacado / 1000 + " Segundos");

    }

    @Override
    public void updateMenoresDeCienMil() {

        long antes = Utilidades.conseguirHora();

        repository.updateCurvas();

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("updateMenoresDeCienMil: " + tiempoSacado / 1000 + " Segundos");
    }

    @Override
    public void updateUnaCurva() {

        long antes = Utilidades.conseguirHora();

        repository.updateUnaCurva();

        long despues = Utilidades.conseguirHora();

        long tiempoSacado = utilidad.calcularTiempo(antes, despues);

        utilidad.registrarActividad("updateUnaCurva: " + tiempoSacado / 1000 + " Segundos");
    }

}
