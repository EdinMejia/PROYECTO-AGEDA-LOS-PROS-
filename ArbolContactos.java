// clase arbol binario de contactos 

class NodoArbol {
    Contacto contacto; 
    NodoArbol izq, der; 

    public NodoArbol(Contacto c) {
        contacto = c; 
        izq = der = null; 
    }
}

public class ArbolContactos {
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

    public void inOrden(){
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoArbol nodo){
        if (nodo != null) {
            inOrdenRec(nodo.izq); 
            Contacto c = nodo.contacto; 
            System.out.println(c.getNombre() + " - " + c.getTelefono() + " - " + c.getCorreo() + " - " + c.getDireccion());
            inOrdenRec(nodo.der);
        }
    }
}