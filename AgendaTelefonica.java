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
            System.out.println("\n Seleccione opcion:  ");
            int opcion = sc.nextInt(); sc.nextInt();
            
            
        }
    }
}
