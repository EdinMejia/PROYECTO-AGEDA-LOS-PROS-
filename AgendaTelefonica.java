import java.io.*; 
import java.util.*;
// clase exportador a txt 
public class Exportador {
    public static void exportar(ListaContactos lista, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            Nodo actual = lista.getCabeza();
            while (actual != null) {
                writer.write(actual.contacto.toString() + "\n------------------------\n");
                actual = actual.siguiente;
            }
            System.out.println("Contactos exportados correctamente a " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar contactos: " + e.getMessage());
        }
    }
}
