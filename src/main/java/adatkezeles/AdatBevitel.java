/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatkezeles;

import alaposztalyok.AutoT;
import alaposztalyok.Jatekos;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kunec
 */
public class AdatBevitel implements AdatInput {

    private static AdatBevitel peldany;

    public static AdatBevitel getPeldany() {
        if (peldany == null) {
            peldany = new AdatBevitel();
        }
        return peldany;
    }

    private Connection kapcsolat;

    /**
     * kapcsolódik a játékosok adatbázisához
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void kapcsolodas() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:DragRaceDB12;create=true;";
        kapcsolat = DriverManager.getConnection(url);

        String sqlVaneMarJatekosTabla = "select * from SYS.SYSTABLES where tablename = 'JATEKOSOK'";

        try (Statement utasitasObjektum = kapcsolat.createStatement();
                ResultSet rs = utasitasObjektum.executeQuery(sqlVaneMarJatekosTabla)) {

            if (!rs.next()) {
                String sqlTablaKeszitesJatekos = " CREATE TABLE APP.JATEKOSOK ( nev varchar(50), penz int, xp int, szint int, autoIDk varchar(4000)  )";
                String sqlUtasitasJatekos = "INSERT INTO APP.JATEKOSOK VALUES ('teszt',1,1,1,'')";
                utasitasObjektum.execute(sqlTablaKeszitesJatekos);
                utasitasObjektum.executeUpdate(sqlUtasitasJatekos);
            }
        }
    }

    /**
     * a játékban szerelpő autók adatbázisához kapcsolódik
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void autoKapcsolodas() throws ClassNotFoundException, SQLException {
//        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//        kapcsolat = DriverManager.getConnection(url);
//
//        String sqlVaneMarAutoTabla = "select * from SYS.SYSTABLES where tablename = 'AUTOK'";
//
//        try (Statement utasitasObjektum = kapcsolat.createStatement();
//                ResultSet rsAuto = utasitasObjektum.executeQuery(sqlVaneMarAutoTabla)) {
//
//            if (!rsAuto.next()) {
//                String sqlTablaKeszitesAuto = " CREATE TABLE APP.AUTOK (id int, marka varchar(100),\n"
//                        + "tipus varchar(100),\n"
//                        + "logo varchar(100),\n"
//                        + "szinKod varchar(100),\n"
//                        + "ar int,\n"
//                        + "szint int,\n"
//                        + "loero int,\n"
//                        + "kobcenti int,\n"
//                        + "tomeg int,\n"
//                        + "meghajtas int,\n"
//                        + "maxFordulat int,\n"
//                        + "sebessegFokozatok int,\n"
//                        + "kocsiDetail varchar(100),\n"
//                        + "kocsimintazata varchar(100),\n"
//                        + "fek varchar(100),\n"
//                        + "kerek varchar(100),\n"
//                        + "ultetes int,\n"
//                        + "hatsoLokTav int,\n"
//                        + "kardanTengely int)";
//                String sqlUtasitasAuto = "INSERT INTO APP.AUTOK VALUES (1,'Alfa Romeo', 'GT','AR.png','#FFD700', 12000, 1, 238, 3179, 1410, 2, 7200, 6,'Alfa_Romeo_GT_Detail.png', 'Alfa_Romeo_GT.png', 'Wheel5.png', 'Alfa_Romeo_GT_Disk.png', 6, 30, 192),\n"
//                        + "(2,'Toyota', 'Supra Mk4','Toyota.png','#00FF00', 17000, 1, 330, 2997, 1550, 1, 6800, 5,'Toyota_Supra_Detail.png','Toyota_Supra.png', 'Wheel5.png', 'Toyota_Supra_Disk.png', -6, 38, 185),\n"
//                        + "(3,'Volkswagen', 'Golf GTI','VW.png','#0000FF', 10000, 1, 220, 1984, 1295, 2, 6000, 6,'Golf_GTI_Detail.png','Golf_GTI.png','Wheel5.png','Golf_GTI_Disk.png', 0, 17, 190),\n"
//                        + "(4,'BMW', '328is E36','BMW.png','#00BFFF', 13000, 1, 190, 2793, 1395, 1, 7000, 5,'BMW_E36_Detail.png','BMW_E36.png', 'Wheel5.png', 'BMW_E36_Disk.png', -3, 35, 196),\n"
//                        + "(5,'Honda', 'Integra Type R','Honda.png','#FF0000', 14000, 1, 195, 1797, 1080, 2, 9000, 5, 'Honda_Integra_Detail.png', 'Honda_Integra.png', 'Wheel5.png', 'Honda_Integra_Disk.png', 14, 29, 185),\n"
//                        + "(6,'Mazda', 'Mx-5','Mazda.png','#FF4500', 14000, 1, 168, 1999, 1100, 1, 6500, 6,'Mazda_MX5_Detail.png','Mazda_MX5.png', 'Wheel5.png', 'Mazda_MX5_Disk.png', 14, 23, 178),\n"
//                        + "(7,'BMW', 'M3', 'BMW.png', '#000000',20000,2,210,2003,1500,1,9000,6,'BMW_M3_Detail.png','BMW_M3.png','Wheel5.png','BMW_M3_Disk.png',15,38,204)";
//                utasitasObjektum.execute(sqlTablaKeszitesAuto);
//                utasitasObjektum.executeUpdate(sqlUtasitasAuto);
//            }
//        }
    }

    /**
     * a játékosok táblát kilistázza és betölti a tartalmát egy játékos típusú
     * listába
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Jatekos> listAll() throws Exception {
        List<Jatekos> jatekosok = new ArrayList<>();
        if (kapcsolat != null) {
            String sqlUtasitas = "SELECT * FROM JATEKOSOK";
            String nev;
            String autoIdk;
            int penz, xp, szint;
            try (Statement utasitasObj = kapcsolat.createStatement();
                    ResultSet eredmenyhalmaz = utasitasObj.executeQuery(sqlUtasitas)) {
                while (eredmenyhalmaz.next()) {
                    nev = eredmenyhalmaz.getString("nev");
                    penz = eredmenyhalmaz.getInt("penz");
                    xp = eredmenyhalmaz.getInt("xp");
                    szint = eredmenyhalmaz.getInt("szint");
                    autoIdk = eredmenyhalmaz.getString("autoIDk");
                    jatekosok.add(new Jatekos(nev, penz, xp, szint, autoIdk));
                }
            }
        }
        return jatekosok;
    }

    /**
     * ha új játékos jelentkezik be, akkor ez a metódus fut le és létrehozza az
     * adatbázisban az új személyt
     *
     * @param jatekos
     * @throws Exception
     */
    @Override
    public void createJatekos(Jatekos jatekos) throws Exception {
        if (kapcsolat != null) {
            try (Statement utasitasObj = kapcsolat.createStatement()) {
                String sqlUtasitas = "INSERT INTO APP.JATEKOSOK VALUES ('" + jatekos.getNev() + "', " + jatekos.getKezdoToke()
                        + "," + jatekos.getXp() + "," + jatekos.getSzint() + ",'" + jatekos.getNev()+".txt" + "')";
                utasitasObj.executeUpdate(sqlUtasitas);
            }
        }
    }

