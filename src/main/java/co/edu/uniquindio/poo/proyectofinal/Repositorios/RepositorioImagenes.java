package co.edu.uniquindio.poo.proyectofinal.Repositorios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javafx.scene.image.Image;

/**
 * Clase encargada de manejar la persistnecia de las imagenes
 */
public class RepositorioImagenes {
    private static RepositorioImagenes INSTANCE;
    private RepositorioImagenes(){
    }
    public static RepositorioImagenes getInstancia(){
        if(INSTANCE == null){
            INSTANCE = new RepositorioImagenes();
        }
        return INSTANCE;
    }

    /*constante con el nombre de la carpeta donde se guardarán las imágenes
    "imagenes/" será una carpeta dentro del directorio raíz del proyecto.*/
    private static final String DIRECTORIO_IMAGENES = "src/main/resources/imagenes/";

    /**
     * Metodo que guarda la imagen en el repositorio de imagenes
     * @param archivoOriginal archivo de imagen que será capturado en la interfaz
     * @return un string que representa la ruta de la imagen
     * @throws IOException
     */
    public static String guardarImagen(File archivoOriginal) throws IOException {
        File directorio = new File(DIRECTORIO_IMAGENES);
        //Crea un objeto File apuntando a la carpeta src/main/resources/imagenes/.
        //Si no existe, la crea usando mkdirs() (crea todos los directorios necesarios).
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        //Genera una ruta relativa unica para cada imagen
        String rutaRelativa = UUID.randomUUID() + "_" + archivoOriginal.getName();
        //Construye la ruta completa del archivo destino como un Path
        Path destino = Paths.get(DIRECTORIO_IMAGENES + rutaRelativa);

        

        //Copia el archivo original a la nueva ruta destino.
        //StandardCopyOption.REPLACE_EXISTING se usa para sobrescribir si ya existe una imagen con el mismo nombre.
        Files.copy(archivoOriginal.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

        return rutaRelativa;
    }

    /**
     * Metodo que busca una imagen según su ruta relativa
     * @param rutaRelativa cadena que representa la ruta relativa de la imagen
     * @return La imagen encontrada
     * @throws Exception si no encuentra la imagen
     */
    public static Image cargarImagen(String rutaRelativa) throws Exception {
        try{
            File file = new File(DIRECTORIO_IMAGENES + rutaRelativa);
            return new Image(file.toURI().toString());
        }catch(Exception e){
            throw new Exception("No se encontro la imagen");
        }
    }

    /**
     * Metodo que permite borrar una imagen segun su ruta relativa
     * @param rutaRelativa la ruta relativa de la imagen
     * @throws Exception si no encuentra la imagen a borrar
     */
    public void eliminarImagen(String rutaRelativa) throws Exception {
        File file = new File(DIRECTORIO_IMAGENES + rutaRelativa);
        if (file.exists()) {
            if (!file.delete()) {
                throw new Exception("No se pudo eliminar la imagen.");
            }
        }
    }
}
