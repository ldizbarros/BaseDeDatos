package basededatos.metodos;

import basededatos.datos.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Metodos {
    
    public static ArrayList crearArrayList (String funcion, String parametro){
        if (funcion.equalsIgnoreCase("mostrarUsuarios")){
            return ConexionBD.mostrarUsuarios();
        }else{
            return ConexionBD.busqueda(parametro);
        }
    }
    
    public static  DefaultTableModel mostrarUsuarios(ArrayList usuarios){
        if (usuarios.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se han encontrado coincidencias");
            return null;
        }else{
            DefaultTableModel tabla = new DefaultTableModel();
            tabla.addColumn("ID");
            tabla.addColumn("NOMBRE");
            tabla.addColumn("APELLIDOS");
            tabla.addColumn("FECHA NACIMIENTO");
            tabla.addColumn("TELEFONO");
            Iterator it = usuarios.iterator();
            while(it.hasNext()){
                Usuario usu = (Usuario) it.next();
                String[] fila = new String[5];
                fila[0] = String.valueOf(usu.getId());
                fila[1] = usu.getNombre();
                fila[2] = usu.getApellidos();
                fila[3] = String.valueOf(usu.getFechaNaciemiento());
                fila[4] = String.valueOf(usu.getTelefono());
                tabla.addRow(fila);
            }
            return tabla;
        }
    }
    
    public static void crearUsuario(String id, String nombre, String apellidos, String fecha, String telefono){
        Usuario usuario = new Usuario(Integer.parseInt(id),nombre,apellidos,fecha,Integer.parseInt(telefono));
        ConexionBD.añadirUsuario(usuario);
    }
    
    public static void borrarUsuario(int id){
        ConexionBD.borrarUsuario(id);
    }
    
    public static Usuario buscarUsuario(int id){
        return ConexionBD.buscarUsuario(id);
    }
    
    public static void modificarUsuario(String id, String nombre, String apellidos, String fecha, String telefono){
        Usuario usuario = new Usuario(Integer.parseInt(id),nombre,apellidos,fecha,Integer.parseInt(telefono));
        ConexionBD.modificarUsuario(usuario);
    }
}
