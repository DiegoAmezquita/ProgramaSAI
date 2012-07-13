/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego
 */
public class Configuracion {

    private Properties config;
    private InputStream is;
    private boolean configuracionCargada = false;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    public Configuracion() {
        config = new Properties();
        is = null;
    }

    public void cargarConfig() {

        try {
            is = new FileInputStream("config.properties");
            config.load(is);
            configuracionCargada = true;
        } catch (IOException ioe) {
            configuracionCargada = false;
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CARGAR EL ARCHIVO DE CONFIGURACI�N");
        }
    }

    public void cargarConfigXML() {
        try {

            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(new File("config.xml"));
            doc.getDocumentElement().normalize();

            System.out.println("El elemento raíz es: " + doc.getDocumentElement().getNodeName());

            configuracionCargada = true;
        } catch (ParserConfigurationException ex) {
            configuracionCargada = false;
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CARGAR EL ARCHIVO DE CONFIGURACIÓN");
           
        } catch (IOException ex) {
            configuracionCargada = false;
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CARGAR EL ARCHIVO DE CONFIGURACIÓN");
           
        } catch (SAXException ex) {
            configuracionCargada = false;
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CARGAR EL ARCHIVO DE CONFIGURACIÓN");
            
        }
    }

    private static String getTagValue(String sTag, Element eElement) {
        String resultado = "";
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue != null) {
            resultado = nValue.getNodeValue();
        }
        return resultado;

    }

    public String buscarValorXML(String valor) {
        String resultado = "";
        NodeList listaPersonas = doc.getElementsByTagName("mysql");
        for (int i = 0; i < listaPersonas.getLength(); i++) {
            Node persona = listaPersonas.item(i);
            if (persona.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) persona;
                System.out.println("Valor buscado : " + valor + " " + getTagValue(valor, elemento));
                resultado = getTagValue(valor, elemento);
            }
        }
        return resultado;

    }

    public void cerrarConfig() {
        try {
            is.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CERRAR EL ARCHIVO DE CONFIGURACIÓN");
        }
    }

    public String buscarValor(String key) {
        String valor = config.getProperty(key);
        return valor;
    }

    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }

    public boolean isConfiguracionCargada() {
        return configuracionCargada;
    }

    public void setConfiguracionCargada(boolean configuracionCargada) {
        this.configuracionCargada = configuracionCargada;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }  
}
