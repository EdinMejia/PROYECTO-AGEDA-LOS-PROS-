import java.io.*; 
import java.util.*;


public class MenudeOpciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        ListaContactos agenda = new ListaContactos(); 

        while (true) {
            System.out.println("\n Agenda de contactos ");
            System.out.println("\n 1- Agregar contacto ");
            System.out.println("\n 2- Buscar por nombre ");
            System.out.println("\n 3- Buscar por correo / telefono  ");
            System.out.println("\n 4- Ajustes");
            System.out.println("\n 5- Eliminar contacto ");
            System.out.println("\n 6- Exportar a .txt ");
            System.out.println("\n 7- Salir ");
            System.out.println("\n 8. Mostrar contactos ordenados por nombre: ");
            System.out.println("\n Seleccione opcion:  ");
            int opcion = sc.nextInt(); sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Nombre Completo: ");
                    String nombre = sc.nextLine();
                    System.out.println("Correo: ");
                    String mail = sc.nextLine();
                    System.out.println("Direccion: ");
                    String direc = sc.nextLine();
                    System.out.println("Telefono");
                    String tel = sc.nextLine();
                    agenda.agregar(new Contacto(nombre, mail, direc, tel));
                    break;
                case 2: 
                    System.out.println("Ingrese Nombre: ");
                    Contacto c1 = agenda.buscarPorNombre(sc.nextLine()); 
                    if (c1 != null) 
                        System.out.println(c1.getNombre() + " - " + c1.getCorreo() + " - " + c1.getDireccion() + " - " + c1.getTelefono());
                    else System.out.println("No se encontro: ");
                    break;    
                case 3:
                    System.out.println("Ingrese correo / telefono: ");
                    Contacto c2 = agenda.buscarPordato(sc.nextLine()); 
                    if (c2 != null) 
                        System.out.println(c2.getNombre() + " - " + c2.getTelefono() + " - " + c2.getCorreo());
                    else System.out.println("no encontrado: "); 
                    break; 
                case 4: 
                    System.out.println("Nombre del contacto a Editar: ");
                    Contacto c3 = agenda.buscarPorNombre(sc.nextLine()); 
                    if (c3 != null) {
                        System.out.println("Nuevo nombre: ");
                        c3.setNombre(sc.nextLine()); 
                        System.out.println("nuevo correo");
                        c3.setCorreo(sc.nextLine()); 
                        System.out.println("nueva direccion");
                        c3.setDireccion(sc.nextLine()); 
                        System.out.println("nuevo telefono");
                        c3.setTelefono(sc.nextLine()); 
                    }else {
                        System.out.println("contacto no encontrado: ");
                    }
                    break; 
                case 5: 
                    System.out.print( "Nombre del contacto a eliminar: ");
                    if (agenda.eliminar(sc.nextLine()))
                    System.out.println("Eliminado con exito. ");
                    else System.out.println("No se encontro el contacto: ");
                    break; 
                case 6: 
                    Exportador.exportarAArchivo(agenda.obtenerTodos(), nombreArchivo:"contactos.txt"); 
                    break;
                case 7:
                    System.out.println("Saliendo de agenda........");
                    return; 
                case 8: 
                    System.out.println("Contactos ordenados alfabeticamente: ");
                    agenda.mostrarOrdenadoPorNombre();
                    break; 
                default:
                    System.out.println("Opcion no valida. ");
            }
            
        }
    }
}
