package es.cic.cmunoz.backend.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.mongodb.client.model.Filters.eq;

/**
 * Tiene métodos para conectarse y manipular una base de
 * datos hecha en mongoDb con el driver 3.4.2
 */
@Service
public class ConectorImpl implements Conector {

    @Autowired
    private Utilidades utilidad;

    /**
     * Logger generico de la clase
     */
    private static final Logger LOG = Logger.getLogger(ConectorImpl.class.getName());

    /**
     * Constantes de la clase: URLBBDD: Direccion Base De Datos PUERTOBBDD:
     * Puerto Base De Datos BASEDATOS_NOMBRE: Nombre Base De Datos
     */
    private static final String URLBBDD = "localhost";
    private static final int PUERTOBBDD = 27017;
    private static final String BASEDATOS_NOMBRE = "pruebaJson";
    private static final String CURVAS_COLECCION = "JuanchoCurvas";

    /**
     * Constructor genérico de clase
     */
    public ConectorImpl() {
        super();
    }

    /**
     * Método usado para la puesta a punto del cliente para la manipulación de
     * la base de datos
     *
     * @return conexion - objeto que contiene la conexión a la base de datos
     */
    @Override
    public MongoDatabase prepararCliente() {

        LOG.log(Level.FINE, "Preparando cliente para la conexión a la base de datos");

        /**
         * Creacion del objeto mongoClient para la puesta a punto de la conexión
         * con la base de datos
         */
        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

            /**
             * llamada al metodo conexionBaseDatos(String) para recibir la
             * conexión con la base de datos
             */
            MongoDatabase conexion = conexionBaseDatos(mongoClient);
            LOG.log(Level.INFO, "Conexion con la base de datos exitosa");

            /**
             * Retornamos la conexión
             */
            return conexion;
        }

    }

    /**
     * Método usado para la conexión con la base de datos
     *
     * @param cliente es el objeto que lleva la configuración de la base de
     * datos
     * @return conexionBaseDatos - es el objeto usado para retornar la conexión
     */
    private MongoDatabase conexionBaseDatos(MongoClient cliente) {

        MongoDatabase conexionBaseDatos = cliente.getDatabase(BASEDATOS_NOMBRE);

        return conexionBaseDatos;
    }

    /**
     * Método usado para la puesta a punto del cliente para la manipulación de
     * la base de datos
     *
     * @return conexion - objeto que contiene la conexión a la base de datos
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public DB prepararClienteDeprecado() {

        LOG.log(Level.FINE, "Preparando cliente deprecado para la conexión a la base de datos");

        /**
         * Creacion del objeto mongoClient para la puesta a punto de la conexión
         * con la base de datos
         */
        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

            /**
             * llamada al metodo conexionBaseDatosDeprecado(String) para recibir
             * la conexión con la base de datos
             */
            DB conexion = conexionBaseDatosDeprecado(mongoClient);
            LOG.log(Level.INFO, "Conexion deprecada con la base de datos exitosa");

            /**
             * Retornamos la conexión
             */
            return conexion;
        }

    }

    /**
     * Método usado para la conexión con la base de datos
     *
     * @param cliente es el objeto que lleva la configuración de la base de
     * datos
     * @return conexionBaseDatos - es el objeto usado para retornar la conexión
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    private DB conexionBaseDatosDeprecado(MongoClient cliente) {

        DB conexionBaseDatos = cliente.getDB(BASEDATOS_NOMBRE);

        return conexionBaseDatos;
    }

    /**
     * Metodo usado para el guardado de un objeto escrito a fuego en el codigo
     *
     * @param nombreColeccion String que contiene el nombre de la colección
     * @return exito - Variable de control para indicar el exito de la operación
     */
    @Override
    public boolean guardarObjetoPredefinido(String nombreColeccion) {

        boolean exito = false;

        try {

            /**
             * Creacion del objeto mongoClient para la puesta a punto de la
             * conexión con la base de datos
             */
            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

                /**
                 * llamada al metodo conexionBaseDatos(String) para recibir la
                 * conexión con la base de datos
                 */
                MongoDatabase conexion;
                conexion = conexionBaseDatos(mongoClient);
                LOG.log(Level.INFO, "Conexion con la base de datos exitosa");

                /**
                 * Busqueda de la coleccion donde queremos guardar el objeto
                 * anterior
                 */
                MongoCollection<Document> coleccionGuardar;
                coleccionGuardar = conexion.getCollection(nombreColeccion);

                LOG.log(Level.FINE, "Numero de colecciones en el documento: {0}", coleccionGuardar.count());

                /**
                 * Creación del objeto donde se guardará las clave/valor que
                 * queramos
                 */
                Document objetoGuardar = new Document();
                objetoGuardar.put("nombre", "Janna");
                objetoGuardar.put("rol", "Apoyo");
                objetoGuardar.put("maestria", 7);

                /**
                 * Guardado del objeto en la colección
                 */
                coleccionGuardar.insertOne(objetoGuardar);

                exito = true;
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Excepcion Al Insertar El Objeto Predefinido Razón: {0}", e.getMessage());
        }

        return exito;

    }

    /**
     * Metodo usado para borrar una base de dato entera
     *
     * @return exito - Variable de control para indicar el exito de la operación
     */
    @Override
    public boolean borrarBaseDatos() {
        boolean exito = false;

        try {
            /**
             * preparación de la conexion a la base de datos
             */
            MongoDatabase baseDeDatos = prepararCliente();
            /**
             * borrado de la base de datos
             */
            baseDeDatos.drop();

            exito = true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Excepcion Al Borrar Base De Datos Razón: {0}", e.getMessage());
        }

        return exito;
    }

    /**
     * Metodo usado para la obtención de los nombres de la base de datos en
     * forma de lista
     *
     * @return listaNombresBaseDatos - lista de los nombres de las bases de
     * datos
     */
    @Override
    public List<String> verNombresBasesDatos() {

        List<String> listaConvertida;

        /**
         * Preparación del cliente de la base de datos
         */
        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

            /**
             * Guardado de la lista de nombres de la base de datos
             */
            MongoIterable<String> listaNombresBaseDatos = mongoClient.listDatabaseNames();

            /**
             * Creación De Una Lista para convertir el MongoIterable así no da
             * excepción porque la conexión esté cerrada
             */
            listaConvertida = new ArrayList<>();

            /**
             * Sacamos cada valor y lo seteamos a la lista creada anteriormente
             */
            for (String nombreScado : listaNombresBaseDatos) {
                listaConvertida.add(nombreScado);
            }

            /**
             * Retorno de la lista
             */
            return listaConvertida;
        }

    }

    /**
     * Método usado para la recuperación en forma de SET de las colecciones de
     * la base de datos
     *
     * @return mapaColecciones - Set de los nombres de las bases de datos
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public Set<String> verColeccionesBaseDatos() {

        /**
         * Creacion del objeto mongoClient para la puesta a punto de la conexión
         * con la base de datos
         */
        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

            /**
             * llamada al metodo conexionBaseDatosDeprecado(String) para recibir
             * la conexión con la base de datos
             */
            DB baseDatos = conexionBaseDatosDeprecado(mongoClient);

            /**
             * Guardado de los nombres de colecciones de la base de datos
             */
            Set<String> mapaColecciones = baseDatos.getCollectionNames();

            /**
             * retorno del objeto SET anterior
             */
            return mapaColecciones;
        }
    }

    /**
     * Método usado para el borrado de los registros de una colección
     *
     * @param nombreColeccion nombre de la coleccion a limpiar
     * @return exito - Variable de control para indicar el exito de la operación
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public boolean eliminarColeccion(String nombreColeccion) {
        boolean exito = false;
        /**
         * Creacion del objeto mongoClient para la puesta a punto de la conexión
         * con la base de datos
         */
        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {
            try {

                /**
                 * llamada al metodo conexionBaseDatosDeprecado(String) para
                 * recibir la conexión con la base de datos
                 */
                DB baseDatos = conexionBaseDatosDeprecado(mongoClient);

                /**
                 * Busqueda de la coleccion a limpiar
                 */
                DBCollection coleccionEncontrada = baseDatos.getCollection(nombreColeccion);

                /**
                 * Limpiado/borrado de la coleccion
                 */
                coleccionEncontrada.drop();

                exito = true;

            } catch (Exception e) {
                LOG.log(Level.WARNING, "Excepcion Al Limpiar La Coleccion Razon: {0}", e.getMessage());
            }
            return exito;
        }
    }

    /**
     * Método usado para comprobar la existencia de una coleccion
     *
     * @param coleccionBuscar nombre de la coleccion a borrar
     * @return exito - Variable de control para indicar el exito de la operación
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public boolean existeColeccion(String coleccionBuscar) {

        boolean exito = false;

        try {

            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {
                /**
                 * llamada al metodo conexionBaseDatosDeprecado(String) para
                 * recibir la conexión con la base de datos
                 */
                DB baseDatos = conexionBaseDatosDeprecado(mongoClient);

                /**
                 * Busqueda de la coleccion a limpiar
                 */
                Set<String> listaNombresColecciones = baseDatos.getCollectionNames();

                for (String coleccionSacada : listaNombresColecciones) {
                    if (coleccionSacada.equalsIgnoreCase(coleccionBuscar)) {
                        exito = true;
                        break;
                    }
                }

                return exito;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LOG.log(Level.WARNING, "Excepcion Al Comprobar Si Existe La Coleccion Razon: {0}", e.getMessage());
        }

        return exito;

    }

    /**
     * Método que imprime en pantalla el contenido de una colección
     *
     * @param coleccionBuscar nombre de la coleccion a consultar
     * @return listaInfoColeccion - lista que tiene el contenido de una
     * colección
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public List<DBObject> verColeccionDeprecado(String coleccionBuscar) {
        List<DBObject> listaInfoColeccion = null;

        boolean exito = false;

        try {

            /**
             * Creación de la lista donde guardamos la información de la
             * colección
             */
            listaInfoColeccion = new ArrayList<>();

            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

                /**
                 * llamada al metodo conexionBaseDatosDeprecado(String) para
                 * recibir la conexión con la base de datos
                 */
                DB baseDatos = conexionBaseDatosDeprecado(mongoClient);

                /**
                 * Busqueda de la coleccion
                 */
                DBCollection coleccionEncontrada = baseDatos.getCollection(coleccionBuscar);

                /**
                 * Creación del cursor que recorrerá la consulta
                 */
                try (DBCursor cursor = coleccionEncontrada.find()) {

                    /**
                     * añadido de los objetos que encontramos gracias al cursor
                     * a la lista a retornar
                     */
                    while (cursor.hasNext()) {
                        listaInfoColeccion.add(cursor.next());
                    }
                }
                /**
                 * Seteamos una variable booleana para controlar el exito de la
                 * operación
                 */
                exito = true;
            }

        } catch (Exception e) {
            exito = false;
            LOG.log(Level.WARNING, "Excepcion Al Recuperar Datos La Coleccion, Razon: {0}", e.getMessage());
        } finally {
            /**
             * Si hay excepción seteamos exito a false y la lista a null para
             * que no de falsos positivos en otro caso, no hará nada, y
             * devolverá normalmente la lista con los valores sacados
             */
            if (!exito) {
                listaInfoColeccion = null;
            }
        }

        return listaInfoColeccion;
    }

    /**
     * Método usado para el update de un registro de una colección buscando por
     * clave y valor agregando un nuevo registro
     *
     * @param nombreColeccion String que contiene el nombre de una colección
     * @param buscarClave String que contiene la clave de la cual buscar el
     * objeto que queremos updatear
     * @param buscarValor String que contiene el valor de la cual buscar el
     * objeto que queremos updatear
     * @param claveNueva String que contiene la clave nueva
     * @param valorNuevo String que contiene el valor nuevo
     * @return exito - Variable de control para indicar el exito de la operación
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public boolean anadirCampo(String nombreColeccion, String buscarClave,
            String buscarValor, String claveNueva, String valorNuevo) {

        boolean exito = false;
        try {
            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {
                /**
                 * llamada al metodo conexionBaseDatosDeprecado(String) para
                 * recibir la conexión con la base de datos
                 */
                DB baseDatos = conexionBaseDatosDeprecado(mongoClient);

                /**
                 * Busqueda de la coleccion
                 */
                DBCollection coleccionEncontrada;
                coleccionEncontrada = baseDatos.getCollection(nombreColeccion);

                /**
                 * Creación de un objeto para la preparación para la inserción
                 * de un nuevo campo
                 */
                BasicDBObject nuevoCampo;
                nuevoCampo = new BasicDBObject();
                nuevoCampo.append(
                        "$set",
                        new BasicDBObject().append(claveNueva, valorNuevo)
                );

                /**
                 * Busca el/los registro/s con la clave y valor indicadas
                 */
                BasicDBObject campoFusionar = new BasicDBObject();
                campoFusionar.append(buscarClave, buscarValor);

                /**
                 * Realización de la actualización
                 */
                coleccionEncontrada.updateMulti(campoFusionar, nuevoCampo);
                exito = true;
            }
        } catch (Exception e) {

            LOG.log(Level.WARNING, "Excepcion Al Recuperar Datos La Coleccion, Razon: {0}", e.getMessage());
        }

        return exito;
    }

    /**
     * Método para la eliminación de registros que sean igual a algún elemento
     * de la lista que pasamos como parametro
     *
     * @param nombreColeccion Nombre de la colección en la que hacer el delete
     * @param claveBuscar Clave del registro de la que buscar
     * @param lista Lista de valores de los cuales queremos borrar todos los que
     * encuentre
     * @return exito - Variable de control para indicar el exito de la operación
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public boolean deleteRegistroContenidoEnLista(String nombreColeccion,
            String claveBuscar, List<String> lista) {

        boolean exito = false;

        try {
            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {
                /**
                 * llamada al metodo conexionBaseDatosDeprecado(String) para
                 * recibir la conexión con la base de datos
                 */
                DB baseDatos = conexionBaseDatosDeprecado(mongoClient);

                /**
                 * Busqueda de la coleccion
                 */
                DBCollection coleccionEncontrada;
                coleccionEncontrada = baseDatos.getCollection(nombreColeccion);

                /**
                 * Busca el/los registro/s con la clave y valor indicadas
                 */
                BasicDBObject query = new BasicDBObject();
                query.put(claveBuscar, new BasicDBObject("$in", lista));

                /**
                 * eliminación del registro que encontró la query anterior
                 */
                coleccionEncontrada.remove(query);
                exito = true;
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Excepcion Al Hacer Remove, Razon: {0}", e.getMessage());
        }

        return exito;
    }

    /**
     * Método usado para el borrado de registros con una determinada condición
     *
     * @param nombreColeccion Nombre de la colección en la que hacer el delete
     * @param claveBuscar Clave del registro de la que buscar
     * @param valorBuscar Valor del registro de la que buscar
     * @param condicion String de la condición son iguales a bash "$gt" (greater
     * than)
     * @return exito - Variable de control para indicar el exito de la operación
     * @deprecated No se recomienda el uso de esta funcion ya que usa elementos
     * deprecados que podrían ser eliminados en un futuro
     */
    @Deprecated
    @Override
    public boolean deleteRegistroCondicion(String nombreColeccion, String claveBuscar,
            String valorBuscar, String condicion) {

        boolean exito = false;

        try {

            /**
             * Preparación del cliente para la manipulación de la base de datos
             */
            DB baseDatos = prepararClienteDeprecado();

            /**
             * Busqueda de la coleccion
             */
            DBCollection coleccionEncontrada;
            coleccionEncontrada = baseDatos.getCollection(nombreColeccion);

            /**
             * Busca el/los registro/s con la clave y valor indicadas
             */
            BasicDBObject query = new BasicDBObject();
            query.put(claveBuscar, new BasicDBObject(condicion, valorBuscar));

            /**
             * eliminación del registro que encontró la query anterior
             */
            coleccionEncontrada.remove(query);

            exito = true;

        } catch (Exception e) {
            LOG.log(Level.WARNING, "Excepcion Al Hacer Remove, Razon: {0}", e.getMessage());
        }

        return exito;
    }

    /**
     * Método usado para el guardado de un millón de registros uno a uno
     */
    public void guardarMillonUnoAUno() {

        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

            MongoDatabase conexion;
            conexion = conexionBaseDatos(mongoClient);

            MongoCollection<Document> coleccionGuardar;
            coleccionGuardar = conexion.getCollection(CURVAS_COLECCION);

            List<String> listaFechas = utilidad.generarFechas();
            List<Integer> listaId = utilidad.generarId();

            for (int i = 1; i < 1000001; i++) {

                Document objetoGuardar = new Document();

                objetoGuardar.put("Id Curva", listaId.get(i - 1));
                objetoGuardar.put("Cups", utilidad.generarCups(i));
                objetoGuardar.put("Magnitud", 7);
                objetoGuardar.put("Valores", utilidad.generarValores());
                objetoGuardar.put("Fecha", listaFechas.get(i - 1));
                objetoGuardar.put("Flag", utilidad.generarFlags());

                coleccionGuardar.insertOne(objetoGuardar);
            }
        }
    }

    /**
     * Método usado para el guardado de un millón de registros a traves de un
     * hashmap
     */
    public void guardarMillonHashmap() {

        try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

            MongoDatabase conexion;
            conexion = conexionBaseDatos(mongoClient);

            MongoCollection<Document> coleccionGuardar;
            coleccionGuardar = conexion.getCollection("JuanchoCurvasHashmap");

            List<String> listaFechas = utilidad.generarFechas();
            List<Integer> listaId = utilidad.generarId();

            for (int i = 1; i < 1000001; i++) {

                Map<String, String> mapa1 = new HashMap<>();
                Map<String, Integer> mapa2 = new HashMap<>();

                mapa2.put("Id Curva", listaId.get(i - 1));
                mapa1.put("Cups", utilidad.generarCups(i));
                mapa1.put("Magnitud", utilidad.generarValores());
                mapa1.put("Fecha", listaFechas.get(i - 1));
                mapa1.put("Valores", utilidad.generarFlags());

                Document objetoGuardar = new Document();
                objetoGuardar.putAll(mapa2);
                objetoGuardar.putAll(mapa1);
                coleccionGuardar.insertOne(objetoGuardar);
            }
        }
    }

    /**
     * Método usado para la select de ids predeterminadas
     */
    public void selectIds() {

        long antes = utilidad.conseguirHora();

        LOG.info("-------------------- Empiezo a hacer las selects --------------------");
        LOG.info("***********Select *****************");

        int[] arregloIds = utilidad.generarArregloIds();
        for (int i = 0; i < arregloIds.length; i++) {

            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

                MongoDatabase conexion;
                conexion = conexionBaseDatos(mongoClient);

                MongoCollection<Document> coleccionEncontrada;
                coleccionEncontrada = conexion.getCollection(CURVAS_COLECCION);

                Document resultadoSelect = coleccionEncontrada.find(eq("Id Curva", arregloIds[i])).first();

                if (resultadoSelect != null) {
                    LOG.log(Level.INFO, "*{0}*", resultadoSelect.toJson());
                } else {
                    LOG.log(Level.INFO, "|No hay elementos con el siguiente Id:", String.valueOf(i) + " |");
                }
            }
        }
        long despues = utilidad.conseguirHora();
        LOG.info("****************************");

        long tiempoTranscurrido = utilidad.calcularTiempo(antes, despues);
        LOG.log(Level.INFO, "----------------- Han Pasado: {0} segs ------------------", tiempoTranscurrido);
    }

    /**
     * Método usado para la select de cups predeterminadas
     */
    public void selectCups() {
        long antes = utilidad.conseguirHora();

        LOG.info("-------------------- Empiezo a hacer las selects De Cups --------------------");
        LOG.info("***********Select CUPS*****************");

        List<String> arregloIds = utilidad.generarArreglosCups();
        for (String arregloId : arregloIds) {

            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

                MongoDatabase conexion;
                conexion = conexionBaseDatos(mongoClient);

                MongoCollection<Document> coleccionEncontrada;
                coleccionEncontrada = conexion.getCollection(CURVAS_COLECCION);

                Document resultadoSelect = coleccionEncontrada.find(eq("Id Curva", arregloId)).first();

                if (resultadoSelect != null) {
                    LOG.log(Level.INFO, "*{0}*", resultadoSelect.toJson());
                } else {
                    LOG.log(Level.INFO, "|No hay elementos con el siguiente Cups:", arregloId + " |");
                }
            }
        }

        long despues = utilidad.conseguirHora();
        LOG.info("****************************");

        long tiempoTranscurrido = utilidad.calcularTiempo(antes, despues);
        LOG.log(Level.INFO, "----------------- Han Pasado: {0} segs ------------------", tiempoTranscurrido);
    }

    /**
     * Método usado para la select de fechas
     */
    public void selectFechas() {
        String fechasArreglo[] = utilidad.generarCincoFechas();
        for (String fechaSacada : fechasArreglo) {
            try (MongoClient mongoClient = new MongoClient(URLBBDD, PUERTOBBDD)) {

                MongoDatabase conexion;
                conexion = conexionBaseDatos(mongoClient);

                MongoCollection<Document> coleccionEncontrada;
                coleccionEncontrada = conexion.getCollection(CURVAS_COLECCION);

                FindIterable<Document> resultadoSelect = coleccionEncontrada.find(eq("Fecha", fechaSacada));

                if (resultadoSelect != null) {
                    for (Document document : resultadoSelect) {
                        LOG.log(Level.INFO, "*{0}*", document.toJson());
                    }

                } else {
                    LOG.log(Level.INFO, "|No hay elementos con la siguiente Fecha:", fechaSacada + " |");
                }
            }
        }
    }

    /**
     * Método usado para el gurdado de dos millones de registros y contabilizar
     * dos métodos cada uno mete un millón
     */
    @Override
    public void guardadoUnMillon() {

        LOG.info("----------------- Inicio Guardar Millon 1 a 1 -----------------");
        long antes = utilidad.conseguirHora();
        guardarMillonUnoAUno();
        long despues = utilidad.conseguirHora();

        long tiempoTranscurrido = utilidad.calcularTiempo(antes, despues);
        LOG.log(Level.INFO, "----------------- Han Pasado: {0} segs ------------------", tiempoTranscurrido);

//        LOG.info("----------------- Inicio Guardar HashMap -----------------");
//        antes = utilidad.conseguirHora();
//        guardarMillonHashmap();
//        despues = utilidad.conseguirHora();
//
//        tiempoTranscurrido = utilidad.calcularTiempo(antes, despues);
//        LOG.log(Level.INFO, "----------------- Han Pasado: {0} segs ------------------", tiempoTranscurrido);
    }

}
