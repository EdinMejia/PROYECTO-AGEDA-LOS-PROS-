import java.io.*;
import java.util.*;
// ListaEnlazada.java
public class ListaEnlazada {
    NodoLista cabeza;

    public void agregar(Contacto contacto) {
        NodoLista nuevo = new NodoLista(contacto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public Contacto buscarPorNombre(String nombre) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.contacto.nombre.equalsIgnoreCase(nombre)) {
                return actual.contacto;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Contacto buscarPorCorreoTelefono(String dato) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.contacto.correo.equalsIgnoreCase(dato) || actual.contacto.telefono.equalsIgnoreCase(dato)) {
                return actual.contacto;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminar(String nombre) {
        NodoLista actual = cabeza;
        NodoLista anterior = null;

        while (actual != null) {
            if (actual.contacto.nombre.equalsIgnoreCase(nombre)) {
                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public void listar() {
        NodoLista actual = cabeza;
        while (actual != null) {
            System.out.println(actual.contacto);
            actual = actual.siguiente;
        }
    }
}
