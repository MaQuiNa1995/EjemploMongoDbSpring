/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.cmunoz.backend.repository;

import es.cic.cmunoz.backend.dominio.Curvas;
import java.util.List;

/**
 * @autor cmunoz
 * @version 1.0
 */
public interface CurvasRepositoryCustom {

    public List<Curvas> recuperar(String text);
}
