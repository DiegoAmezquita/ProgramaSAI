package interfazPersona;

import Codigo.Elemento;
import Codigo.OrdenServicio;
import Codigo.Reporte;
import Codigo.Datos;
import Codigo.Imprimir;
import Codigo.Persona;
import DAO.DAOElemento;

import DAO.DAOOrdenServicio;
import DAO.DAOPersona;
import Interfaz.DatosCellRenderer;
import Interfaz.FrameMain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelOrders extends JPanel {

    private JList listOrders;
    private JScrollPane scrollPaneOrders;
    private DefaultListModel modelListOrders;
    private JButton buttonReport;
    private JButton buttonEdit;
    private JButton buttonNew;
    private JButton buttonPrint;
    private int codPerson;
    private int codOrdenSeleccionada;
    private DAOOrdenServicio daoOrdenServicio;
    private DAOPersona daoPersona;
    private DAOElemento daoElemento;

    public PanelOrders(FrameMain frameMain) {
        setLayout(null);



        JLabel labelTitulo = new JLabel("Ordenes");
        labelTitulo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        labelTitulo.setBounds(20, 20, 850, 30);
        add(labelTitulo);
        modelListOrders = new DefaultListModel();
        listOrders = new JList(modelListOrders);
        listOrders.setName("listOrders");
        listOrders.addListSelectionListener(frameMain);
        listOrders.setCellRenderer(new DatosCellRenderer());
        scrollPaneOrders = new JScrollPane(listOrders);
        scrollPaneOrders.setBounds(20, 50, 850, 180);
        add(scrollPaneOrders);

        buttonPrint = new JButton("Imprimir");
        buttonPrint.setBounds(280, 250, 100, 30);
        buttonPrint.setActionCommand("IMPRIMIRORDENSERVICIO");
        buttonPrint.addActionListener(frameMain);
        add(buttonPrint);

        buttonReport = new JButton("Reporte");
        buttonReport.setBounds(400, 250, 100, 30);
        buttonReport.setActionCommand("REPORTEORDENSERVICIO");
        buttonReport.addActionListener(frameMain);
        add(buttonReport);

        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(520, 250, 100, 30);
        buttonEdit.setActionCommand("EDITARORDENSERVICIO");
        buttonEdit.addActionListener(frameMain);
        add(buttonEdit);

        buttonNew = new JButton("Nuevo");
        buttonNew.setBounds(640, 250, 100, 30);
        buttonNew.setActionCommand("NUEVAORDENSERVICIO");
        buttonNew.addActionListener(frameMain);
        add(buttonNew);
        bloquearElementos();
        daoOrdenServicio = new DAOOrdenServicio();
        daoPersona = new DAOPersona();
        daoElemento = new DAOElemento();

    }

    public void mostrarImpresionReporte() {
        Imprimir imp = new Imprimir();
        OrdenServicio os = daoOrdenServicio.consultar(codOrdenSeleccionada);
        Persona per = daoPersona.buscarPorCodigo(codPerson);
        ArrayList<Elemento> listaElementos = daoElemento.retornarElementosOrden(codOrdenSeleccionada);
        imp.setNumeroOrden(os.getNumero() + "");
        imp.setNombreCliente(per.getNombre() + " " + per.getApellido());
        imp.setDocumentoCliente(per.getNumeroDocumento() + "");
        imp.setFechaIngreso(os.getFechaCreacion());
        imp.setListaElementos(listaElementos);
        imp.setDescripcionOrden(os.getMotivo());
        imp.imprimir();



    }

    public void cargarOrdenes() {
        ArrayList<OrdenServicio> listaOrdenes = daoOrdenServicio.listarOrdenesPersona(codPerson);
        modelListOrders.clear();
        for (int i = 0; i < listaOrdenes.size(); i++) {

            String orden = listaOrdenes.get(i).getMotivo() + " " + listaOrdenes.get(i).getFechaCreacion();
            ArrayList<Elemento> listElements = daoElemento.retornarElementosOrden(listaOrdenes.get(i).getCodigo());
            for (int j = 0; j < listElements.size(); j++) {
                orden = orden + "  --  " + listElements.get(j).getTipo();
            }
            modelListOrders.addElement(new Datos(listaOrdenes.get(i).getCodigo(), orden));
        }
    }

    public void crearReporte() {
        OrdenServicio os = daoOrdenServicio.consultar(codOrdenSeleccionada);
        Reporte reporte = new Reporte(codOrdenSeleccionada + "");
        Persona per = daoPersona.buscarPorCodigo(codPerson);
        ArrayList<Elemento> listaElementos = daoElemento.retornarElementosOrden(codOrdenSeleccionada);
        int tamanioListaElementos = listaElementos.size();
        int elementosPorOrden = 8;
        int numeroReportes = tamanioListaElementos / elementosPorOrden;
        if ((tamanioListaElementos % elementosPorOrden) != 0) {
            numeroReportes = numeroReportes + 1;
        }
        int inicio = 0;
        for (int i = 0; i < numeroReportes; i++) {
            reporte.ponerImagenSAI();
            reporte.crearParrafoConFuente(new Paragraph("Soluciones Automatizadas Integrales SAI Ltda.",
                    FontFactory.getFont("arial", 15, BaseColor.BLACK)));
            reporte.crearParrafoConFuente(new Paragraph("NIT. 800.171.127 - 1 RÉGIMEN COMÚN",
                    FontFactory.getFont("arial", 12, BaseColor.BLACK)));
            reporte.crearParrafoConFuente(new Paragraph("RESOLUCIÓN DIAN No. 200000023863 - 2009/05/20 AUTORIZA DEL No. B 25001 AL No. B 38000",
                    FontFactory.getFont("arial", 8, BaseColor.BLACK)));
            reporte.crearParrafo("Numero de Orden No. B: " + os.getNumero());
            reporte.crearParrafo("Fecha Ingreso: " + os.getFechaCreacion());

            reporte.crearParrafo("Cliente: " + per.getNombre() + " " + per.getApellido() + " Documento:" + per.getNumeroDocumento());
            reporte.crearParrafo(" ");
            reporte.crearTabla(1);
            reporte.agregarCelda(new Phrase("Descripción", FontFactory.getFont("arial", 15, BaseColor.BLACK)));
            for (int j = inicio; j < (inicio + elementosPorOrden); j++) {
                if (j < listaElementos.size()) {
                    Elemento ele = listaElementos.get(j);
                    reporte.agregarCelda(new Phrase(ele.getTipo() + " "
                            + ele.getMarca() + "    "
                            + ele.getModelo() + "    "
                            + ele.getSerial() + "    "
                            + ele.getDescripcion(), FontFactory.getFont("arial", 9, BaseColor.BLACK)));
                }

            }

            inicio = inicio + elementosPorOrden;
            reporte.cerrarTabla();
            reporte.crearParrafo("Motivo: " + os.getMotivo());
            reporte.crearParrafo(" ");
            reporte.crearTabla(2);
            reporte.agregarCelda(new Phrase("ACEPTADA", FontFactory.getFont("arial", 15, BaseColor.BLACK)));
            reporte.agregarCelda(new Phrase("GARANTIA", FontFactory.getFont("arial", 15, BaseColor.BLACK)));
            reporte.agregarCelda(new Phrase("NOMBRE: ____________________________"
                    + " CC: ________________________________"
                    + " FECHA RECIBIDO (DD/MM/AA):___/___/___"
                    + "                                       "
                    + " FIRMA: _________________________________", FontFactory.getFont("arial", 12, BaseColor.BLACK)));

            reporte.agregarCelda(new Phrase("Garantia limitada a defectos de fabricacion. el usuario debe suministrar "
                    + "las condiciones ambientales, electricas y de seguridad adecuadas para el normal "
                    + "funcionamiento de los elementos. "
                    + "Para efectos de garantia se necesita presentar la factura "
                    + "y la mercancia con todos los accesorios y empaques originales", FontFactory.getFont("arial", 10, BaseColor.BLACK)));

            reporte.cerrarTabla();

            /*for (int i = 0; i < listaElementos.size(); i++) {

            Elemento ele = listaElementos.get(i);
            reporte.crearParrafo(ele.getTipo() + " " + ele.getMarca() + " " + ele.getModelo() + " "
            + ele.getSerial() + " " + ele.getEstado() + " " + ele.getDescripcion());
            reporte.crearParrafo("           ");
            reporte.crearTabla(4);
            reporte.agregarCelda("Descripcion");
            reporte.agregarCelda("Fecha Evento");
            reporte.agregarCelda("Reesponsable");
            reporte.agregarCelda("Tipo Evento");
            ArrayList<Evento> listaEventos = listarEventosElemento(ele.getCodigoElemento());
            for (int j = 0; j < listaEventos.size(); j++) {
            Evento even = listaEventos.get(j);
            reporte.agregarCelda(even.getDescripcionEvento());
            reporte.agregarCelda(even.getFechaEvento());
            reporte.agregarCelda(even.getResponsableEvento());
            reporte.agregarCelda(buscarTipoEvenPorCodigo(even.getTipoEvento()));
            }
            reporte.cerrarTabla();

            reporte.crearParrafo("____________________________________________________________________________");
            }
             */


        }
        reporte.cerrarReporte();
        abrirReporte();

    }

    public void abrirReporte() {

        try {
            File path = new File("REPORTES\\Reporte" + codOrdenSeleccionada + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void bloquearElementos() {
        listOrders.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonPrint.setEnabled(false);
        buttonNew.setEnabled(false);
        buttonReport.setEnabled(false);
    }

    public void personaSeleccionada() {
        listOrders.setEnabled(true);
        buttonNew.setEnabled(true);
    }

    public void ordenSeleccionada() {
        buttonEdit.setEnabled(true);
        buttonReport.setEnabled(true);
        buttonPrint.setEnabled(true);
    }

    public void ordenNoSeleccionada() {
        buttonEdit.setEnabled(false);
        buttonReport.setEnabled(false);
        buttonPrint.setEnabled(false);
    }

    public int getCodPerson() {
        return codPerson;
    }

    public void setCodPerson(int codPerson) {
        this.codPerson = codPerson;
    }

    public int getCodOrdenSeleccionada() {
        return codOrdenSeleccionada;
    }

    public void setCodOrdenSeleccionada(int codOrdenSeleccionada) {
        this.codOrdenSeleccionada = codOrdenSeleccionada;
    }
}
