/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatkezeles;

import alaposztalyok.AutoT;
import alaposztalyok.Jatekos;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kunec
 */
public interface AdatInput {

    public void kapcsolodas() throws ClassNotFoundException, SQLException;

    public void autoKapcsolodas() throws ClassNotFoundException, SQLException;

    public List<Jatekos> listAll() throws Exception;

    public List<AutoT> listAllAuto() throws Exception;

    public void createJatekos(Jatekos jatekos) throws Exception;

    public void updateJatekos(Jatekos jatekos) throws Exception;
}
