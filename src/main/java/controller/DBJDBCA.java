package controller;

import model.Forening;
import model.Hus;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBJDBCA {
    static protected Connection con;
    //todo: there are better ways to point to the file.


    String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator +"BostadsBastv1222.accdb";
    private String URL = "jdbc:ucanaccess://" + filePath;
    private String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private String userID = "root";
    private String password = "bunny";

    public void connect() throws SQLException {
        con = DriverManager.getConnection(URL);
        con.setAutoCommit(false);
    }

    public void insertHus(Hus hus) throws SQLException {
        String query;
        PreparedStatement stmt;
        query = "INSERT INTO Hus (postadress, gatadress, antalvåningar,byggår,utrymme,förening) VALUES (?, ?, ?,?,?,?)";
        stmt = con.prepareStatement(query);
        stmt.setString(1, hus.postadress);
        stmt.setString(2, hus.gatadress);
        stmt.setInt(3, hus.antalvaningar);
        stmt.setInt(4, hus.byggar);
        stmt.setInt(5, hus.utrymme);
        stmt.setString(6, hus.forenig.orgnr);
        stmt.executeUpdate();
        stmt.close();
        con.commit();
        con.close();
    }


    public ArrayList<Forening> getForening(String orgNr) throws SQLException {
        String query;
        ResultSet rs;
        PreparedStatement stmt;
        query = "SELECT *  FROM Förening WHERE orgnr =?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, orgNr);
        rs = stmt.executeQuery();
        ArrayList<Forening> list = new ArrayList<>();
        while (rs.next()) {
            Forening forening = new Forening();
            forening.orgnr = rs.getString("orgnr");
            forening.flerParkplats = rs.getBoolean("flerparkplats");
            forening.namn = rs.getString("namn");
            list.add(forening);
        }
        stmt.close();
        con.commit();
        con.close();
        return list;
    }

    public ArrayList<Hus> getHusByOrgNr(String orgNr) throws SQLException {
        String query;
        ResultSet rs;
        PreparedStatement stmt;
        query = "SELECT *  FROM Hus WHERE förening =?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, orgNr);
        rs = stmt.executeQuery();
        ArrayList<Hus> list = new ArrayList<>();
        while (rs.next()) {
            Hus hus = new Hus();
            hus.utrymme = rs.getInt("utrymme");
            hus.postadress = rs.getString("postadress");
            hus.antalvaningar = rs.getInt("antalvåningar");
            hus.byggar = rs.getInt("byggår");
            hus.gatadress = rs.getString("gatadress");
            list.add(hus);
        }
        stmt.close();
        con.commit();
        con.close();
        return list;
    }

    public ArrayList<Forening> getAllHus() throws SQLException {
        String query;
        ResultSet rs;
        Statement stmt;
        query = "SELECT * From Förening";
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        ArrayList<Forening> list = new ArrayList<>();
        while (rs.next()) {
            Forening forening = new Forening();
            forening.orgnr = rs.getString("orgnr");
            forening.flerParkplats = rs.getBoolean("flerparkplats");
            forening.namn = rs.getString("namn");

            list.add(forening);
        }
        stmt.close();
        con.commit();
        con.close();
        return list;
    }
}
