package model;

public class Hus {

    public String postadress;
    public String gatadress;
    public int antalvaningar;
    public int byggar;
    public int utrymme;
    public Forening forenig;

    @Override
    public String toString() {
        return "Hus{" +
                "postadress='" + postadress + '\'' +
                ", gatadress='" + gatadress + '\'' +
                ", antalvaningar=" + antalvaningar +
                ", byggår=" + byggar +
                ", utrymme=" + utrymme +
                '}';
    }
}
