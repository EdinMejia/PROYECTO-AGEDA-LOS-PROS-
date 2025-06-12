import java.io.*;
import java.util.*;

// CLASE PRINCIPAL
public class AgendaContacto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaContactos agenda = new ListaContactos();

        while (true) {
            try {
                System.out.println("\n===============================");
                System.out.println("     AGENDA DE CONTACTOS");
                System.out.println("===============================");
                System.out.println("1. Agregar contacto");
                System.out.println("2. Buscar por nombre");
                System.out.println("3. Buscar por correo/teléfono");
                System.out.println("4. Editar contacto");
                System.out.println("5. Eliminar contacto");
                System.out.println("6. Exportar a archivo");
                System.out.println("7. Salir");
                System.out.println("8. Mostrar contactos ordenados");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(sc.nextLine());

                System.out.println("\n--------------------------------");

                switch (opcion) {
                    case 1:
                        System.out.println(">> Agregar nuevo contacto");
                        System.out.print("Nombre completo: ");
                        String nombre = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String tel = sc.nextLine();
                        System.out.print("Correo: ");
                        String mail = sc.nextLine();
                        System.out.print("Dirección: ");
                        String dir = sc.nextLine();
                        agenda.agregar(new Contacto(nombre, tel, mail, dir));
                        break;

                    case 2:
                        System.out.print("Buscar por nombre (completo o parcial): ");
                        List<Contacto> encontrados = agenda.buscarPorNombre(sc.nextLine());
                        if (encontrados.isEmpty()) {
                            System.out.println("No se encontraron contactos.");
                        } else {
                            System.out.println("Resultados encontrados:");
                            for (Contacto c : encontrados) {
                                mostrarContacto(c);
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Correo o teléfono: ");
                        Contacto c2 = agenda.buscarPorDato(sc.nextLine());
                        if (c2 != null)
                            mostrarContacto(c2);
                        else
                            System.out.println("No se encontró ningún contacto con ese dato.");
                        break;

                    case 4:
                        System.out.print("Nombre del contacto a editar: ");
                        List<Contacto> editar = agenda.buscarPorNombre(sc.nextLine());
                        if (editar.isEmpty()) {
                            System.out.println("Contacto no encontrado.");
                        } else {
                            Contacto c3 = editar.get(0);

                            // Guardar datos anteriores
                            String telAntiguo = c3.getTelefono();
                            String correoAntiguo = c3.getCorreo();

                            System.out.print("Nuevo teléfono: ");
                            c3.setTelefono(sc.nextLine());
                            System.out.print("Nuevo correo: ");
                            c3.setCorreo(sc.nextLine());
                            System.out.print("Nueva dirección: ");
                            c3.setDireccion(sc.nextLine());

                            // Actualizar claves del hash
                            agenda.actualizarIndices(c3, telAntiguo, correoAntiguo);

                            System.out.println("Contacto actualizado correctamente.");
                        }
                        break;

                    case 5:
                        System.out.print("Nombre del contacto a eliminar: ");
                        if (agenda.eliminar(sc.nextLine()))
                            System.out.println("Eliminado correctamente.");
                        else
                            System.out.println("No se encontró el contacto.");
                        break;

                    case 6:
                        Exportador.exportarAArchivo(agenda.obtenerTodos(), "contactos.txt");
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        return;

                    case 8:
                        System.out.println(">> Contactos ordenados alfabéticamente:");
                        agenda.mostrarOrdenadoPorNombre();
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

                System.out.println("--------------------------------");

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    // Método auxiliar para imprimir contactos bonito
    public static void mostrarContacto(Contacto c) {
        System.out.println("--------------------------------");
        System.out.println("Nombre   : " + c.getNombre());
        System.out.println("Teléfono : " + c.getTelefono());
        System.out.println("Correo   : " + c.getCorreo());
        System.out.println("Dirección: " + c.getDireccion());
    }
}


// CLASE Contacto
class Contacto {
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;

    public Contacto(String nombre, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.telefono = Cifrado.cifrarCesar(telefono, 3);
        this.correo = Cifrado.cifrarCesar(correo, 3);
        this.direccion = direccion;

        System.out.println("\n>> Contacto agregado:");
        System.out.println("Teléfono original : " + telefono + " -> cifrado: " + this.telefono);
        System.out.println("Correo original   : " + correo + " -> cifrado: " + this.correo);
        System.out.println("Dirección         : " + this.direccion);
    }

    public String getNombre() { return nombre; }
    public String getTelefono() { return Cifrado.descifrarCesar(telefono, 3); }
    public String getCorreo() { return Cifrado.descifrarCesar(correo, 3); }
    public String getDireccion() { return direccion; }

    public void setTelefono(String telefono) {
        this.telefono = Cifrado.cifrarCesar(telefono, 3);
    }

    public void setCorreo(String correo) {
        this.correo = Cifrado.cifrarCesar(correo, 3);
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

// CLASE NodoLista
class NodoLista {
    Contacto contacto;
    NodoLista siguiente;

    public NodoLista(Contacto c) {
        contacto = c;
        siguiente = null;
    }
}



// CLASE ListaContactos
class ListaContactos {
    NodoLista cabeza = null;
    HashMap<String, Contacto> hashBusqueda = new HashMap<>();
    ArbolContactos arbol = new ArbolContactos();

    public void agregar(Contacto c) {
        NodoLista nuevo = new NodoLista(c);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        hashBusqueda.put(c.getCorreo(), c);
        hashBusqueda.put(c.getTelefono(), c);
        arbol.insertar(c);
    }

    public List<Contacto> buscarPorNombre(String nombreParcial) {
        List<Contacto> resultados = new ArrayList<>();
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.contacto.getNombre().toLowerCase().contains(nombreParcial.toLowerCase())) {
                resultados.add(actual.contacto);
            }
            actual = actual.siguiente;
        }
        return resultados;
    }

    public Contacto buscarPorDato(String dato) {
        return hashBusqueda.getOrDefault(dato, null);
    }

    public boolean eliminar(String nombre) {
        NodoLista actual = cabeza;
        NodoLista anterior = null;

        while (actual != null) {
            if (actual.contacto.getNombre().equalsIgnoreCase(nombre)) {
                if (anterior == null) cabeza = actual.siguiente;
                else anterior.siguiente = actual.siguiente;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public List<Contacto> obtenerTodos() {
        List<Contacto> lista = new ArrayList<>();
        NodoLista actual = cabeza;
        while (actual != null) {
            lista.add(actual.contacto);
            actual = actual.siguiente;
        }
        return lista;
    }

    public void mostrarOrdenadoPorNombre() {
        arbol.inOrden();
    }

    // NUEVO MÉTODO PARA ACTUALIZAR EL HASHMAP
    public void actualizarIndices(Contacto contacto, String telefonoAnterior, String correoAnterior) {
        hashBusqueda.remove(correoAnterior);
        hashBusqueda.remove(telefonoAnterior);
        hashBusqueda.put(contacto.getCorreo(), contacto);
        hashBusqueda.put(contacto.getTelefono(), contacto);
    }
}


// CLASE NodoArbol
class NodoArbol {
    Contacto contacto;
    NodoArbol izq, der;

    public NodoArbol(Contacto c) {
        contacto = c;
        izq = der = null;
    }
}

// CLASE ArbolContactos
class ArbolContactos {
    private NodoArbol raiz = null;

    public void insertar(Contacto c) {
        raiz = insertarRec(raiz, c);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Contacto c) {
        if (nodo == null) return new NodoArbol(c);
        if (c.getNombre().compareToIgnoreCase(nodo.contacto.getNombre()) < 0)
            nodo.izq = insertarRec(nodo.izq, c);
        else
            nodo.der = insertarRec(nodo.der, c);
        return nodo;
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izq);
            Contacto c = nodo.contacto;
            AgendaContacto.mostrarContacto(c);
            inOrdenRec(nodo.der);
        }
    }
}


// CLASE Cifrado
class Cifrado {
    public static String cifrarCesar(String texto, int desp) {
        StringBuilder res = new StringBuilder();
        for (char c : texto.toCharArray()) {
            res.append((char)(c + desp));
        }
        return res.toString();
    }

    public static String descifrarCesar(String texto, int desp) {
        StringBuilder res = new StringBuilder();
        for (char c : texto.toCharArray()) {
            res.append((char)(c - desp));
        }
        return res.toString();
    }
}


// CLASE Exportador
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