    /**
     * frissíti az aktuális játékos adatait, lefut, ha a játkos rányom a mentés
     * gombra, valamit abban az esetben, ha a játékos futamot nyert
     *
     * @param jatekos
     * @throws Exception
     */
    @Override
    public void updateJatekos(Jatekos jatekos) throws Exception {
        if (kapcsolat != null) {
            //nev , penz , xp , szint , autoIDk  
            String sqlUtasitas = "UPDATE APP.JATEKOSOK SET penz = ?, xp = ?, szint = ?, autoIDk = ? WHERE nev = ?";
            try (PreparedStatement utasitasObj = kapcsolat.prepareStatement(sqlUtasitas)) {
                utasitasObj.setInt(1, jatekos.getPenz());
                utasitasObj.setInt(2, jatekos.getXp());
                utasitasObj.setInt(3, jatekos.getSzint());
                utasitasObj.setString(4, jatekos.getNev()+".txt");
                utasitasObj.setString(5, jatekos.getNev());
                utasitasObj.executeUpdate();
            }
        }
    }

    /**
     * kilistázza az összes autót az adatbázisból, és betölti egy AutoT tíousú
     * listába
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<AutoT> listAllAuto() throws Exception {
        List<AutoT> autok = new ArrayList<>();
        String marka;
        String tipus;
        int vetelAr;
        int loero;
        int tomeg;
        int maximumFordulatszam;
        String kocsiMintazat;
        String kocsiDetail;
        String kocsiFek;
        String kocsiKerek;
        int ultetes;
        int hatsoLokharitoTavolsag;
        int kardanTengelyTavolsag;
        int szint;
        int meghajtas;
        int sebessegfokozatok;
        String szinKod;
        String logo;
        int kobcenti;

        try (InputStream ins = this.getClass().getResourceAsStream("/autoAdat/autok.txt");
                Scanner fajlScanner = new Scanner(ins, "UTF-8")) {
            String[] adatok;
            String sor;

            while (fajlScanner.hasNextLine()) {
                sor = fajlScanner.nextLine();
//marja;tipus;logo;szin;ár;szint;lóerő;tömeg;kobtenci;meghajás;fordulatszám;sebesség fokozatok;detail;sima;
//kerék;fék;ültetés;kardanTengelyTavolsag;hátó
                if (!sor.isEmpty() && !sor.substring(0,1).equals("/")) {
                    adatok = sor.split(";");
                    //id = Integer.valueOf(adatok[0]);
                    marka = adatok[0];
                    tipus = adatok[1];
                    logo = adatok[2];
                    szinKod = adatok[3];
                    vetelAr = Integer.valueOf(adatok[4]);
                    szint = Integer.valueOf(adatok[5]);
                    loero = Integer.valueOf(adatok[6]);
                    tomeg = Integer.valueOf(adatok[7]);
                    kobcenti = Integer.valueOf(adatok[8]);
                    meghajtas = Integer.valueOf(adatok[9]);
                    maximumFordulatszam = Integer.valueOf(adatok[10]);
                    sebessegfokozatok = Integer.valueOf(adatok[11]);
                    kocsiDetail = adatok[12];
                    kocsiMintazat = adatok[13];
                    kocsiKerek = adatok[15];
                    kocsiFek = adatok[14];
                    ultetes = Integer.valueOf(adatok[16]);
                    hatsoLokharitoTavolsag = Integer.valueOf(adatok[17]);
                    kardanTengelyTavolsag = Integer.valueOf(adatok[18]);

                   /* kocsiKepMintazata = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiDetail)).getImage();
                    kocsiKepDetail = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiMintazat)).getImage();
                    kocsiFekKep = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiFek)).getImage();
                    kocsiKerekKep = new ImageIcon(this.getClass().getResource("/autoKepek/" + kocsiKerek)).getImage();
                    logoKep = new ImageIcon(this.getClass().getResource("/logos/" + logo)).getImage();*/

                    autok.add(new AutoT( marka, tipus, logo, szinKod, vetelAr, sebessegfokozatok,
                            loero, tomeg, maximumFordulatszam, kocsiMintazat, kocsiDetail, kocsiFek,
                            kocsiKerek, ultetes, hatsoLokharitoTavolsag, kardanTengelyTavolsag, szint, meghajtas, kobcenti));
                }

            }

        }

        return autok;
    }

}
