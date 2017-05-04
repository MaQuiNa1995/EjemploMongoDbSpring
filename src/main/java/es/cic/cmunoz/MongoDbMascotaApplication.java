package es.cic.cmunoz;

import es.cic.cmunoz.backend.service.CurvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDbMascotaApplication implements CommandLineRunner {
    //si no pones nada se leventa con tomcat de default (implements)

    @Autowired
    private CurvasService curvasService;

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

//        for (int i = 0; i < 38; i++) {
            curvasService.guardarMillon();
//        }
//        curvasService.verCincoCups();
//        curvasService.verCincoFechas();
//        curvasService.verSeisIdCurvas();
//        curvasService.selecionarPorPatron(); 
//        curvasService.updateMenoresDeCienMil();
//        curvasService.updateUnaCurva();
    }

}
