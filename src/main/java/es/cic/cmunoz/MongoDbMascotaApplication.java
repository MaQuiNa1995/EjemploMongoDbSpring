package es.cic.cmunoz;

import es.cic.cmunoz.backend.service.CurvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDbMascotaApplication implements CommandLineRunner {
    
    @Autowired
    private CurvasService curvasService;
    
    public static void main(String[] args) {
        SpringApplication.run(MongoDbMascotaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        curvasService.guardarMillon();
        curvasService.verCincoCups();
        curvasService.verCincoFechas();
        curvasService.verSeisIdCurvas();
        curvasService.selecionarPorPatron(); 
        curvasService.updateMenoresDeCienMil();
        curvasService.updateUnaCurva();
    }

    
}
