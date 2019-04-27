package registraduria;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

public class RegistraduriaServer implements Runnable {
   Socket socket_cliente;
   static HashMap<String,String> ciudadanos; 
   RegistraduriaServer(Socket socket_cliente) {
      this.socket_cliente = socket_cliente;
   }
   public static void main(String args[]) throws Exception { 
       ciudadanos = new HashMap<>();
       ciudadanos.put("76317623", "Julio Ariel Hurtado");
       ciudadanos.put("76034675","Bernardita Gutierrez");
       ServerSocket ssock = new ServerSocket(5000);
       System.out.println("Listening");
       while (true) {
         Socket socket = ssock.accept();
         System.out.println("Connected");
         new Thread(new RegistraduriaServer(socket)).start();
       }
   }
   @Override
   public void run() {
      try {
         String id;
         String nombre;
         PrintStream salidaDecorada = new PrintStream(socket_cliente.getOutputStream());
         Scanner entradaDecorada = new Scanner(socket_cliente.getInputStream());
         System.out.println("Flujos Listos");
         if(entradaDecorada.hasNextLine()){
             id = entradaDecorada.nextLine();
             System.out.println(id);
             nombre = ciudadanos.get(id);
             salidaDecorada.flush();
             if(nombre == null) salidaDecorada.println("NO_ENCONTRADO");     
             else  salidaDecorada.println(nombre);
             
         }
         else{
             salidaDecorada.flush();
             salidaDecorada.println("NO_ENCONTRADO");
         }
         salidaDecorada.close();
         entradaDecorada.close();
         socket_cliente.close();
      } catch (IOException e) {
         System.out.println(e);
      }
   }
}