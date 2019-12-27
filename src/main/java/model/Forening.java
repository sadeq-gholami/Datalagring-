package model;

public class Forening {

   public String orgnr;
   public String namn;
   public Boolean flerParkplats;


   @Override
   public String toString() {
      return "Forening{" +
              "orgnr='" + orgnr + '\'' +
              ", namn='" + namn + '\'' +
              ", flerParkplats=" + flerParkplats +
              '}';
   }
}
