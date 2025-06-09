import java.io.*; 
import java.util.*;

// clase principal menu 

public class AgendaContacto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaContactos agenda = new ListaContactos();

        while (true) {
            try {
                System.out.println("\nAgenda de Contactos");
                System.out.println("1. Agregar contacto");
                System.out.println("2. Buscar por nombre");
                System.out.println("3. Buscar por correo/teléfono");
                System.out.println("4. Editar contacto");
                System.out.println("5. Eliminar contacto");
                System.out.println("6. Exportar a archivo");
                System.out.println("7. Salir");
                System.out.println("8. Mostrar contactos ordenados por nombre");
                System.out.print("Opción: ");

                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
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
                            for (Contacto c : encontrados) {
                                System.out.println(c.getNombre() + " - " + c.getTelefono() + " - " + c.getCorreo() + " - " + c.getDireccion());
                            }
                        }
                        break;
                    case 3:
                        System.out.print("Correo o teléfono: ");
                        Contacto c2 = agenda.buscarPorDato(sc.nextLine());
                        if (c2 != null)
                            System.out.println(c2.getNombre() + " - " + c2.getTelefono() + " - " + c2.getCorreo() + " - " + c2.getDireccion());
                        else System.out.println("No encontrado.");
                        break;
                    case 4:
                        System.out.print("Nombre del contacto a editar: ");
                        List<Contacto> editar = agenda.buscarPorNombre(sc.nextLine());
                        if (editar.isEmpty()) {
                            System.out.println("Contacto no encontrado.");
                        } else {
                            Contacto c3 = editar.get(0); // Editar el primero que coincida
                            System.out.print("Nuevo teléfono: ");
                            c3.setTelefono(sc.nextLine());
                            System.out.print("Nuevo correo: ");
                            c3.setCorreo(sc.nextLine());
                            System.out.print("Nueva dirección: ");
                            c3.setDireccion(sc.nextLine());
                        }
                        break;
                    case 5:
                        System.out.print("Nombre del contacto a eliminar: ");
                        if (agenda.eliminar(sc.nextLine()))
                            System.out.println("Eliminado correctamente.");
                        else System.out.println("No encontrado.");
                        break;
                    case 6:
                        Exportador.exportarAArchivo(agenda.obtenerTodos(), "contactos.txt");
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        return;
                    case 8:
                        System.out.println("Contactos ordenados alfabéticamente:");
                        agenda.mostrarOrdenadoPorNombre();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }
}