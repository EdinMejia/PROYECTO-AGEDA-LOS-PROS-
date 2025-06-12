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

// CLASE NodoArbol
class NodoArbol {
    Contacto contacto;
    NodoArbol izq, der;

    public NodoArbol(Contacto c) {
        contacto = c;
        izq = der = null;
    }
}

