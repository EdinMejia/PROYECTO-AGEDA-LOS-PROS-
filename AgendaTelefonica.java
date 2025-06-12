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

// clase exportador a txt 
class Exportador { // Declaramos la clase llamada Exportador
        public static void exportarAArchivo(List<Contacto> contactos, String nombreArchivo) {
            try { // creamos el bloque de codigo 
                FileWriter fw = new FileWriter(nombreArchivo); // esto crea un objeto FileWriter que creara el archivo .txt
                for (Contacto c : contactos) {
                    fw.write("Nombre: " + c.getNombre() +
                            ", Teléfono: " + c.getTelefono() +
                            ", Correo: " + c.getCorreo() +
                            ", Dirección: " + c.getDireccion() + "\n");
                }
                fw.close(); // cerramos el archibo 
                System.out.println("Exportación exitosa a " + nombreArchivo);
            } catch (IOException e) { // si no se culple el bloque anterior se ejecuta este 
                System.out.println("Error al exportar: " + e.getMessage());
            }
        }
    }
