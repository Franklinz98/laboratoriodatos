/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio;

/**
 *
 * @author mybas
 */
public class Banco {

    public Banco() {
        this.ptrcaja = null;
    }

    class Caja {

        int identificador;
        long dinero;
        String transaccion;
        Cliente cola;
        int tamañocola;
        Caja link;
    }
    Caja ptrcaja;

    class Cliente {

        Cliente llink;
        int identificador;
        boolean transaccion;
        Cliente rlink;
    }

    /**
     *
     * @param ptr
     * @param identificador
     * @param dinero
     * @param transaccion
     * @return
     */
    public Caja agregarCaja(Caja ptr, int identificador, long dinero, String transaccion) {
        Caja p = new Caja();
        p.identificador = identificador;
        p.dinero = dinero;
        p.transaccion = transaccion;
        if (ptr == null) {
            return p;
        } else {
            Caja q = ptr;
            while (q.link != null) {
                q = q.link;
            }
            q.link = p;
            return ptr;
        }
    }
//en cada caja busca la menor cantidad de clientes y luego agrega el cliente a la lista de esa caja
    public Caja agregarCliente(Caja ptr, int identificador, String transaccion) {
        int menorcola = 0;
        int counter = 0;
        Cliente p = new Cliente();
        Cliente s = null;
        p.identificador = identificador;
        p.transaccion=false;
        Caja q = ptr;
        Caja r;
        while (q.link != null) {
            if (q.transaccion.equals(transaccion)) {
                counter++;
                if (counter == 1) {
                    s = q.cola;
                    menorcola = q.tamañocola;
                } else if (menorcola > q.tamañocola) {
                    menorcola = q.tamañocola;
                    s = q.cola;
                }
            }
            q = q.link;
        }
        if (q.transaccion.equals(transaccion)) {
            counter++;
            if (counter == 1) {
                s = q.cola;
                menorcola = q.tamañocola;
            } else if (menorcola > q.tamañocola) {
                menorcola = q.tamañocola;
                s = q.cola;
            }
        }
        if (s == null) {
            s = p;
        } else {
            while (s.rlink != null) {
                s = s.rlink;
            }
            s.rlink = p;
            p.llink = s;
            p.rlink = null;
        }
        return ptr;
    }
    
    public Caja eliminarCliente(Caja ptr, int identificador){
        Caja p=ptr;
        Cliente q=null;
        while(p.link!=null){
            q=p.cola;
            while(q.rlink!=null && q.identificador!=identificador){
                q=q.rlink;
            }
            if (q.identificador==identificador) {
                q.llink.rlink=q.rlink;
                q.rlink.llink=q.llink;
                break;
            }
        }
        return ptr;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
