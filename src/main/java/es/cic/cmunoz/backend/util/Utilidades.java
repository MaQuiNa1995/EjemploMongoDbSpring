/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.cic.cmunoz.backend.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 */
@Service
public class Utilidades {

    public Utilidades() {
        super();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public String generarCups(int i) {

        final String PRINCIPIOCUPS = "ES00277000000000";
        final String FINCUPS = "0F";

        StringBuilder sb = new StringBuilder(PRINCIPIOCUPS);

        String cadenaConvertida = annadirCeros(String.valueOf(i));

        sb.append(cadenaConvertida);
        sb.append(FINCUPS);

        String cupsGenerado = sb.toString();

        return cupsGenerado;
    }

    /**
     *
     * @return
     */
    public List<Integer> generarId() {

        List<Integer> mapaIds = new ArrayList<>();

        for (int i = 1; i < 1000001; i++) {
            mapaIds.add(i);
        }

        return mapaIds;
    }

    /**
     *
     * @return
     */
    public List<String> generarFechas() {

        final String ANNO = "2016";
        final int arraymeses[] = generarDiasMeses();

        int registrosCompletados = 0;

        List<String> mapaFechas = new ArrayList<>();

        do {

            int contadorMeses = 1;

            for (int mes : arraymeses) {

                for (int dia = 1; dia < mes + 1; dia++) {

                    StringBuilder fechaCompleta = new StringBuilder();

                    fechaCompleta.append(ANNO);
                    fechaCompleta.append(formatearDiaMes(contadorMeses));
                    fechaCompleta.append(formatearDiaMes(dia));

                    mapaFechas.add(fechaCompleta.toString());
                    if (registrosCompletados < 1000000) {
                        registrosCompletados++;
                    } else {
                        return mapaFechas;
                    }

                }
                contadorMeses++;
            }

        } while (registrosCompletados < 1000000);

        return mapaFechas;
    }

    /**
     *
     * @return
     */
    public String[] generarCincoFechas() {

        final String ANNO = "2016";

        String mapaFechas[] = {
            null, null, null, null, null
        };

        for (int i = 0; i < mapaFechas.length; i++) {

            StringBuilder fechaCompleta = new StringBuilder();

            fechaCompleta.append(ANNO);
            fechaCompleta.append(generarUnDia());
            fechaCompleta.append(generarUnMes());

            mapaFechas[i] = fechaCompleta.toString();
        }

        return mapaFechas;
    }

    private String generarUnDia() {

        Random rand = new Random();
        int dia = rand.nextInt(30) + 1;

        String diaFormateado;
        if (dia < 10) {
            diaFormateado = "0".concat(String.valueOf(dia));
        } else {
            diaFormateado = String.valueOf(dia);
        }

        return diaFormateado;
    }

    private String generarUnMes() {

        Random rand = new Random();
        int mes = rand.nextInt(12) + 1;

        String mesFormateado;
        if (mes < 10) {
            mesFormateado = "0".concat(String.valueOf(mes));
        } else {
            mesFormateado = String.valueOf(mes);
        }

        return mesFormateado;
    }

    /**
     *
     * @return
     */
    public String generarValores() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 24; i++) {
            sb.append("1678;");
        }
        sb.append("1678");

        String magnitudGenerada = sb.toString();

        return magnitudGenerada;

    }

    /**
     *
     * @return
     */
    public String generarFlags() {

        StringBuilder sb = new StringBuilder();
        final int LIMITE = 2;
        Random rand = new Random();
        for (int i = 0; i < 24; i++) {
            sb.append(String.valueOf(rand.nextInt(LIMITE)));
            sb.append(";");
        }
        sb.append(String.valueOf(rand.nextInt(LIMITE)));

        String flagGenerada = sb.toString();

        return flagGenerada;
    }

    /**
     * ------------------------- Utilitarios ----------------------------
     */
    private int[] generarDiasMeses() {

        final int ENERO = 31;
        final int MARZO = 31;
        final int MAYO = 31;
        final int JULIO = 31;
        final int AGOSTO = 31;
        final int OCTUBRE = 31;
        final int DICIEMBRE = 31;

        final int ABRIL = 30;
        final int JUNIO = 30;
        final int SEPTIEMBRE = 30;
        final int NOVIEMBRE = 30;

        final int FEBRERO = 28;

        final int ARREGLOMESES[] = {
            ENERO, FEBRERO, MARZO,
            ABRIL, MAYO, JUNIO,
            JULIO, AGOSTO, SEPTIEMBRE,
            OCTUBRE, NOVIEMBRE, DICIEMBRE
        };

        return ARREGLOMESES;
    }

    private String formatearDiaMes(int diaMes) {
        StringBuilder sb = new StringBuilder();
        if (diaMes < 10) {
            sb.append("0");
        }
        String diaMesFormateado = sb.append(diaMes).toString();

        return diaMesFormateado;
    }

    private String annadirCeros(String cadenaSinCeros) {

        StringBuilder sb = new StringBuilder();

        int cerosAgregar = 7 - cadenaSinCeros.length();

        if (cerosAgregar != 0) {
            for (int i = 0; i < cerosAgregar; i++) {
                sb.append("0");
            }
        }

        String cadenaConvertida = sb.append(cadenaSinCeros).toString();

        return cadenaConvertida;
    }

    public long[] generarArregloIds() {
        final long[] ARREGLO_IDS = {
            1, 200000, 400000,
            600000, 800000,
            1000000, 234567890
        };

        return ARREGLO_IDS;
    }
    
    

    public List<String> generarArreglosCups() {
        Random rand = new Random();
        List<String> arregloRandoms = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String cupsGenerado = generarCups((rand.nextInt(1000000)));
            arregloRandoms.add(cupsGenerado);
        }

        return arregloRandoms;
    }

    public long conseguirHora() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public long calcularTiempo(long antes, long despues) {
        return despues - antes;
    }

}
