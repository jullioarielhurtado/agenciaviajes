/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaViajes.presentacion;

import agenciaViajes.negocio.GestorClientes;
import agenciaViajes.presentacion.GuiPlanificadorViaje;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;

/**
 *
 * @author ahurtado
 */
public class PlanificadorController extends AActionController{

    private GestorClientes clientes;
    private GuiPlanificadorViaje vista;

    public  PlanificadorController (AModel myModel, AView myView){
        super(myModel,myView);
        this.clientes = (GestorClientes) myModel;
        this.vista = (GuiPlanificadorViaje) myView;
    }
    
    @Override
     public void actualizar() {
       String name;
       String id;
       name = vista.getNombreCliente();
       id = vista.getIdCliente(); 
       if(name.equals("")) clientes.addCliente(id);
       else clientes.addCliente(name,id);  
    }
}
