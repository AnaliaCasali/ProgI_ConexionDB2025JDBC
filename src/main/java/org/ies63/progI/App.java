package org.ies63.progI;

import org.ies63.progI.configuracion.AdministradorConexion;
import org.ies63.progI.dao.AutoDAO;
import org.ies63.progI.dao.AutoImpl;
import org.ies63.progI.entities.Auto;
import org.ies63.progI.entities.Marca;

import java.sql.Connection;
import java.util.List;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {

    System.out.println("Hello World!");
    Auto auto =
        new Auto("CCCCCC", "Blanco", 2025, 897
            , Marca.Honda, "Fit");
    // guardo en la BD
    AutoDAO autoDAO = new AutoDAO();
    AutoImpl autoImpl = new AutoImpl();
    autoDAO.insertarAuto(auto);
      // recorro la lista de autos
    //List<Auto> miLista = autoDAO.findAll();
    List<Auto> miLista = autoImpl.getAll();
    if (!miLista.isEmpty()) {
      for (Auto auto1 : miLista) {
        System.out.println(auto1.toString());
      }
    }
    //modifica auto
    Auto autoAModificar =
        new Auto(10, "AABBCC", "Gis", 2024, 897555
            , Marca.Honda, "Fit");

    //autoDAO.update(autoAModificar);
    autoImpl.update(autoAModificar);
    //autoDAO.delete(14);

//    System.out.println("Auto encontrado: " + autoDAO.getById(25).toString());
    System.out.println("Auto encontrado: " + autoImpl.getById(25).toString());

    System.out.println("Lista de autos después de la modificación:");
    // recorro la lista de autos
    miLista = autoDAO.findAll();
    if (!miLista.isEmpty()) {
      for (Auto auto1 : miLista) {
        System.out.println(auto1.toString());
      }
    }
    System.out.println(" ----------AGREGANDO CON DAO IMPL ----------------");
    Auto autoTest=
    new Auto( "CCCCCC", "Blanco", 2025, 0
        , Marca.Toyota, "Corolla");
    autoImpl.insert(autoTest);

  }




}



