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