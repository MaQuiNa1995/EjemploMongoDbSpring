/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.cic.cmunoz.backend.service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import es.cic.cmunoz.backend.repository.CurvasRepository;
import es.cic.cmunoz.backend.util.Conector;
import es.cic.cmunoz.backend.util.Utilidades;
import java.util.logging.Level;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CurvasServiceImpl {
    @Autowired
    private CurvasRepository repository;

    @Autowired
    private Conector conector;
    
    @Autowired
    private Utilidades utilidad;
    
    public CurvasServiceImpl() {
    }
    
    public void buscarCincoFechas(){
        String fechasArreglo[] = utilidad.generarCincoFechas();
        for (String fechaSacada : fechasArreglo) {
            repository.findByFecha(fechaSacada);
        }
    }
    
    public void buscarCincoIdCurvas(){
        String fechasArreglo[] = utilidad.generarCincoFechas();
        for (String fechaSacada : fechasArreglo) {
            repository.findByFecha(fechaSacada);
        }
    }
}
