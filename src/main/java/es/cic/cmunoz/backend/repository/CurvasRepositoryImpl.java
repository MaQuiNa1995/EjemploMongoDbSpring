/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.cmunoz.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @autor cmunoz
 * @version 1.0
 */
public class CurvasRepositoryImpl implements CurvasRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateCurvas() {

        Query consulta = new Query(Criteria.where("Id Curva").lt(100000));

        Update actualizado = new Update();
        actualizado.set("Fecha", "Cambio Fecha Menores De 100000");
        
        mongoTemplate.updateMulti(consulta, actualizado, "JuanchoCurvas");
    }

    @Override
    public void updateUnaCurva() {
        Query consulta = new Query(Criteria.where("Id Curva").is(1));

        Update actualizado = new Update();
        actualizado.set("Cups", "GB0027700000000000000010F");

        mongoTemplate.updateFirst(consulta, actualizado, "JuanchoCurvas");
    }

}
