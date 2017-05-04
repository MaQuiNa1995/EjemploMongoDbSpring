package es.cic.cmunoz;

import es.cic.cmunoz.backend.dominio.MusicGroup;
import es.cic.cmunoz.backend.service.CurvasService;
import es.cic.cmunoz.backend.service.MusicGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import es.cic.cmunoz.backend.service.MusicGroupService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class MongoDbMascotaApplication implements CommandLineRunner {
    //si no pones nada se leventa con tomcat de default (implements)

    @Autowired
    private CurvasService curvasService;

    @Autowired
    private MusicGroupService musicaService;

    /**
     * Método principal para la ejecución del programa
     *
     * @param args Strings que se podrían pasar al main al ejecutar la
     * aplicación
     */
    public static void main(String[] args) {
        SpringApplication.run(MongoDbMascotaApplication.class, args);
    }

    /**
     * Método que inica la aplicación
     *
     * @param args Strings que se podrían pasar al main al ejecutar la
     * aplicación
     */
    @Override
    public void run(String... args) {
        String[] memberList = new String[3];
        memberList[0] = "typeA";
        memberList[1] = "typeB";
        memberList[2] = "typec";

        MusicGroup grupo = new MusicGroup();
        
        
        grupo.setType("888888888889");
        grupo.setTitle("hhhhhhh");
        grupo.setIntegrantes(memberList);
        musicaService.storeGroup(grupo);

//        for (int i = 0; i < 29; i++) {
//            
//        }
//        curvasService.guardarMillon();
//        curvasService.verCincoCups();
//        curvasService.verCincoFechas();
//        curvasService.verSeisIdCurvas();
//        curvasService.selecionarPorPatron(); 
//        curvasService.updateMenoresDeCienMil();
//        curvasService.updateUnaCurva();
    }

}
