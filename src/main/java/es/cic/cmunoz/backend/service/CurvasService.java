package es.cic.cmunoz.backend.service;

import org.springframework.stereotype.Service;

/**
 * Clase usada para el llamado a métodos del repository registrando los segundos
 */
@Service
public interface CurvasService {

    /**
     * Método usado para el guardado de un millon de registros en base de datos
     */
    void guardarMillon();

    /**
     * Método usado para la recuperación de una curva que tenga un cups
     * determinado
     */
    void recuperarUpdate();

    /**
     * Método usado para la seleccion de curvas por patrón
     */
    List<Curvas> selecionarPorPatron();

    /**
     * Método usado para la actualización de curvas que tengan un Id Curva menor
     * de 100.000
     */
    void updateMenoresDeCienMil();

    /**
     * Método usado para la seleccion de curvas seleccionandolos por cups
     *
     * @since 1.8
     */
    void verCincoCups();

    /**
     * Método usado para el select de curvas seleccionandolas por fechas
     */
    List<Curvas> verCincoFechas();

    /**
     * Método usado para el select de 6 curvas seleccionandolas por Id Curva
     */
    void verSeisIdCurvas();

    /**
     * Método usado para la actualización de una curva
     */
    void updateUnaCurva();

}
