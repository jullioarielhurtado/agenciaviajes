/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaViajes.negocio;

import agenciaViajes.acceso.ServicioRegistraduriaSocket;
import agenciaViajes.negocio.Cliente;
import java.util.ArrayList;
import mvcf.AModel;
import agenciaViajes.acceso.ServicioRegistraduria;

/**
 *
 * @author ahurtado
 */
public class GestorClientes extends AModel {
    private ArrayList<Cliente> misClientes;
    private int number;
    private ServicioRegistraduria reg; 
    
    public GestorClientes(){
        misClientes = new ArrayList<>();
        number = 0;
        reg = new ServicioRegistraduriaSocket();
    }
    public void addCliente(String nombre, String id){
        Cliente temp = new Cliente(nombre,id);
        misClientes.add(temp);
        number++;
        this.notificar();
    }
    
    public void addCliente(String id){
        String nombre = reg.obtenerNombre(id);
        if(!nombre.equals("NO_ENCONTRADO")){
            misClientes.add(new Cliente(nombre,id));
            number++;
            this.notificar();}   
    }
    public Cliente getLast(){
    return misClientes.get(number-1);
    }   

    public int size() {
        return misClientes.size();
    }
}


