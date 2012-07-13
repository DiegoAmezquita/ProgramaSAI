/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Codigo;

/**
 *
 * @author Diego
 */
public class Contacto {

     private String tipoContacto;
    private int codPerson;
    private int codContact;

    public int getCodContact() {
        return codContact;
    }

    public void setCodContact(int codContact) {
        this.codContact = codContact;
    }

    public int getCodPerson() {
        return codPerson;
    }

    public void setCodPerson(int codPerson) {
        this.codPerson = codPerson;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }



}
