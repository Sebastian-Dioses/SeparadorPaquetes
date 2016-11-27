/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



/**
 *
 * @author JoseLuis
 */
public class DFS {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        
        BufferArchivos bufferArchivos=new BufferArchivos();
        GestorCiudades gestor= new GestorCiudades("Extras/plan_vuelo.txt", "Extras/_aeropuertos.OACI.txt", "Extras/_husos_horarios.txt");
        gestor.lineaInicial();
        bufferArchivos.organizarPedidos(gestor);
        
        
        
        Set set = bufferArchivos.getListaPedidosEscala1().entrySet();
        Iterator i = set.iterator();
        
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            String[] ciudadActual=(String[])me.getValue();
            
            System.out.println(me.getKey()+"-"+ ciudadActual.length);
        }
        
        Set set2 = bufferArchivos.getListaPedidosEscala2().entrySet();
        Iterator i2 = set2.iterator();
        
        while(i2.hasNext()) {
            Map.Entry me = (Map.Entry)i2.next();
            String[] ciudadActual=(String[])me.getValue();
            
            System.out.println(me.getKey()+"-"+ ciudadActual.length);
        }
        bufferArchivos.generarJson();
        /*Gson gson = new Gson();
        GestorCiudades temporal= new GestorCiudades();
        try (Reader reader = new FileReader("staff.json")) {

	
            
            temporal=gson.fromJson(reader, GestorCiudades.class);
            

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //gestor.lineaInicial();
        //gestor.asignarPedidos();        
        //gestor.imprimirCiudades();
        
        
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("staff.json")) {
            gson.toJson(gestor,writer);
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
        
    }
    
}
