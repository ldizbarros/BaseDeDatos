package basededatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    
    static String nombreBD = "miBD.bd";
    static Connection connect;
    
    public static void conectarBD(){
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
            if (connect!=null) {
                System.out.println("Conectado");
            }
        }catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }
    
    public static void cerrarBD(){
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList <Usuario> mostrarUsuarios(){
        ArrayList <Usuario> listaUsuarios = new ArrayList();
        conectarBD();
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement("SELECT * FROM Usuarios");
            result = st.executeQuery();
            while (result.next()) {
                Usuario usu = new Usuario(result.getInt("id"),result.getString("Nombre"),
                           result.getString("Apellidos"), result.getString("FechaNacimiento"), result.getInt("Telefono"));
                listaUsuarios.add(usu);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        cerrarBD();
        return listaUsuarios;
    }
    
    public static void añadirUsuario(Usuario usuario){
        conectarBD();
        try {
            PreparedStatement st = connect.prepareStatement("insert into usuarios (id, Nombre, Apellidos, FechaNacimiento, Telefono) values (?,?,?,?,?)");
            st.setString(1, String.valueOf(usuario.getId()));
            st.setString(2, usuario.getNombre());
            st.setString(3, usuario.getApellidos());
            st.setString(4, usuario.getFechaNaciemiento());
            st.setString(5, String.valueOf(usuario.getTelefono()));
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        cerrarBD();
    }
    
    public static void borrarUsuario(int id){
        conectarBD();   
        try {
            PreparedStatement st = connect.prepareStatement("DELETE FROM Usuarios WHERE id=?");
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        cerrarBD();
    }
   
    public static ArrayList <Usuario> busqueda(String busqueda){
        ArrayList <Usuario> listaUsuarios = new ArrayList();
        conectarBD();
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement("select * from Usuarios where id like '"+busqueda
                    +"' or Nombre like '"+busqueda+"' or Apellidos like '"+busqueda+"' or Telefono like '"+busqueda+""
                            + "' or FechaNacimiento like '"+busqueda+"'");
            result = st.executeQuery();
            while (result.next()) {
                Usuario usu = new Usuario(result.getInt("id"),result.getString("Nombre"),
                           result.getString("Apellidos"), result.getString("FechaNacimiento"), result.getInt("Telefono"));
                listaUsuarios.add(usu);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        cerrarBD();
        return listaUsuarios;
    }
    
    public static void modificarUsuario(Usuario usuario){
        cerrarBD();
        conectarBD();   
        try {
            PreparedStatement st = connect.prepareStatement("UPDATE Usuarios SET Nombre=? , Apellidos = ? , FechaNacimiento = ? , Telefono = ?  where id=?");
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellidos());
            st.setString(3, usuario.getFechaNaciemiento());
            st.setString(4, String.valueOf(usuario.getTelefono()));
            st.setString(5, String.valueOf(usuario.getId()));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        cerrarBD();
    }
    
    public static Usuario buscarUsuario(int id){
        conectarBD();
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement("select * from Usuarios where id like '"+id+"'");
            result = st.executeQuery();
            if (result.next()) {
                Usuario usu = new Usuario(result.getInt("id"),result.getString("Nombre"),
                           result.getString("Apellidos"), result.getString("FechaNacimiento"), result.getInt("Telefono"));
                return usu;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        cerrarBD();
        return null;
    }
}
