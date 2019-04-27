/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaViajes.acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahurtado
 */
public class ServicioRegistraduriaSocket implements ServicioRegistraduria {

    Socket socket= null;
    @Override
    public String obtenerNombre(String id) {
        String nombre = null;
        try {
            conectar("127.0.0.1", 5000);
            Scanner entradaDecorada = new Scanner(socket.getInputStream());
            PrintStream salidaDecorada = new PrintStream(socket.getOutputStream());
            salidaDecorada.flush();
            salidaDecorada.println(id);
            if(entradaDecorada.hasNextLine())
                       nombre = entradaDecorada.nextLine();
            salidaDecorada.close();
            entradaDecorada.close();
            desconectar();
           
        } catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombre;
    } 

    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void conectar(String address, int port) 
    { 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Conectado");  
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
    }
}
