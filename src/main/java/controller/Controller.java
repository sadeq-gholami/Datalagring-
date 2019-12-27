package controller;

import model.Forening;
import model.Hus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {


    public List<Forening> getAllForening() throws SQLException {
        DBJDBCA database = new DBJDBCA();
        database.connect();
        ArrayList<Forening> forenings = database.getAllHus();
        return forenings;

    }


    public List<Hus> getHusByForening(String orgnr) throws SQLException {
        DBJDBCA database = new DBJDBCA();
        database.connect();

        return database.getHusByOrgNr(orgnr);
    }

    public void addNewHus(String postadress, String gatadress, String orgNr, String byggar) throws SQLException {
        DBJDBCA database = new DBJDBCA();
        database.connect();
        Hus hus = new Hus();

        hus.forenig = database.getForening(orgNr).get(0);
        hus.gatadress = gatadress;
        hus.postadress = postadress;
        hus.utrymme = 0;
        hus.byggar = Integer.parseInt(byggar);
        hus.antalvaningar =0;

        database.connect();
        database.insertHus(hus);
    }
}
