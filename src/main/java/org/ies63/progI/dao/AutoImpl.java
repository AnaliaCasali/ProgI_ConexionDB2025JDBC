package org.ies63.progI.dao;

import org.ies63.progI.configuracion.AdministradorConexion;
import org.ies63.progI.entities.Auto;
import org.ies63.progI.interfaces.AdmConnexion;
import org.ies63.progI.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoImpl implements DAO<Auto,Integer>, AdmConnexion {
  private Connection conn= null;

  private static final String SQL_INSERT=
      "INSERT INTO autos (patente,color,anio,kilometraje,marca,modelo) " +
      "VALUES            (      ?,        ?,    ?,   ?,        ?,      ?)";


  private static  final String  SQL_UPDATE= "UPDATE autos SET " +
      "patente = ? , color = ? , anio = ? , kilometraje = ? " +
      " , marca = ? , modelo = ? " +
      "  WHERE idAuto = ? " ;

  private static  final String  SQL_DELETE= "DELETE FROM autos  WHERE idAuto = ? " ;
  private static  final String  SQL_GETALL= "SELECT * FROM autos ORDER BY patente" ;
  private  static final String  SQL_GETBYID= "SELECT * FROM autos WHERE idAuto = ? " ;

  @Override
  public List<Auto> getAll() {
    List<Auto> lista= new ArrayList<>();
    return lista;
  }

  @Override
  public void insert(Auto objeto) {
    // 1 establecer conexion
    Auto auto = objeto;
    conn = obtenerConexion();
    // establecer conexion a la base de datos

    // paso 2 crear string consulta SQL
/*    String sql =
        "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
            "VALUES            (" + auto.getIdAuto() + "," +
            "'" + auto.getPatente() + "'," +
            "'" + auto.getColor() + "'," +
            +auto.getAnio() + "," +
            +auto.getKilometraje() + "," +
            "'" + auto.getMarca() + "'," +
            "'" + auto.getModelo() + "')";
*/
    // paso 3 crear instruccion
    PreparedStatement pst = null;
    try {
       // con la conexion llamo al prepareStatement pasandole la consulta SQL
      pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

      pst.setString(1, auto.getPatente());
      pst.setString(2,auto.getColor());
      pst.setInt(3,auto.getAnio());
      pst.setInt(4,auto.getKilometraje());
      pst.setString(5,auto.getMarca().toString());
      pst.setString(6,auto.getModelo());

      // paso 4 ejecutar instruccion
      // executeUpdate devuelve 1 si ejecuto correctamente 0 caso contrario
      int resultado = pst.executeUpdate();
      if (resultado == 1) {
        System.out.println("Auto insertado correctamente");
      } else {
        System.out.println("No se pudo insertar el auto");
      }

      ResultSet rs= pst.getGeneratedKeys();
      if(rs.next())
      { auto.setIdAuto(rs.getInt(1));
        System.out.println( "El id asignado es: "+ auto.getIdAuto());
      }

      // paso 5 cerrar conexion
      pst.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }

  @Override
  public void update(Auto objeto) {

  }

  @Override
  public void delete(Integer id) {
      Connection conn = this.obtenerConexion();

      try {
        PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
        pst.setInt(1,id);
        int resultado = pst.executeUpdate();
        if (resultado == 1) {
          System.out.println("Auto eliminado correctamente");
        } else {
          System.out.println("No se pudo eliminar el auto");
        }
        pst.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("No se pudo eliminar el auto. Error: " + e.getMessage());
      }

  }

  @Override
  public Auto getById(Integer id) {
    return null;
  }

  @Override
  public boolean existsById(Integer id) {
    return false;
  }
}
