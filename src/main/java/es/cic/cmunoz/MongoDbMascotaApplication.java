package es.cic.cmunoz;

import es.cic.cmunoz.backend.dominio.Curvas;
import es.cic.cmunoz.backend.repository.CurvasRepository;
import es.cic.cmunoz.backend.util.Conector;
import es.cic.cmunoz.backend.util.Utilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDbMascotaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbMascotaApplication.class, args);
    }
    @Autowired
    private CurvasRepository repository;
    
    @Autowired
    private Utilidades utilidad;
    
//    @Autowired
//    private Conector conector;

    @Override
    public void run(String... args) {
        
//        conector.guardadoUnMillon();

        //repository.deleteAll();
        // fetch all customers
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (Curvas curvaSacada : repository.findAll()) {
//            System.out.println(curvaSacada.toString());
//        }

        System.out.println("Customer found with magnitud Valores:");
        System.out.println("--------------------------------");
        System.out.println(repository.encontrarIdCurva(5).toString());
        System.out.println(repository.encontrarFecha("20160615").toString());
        System.out.println(repository.encontrarCups("ES0027700000000000600050F").toString());

    }
    
    public void verCincoFechas(){
        String [] arregloFechas = utilidad.generarCincoFechas();
        
        for (String cadenaSacada : arregloFechas) {
            repository.encontrarFecha(cadenaSacada);
        }
    }
        public void verCincoIdCurvas(){
        long [] arregloIds = utilidad.generarArregloIds();
        
        for (long idSacado : arregloIds) {
            repository.encontrarIdCurva((int)(idSacado)).toString();
        }
    }
}
