import java.io.*; 
import java.util.*;
// CLASE NodoLista enlazada 
class NodoLista {
    Contacto contacto;
    NodoLista siguiente;

    public NodoLista(Contacto c) {
        contacto = c;
        siguiente = null;
    }
}

// CLASE Exportador a .txt
class Exportador {
    public static void exportarAArchivo(List<Contacto> contactos, String nombreArchivo) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            for (Contacto c : contactos) {
                fw.write("Nombre: " + c.getNombre() +
                         ", Teléfono: " + c.getTelefono() +
                         ", Correo: " + c.getCorreo() +
                         ", Dirección: " + c.getDireccion() + "\n");
            }
            System.out.println("Exportación exitosa a '" + nombreArchivo + "'");
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
}
