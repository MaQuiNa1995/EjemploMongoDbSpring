/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.cmunoz.backend.service;

import org.springframework.stereotype.Service;

/**
 * @autor cmunoz
 * @version 1.0
 */
@Service
public interface CurvasService {

    void guardarMillon();

    void recuperarUpdate();

    void selecionarPorPatron();

    void updateMenoresDeCienMil();

    void verCincoCups();

    void verCincoFechas();

    void verSeisIdCurvas();

    void updateUnaCurva();
    
}
