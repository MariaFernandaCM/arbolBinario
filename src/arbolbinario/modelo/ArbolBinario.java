/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {

    private Nodo raiz;
    private int cant;
    private int altura;
    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }
public void niveles() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }
    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
     /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en postorden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
     */
    public ArrayList postOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            postOrden(raiz, l);
        }        
        return l;
    }
    /**
     * Método recursivo que recorre todo el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado Acumulador para registrar el dato del nodo visitado
     */
    private void postOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {           
            postOrden(temp.getIzquierda(), listado);
            postOrden(temp.getDerecha(), listado);
            listado.add(temp.getDato());
        }
    }
    

    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en preorden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
     */
    public ArrayList preOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            preOrden(raiz, l);
        }        
        return l;
    }
    /**
     * Método recursivo que recorre todo el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado Acumulador para registrar el dato del nodo visitado
     */
    private void preOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            listado.add(temp.getDato());
            preOrden(temp.getIzquierda(), listado);
            preOrden(temp.getDerecha(), listado);
        }
    }

    public ArrayList inOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        inOrden(raiz, l);
        return l;
    }

    private void inOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(), l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(), l);
        }
    }

    public void llenarArbol(String datos) throws ArbolBinarioException {
        String[] arrayDatos = datos.split(",");
        for (String cadena : arrayDatos) {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }

    }
    
    
    /**
     * Método que retorna la cantidad de nodos del arbol
     * @return int cantidad de nodos
     */                             // raiz
    public int contarNodos()        //reco
    {
        return this.contarNodos(raiz);        
    }    
    
    public int contarNodos(Nodo reco)
    {
        //Hacen el dllo del contar
        if(reco==null)
        {
            return 0;
        }
        if(reco.isHoja())
        {
            return 1;
        }
        else
        {   
            return 1 + contarNodos(reco.getIzquierda())+ contarNodos(reco.getDerecha());
        }
    }
    
    /**
     * Método que retorna la suma de los valos de un arbol a partir del nodo dado
     * @param ref Nodo que se toma como raiz para sumar todos sus descendientes
     * @return int Valor sumar todos los nodos a partir de la referencia
     */    
        
    public int sumarNodos(Nodo ref)
    {
        if(ref != null)
        {
            return ref.getDato() + sumarNodos(ref.getIzquierda()) 
                    + sumarNodos(ref.getDerecha());
        }
        else
        {
            return 0;
        }
    }
    
    
    /**
     * Método que retorna la suma total del arbol     
     * @return int Valor suma total del árbol
     */    
        
    public int sumarArbol()
    {
       return sumarNodos(raiz);
    }
    
    /**
     * Método que buscar un nodo a partir de una referencia
     * @param datobuscar entero que recibe el número a buscar
     * @param ref Nodo a partir del cual busco el dato
     * @return Retrona todo el arbol a partir del Nodo ubicado
     * 
     */
    public Nodo buscarNodo(int datobuscar, Nodo ref)
    {
        if(ref !=null)
        {
            if(ref.getDato()==datobuscar)
            {
                return ref;
            }
            else
            {
                if(datobuscar < ref.getDato())
                {
                    return buscarNodo(datobuscar, ref.getIzquierda());
                }
                else
                {
                    return buscarNodo(datobuscar, ref.getDerecha());
                }
            }
        }
        else
        {
            //Ojoo
            return null;
        }
    }
        public void podar(){
            podar(this.raiz);
                    }
        private void podar (Nodo x){
            if (x==null){
                return;
            }
        
        if (x.getIzquierda().isHoja()){
            x.setIzquierda(null);
            }
        if (x.getDerecha().isHoja()){
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
}
        public boolean buscar(int x) {
        return (buscar(this.raiz, x));
        }
        //Borrar el que sea
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        Nodo z = borrar(this.raiz, x);
        this.setRaiz(z);
        return x;

    }

    private Nodo borrar(Nodo r, int x) {
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzquierda(borrar(r.getIzquierda(), x));
        } else if (compara < 0) {
            r.setDerecha(borrar(r.getDerecha(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (r.getIzquierda() != null && r.getDerecha() != null) {
                
                Nodo cambiar = buscarMin(r.getDerecha());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDerecha(borrar(r.getDerecha(), x));
              
            } else {
                r = (r.getIzquierda() != null) ? r.getIzquierda() : r.getDerecha();
                 System.out.println("Entro cuando le faltan ramas ");
               
            }
        }
        return r;
        }
         private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzquierda(), x));
        } else if (compara < 0) {
            return (buscar(r.getDerecha(), x));
        } else {
            return (true);
        }
    }

           private Nodo buscarMin(Nodo r) {
        for (; r.getIzquierda() != null; r = r.getIzquierda());
        return (r);
    }

     
    
 public ArrayList imprimirNivel() {
     ArrayList l=new ArrayList();
     if (raiz != null){
         String[] niveles = new String[raiz.obtenerAlturaNodo() + 1];
        
        imprimirNivel(raiz, 0, niveles);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
     }
        return l;
    }
 
      public void imprimirNivel(Nodo pivote, int nivel2, String[] niveles) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1, niveles);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1, niveles);
}
    }
      
      public boolean multiplicar() {
            multiplicar(raiz, 1);
            return true;
    }

    private void multiplicar(Nodo reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato()* reco.getDato());
            multiplicar(reco.getIzquierda(), nivel );
            multiplicar(reco.getDerecha(), nivel );
        }
    }
     public String borrarMayor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
     
     public String borrarNivel() {
        Nodo reco=raiz.getIzquierda();    
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo nivel = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    
                    nivel = reco;
                    reco = reco.getDerecha();
                }
                                
                nivel.setDerecha(reco.getIzquierda ());
            }
        }
        return ("Nivel Eliminado: " + reco.getDato());
    }
      public String borrarMenor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda()!= null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
      // HOJAS
    public void getHojas(Nodo r, ArrayList l){
        if(r != null){
            if (this.esHoja(r)){
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }
    }
    
    public ArrayList getHojas(){
        ArrayList lista = new ArrayList();
        getHojas(this.raiz, lista);
        return (lista);
    }  
   
    
    protected boolean esHoja(Nodo x){
        return (x != null && x.getIzquierda() == null && x.getDerecha() == null);
    }
    
    public String cantidadNodos(){
        cant = 0;
        cantidadNodos(raiz);
        return "" + cant;
    }
    
    private void cantidadNodos(Nodo reco){
        if (reco != null){
            cant++;
            cantidadNodos(reco.getIzquierda());
            cantidadNodos(reco.getDerecha());            
        }        
    }  
     public String cantidadNodosHoja(){
        cant = 0;
        cantidadNodosHoja(raiz);
        return "" + cant;
    }
    
    private void cantidadNodosHoja(Nodo reco){
        if (reco != null){
            if (reco.getIzquierda() == null && reco.getDerecha() == null){
                cant++;
            }
            cantidadNodosHoja(reco.getIzquierda());
            cantidadNodosHoja(reco.getDerecha());
        }
    }
}   


