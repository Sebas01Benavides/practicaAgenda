package UI;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import Modelo.Contacto;
import Modelo.Evento;
import DAO.ContactosDAO; 
import DAO.EventosDAO;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import com.toedter.calendar.JCalendar; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.multi.MultiLookAndFeel;
/**
 *
 * @author sebas
 */
public class Ventana extends javax.swing.JFrame {
    private ContactosDAO contactoDAO;
    private EventosDAO eventoDAO;

    /**
     * Constructor de la Ventana.
     * Inicializa los componentes de la UI y centra la ventana.
     * Configura los modelos de tabla iniciales.
     */
    public Ventana() {
        initComponents(); // Llama al método initComponents() generado por NetBeans
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono.jpeg")).getImage());
        // Inicializamos las instancias de los DAOs
        contactoDAO = new ContactosDAO();
        eventoDAO = new EventosDAO();
        // Aquí es donde se establece el modelo para tablaContactos y tablaEventos
        tablaContactos.setModel(new ModeloTablaContacto(new ArrayList<>()));
        tablaEventos.setModel(new ModeloTablaEvento(new ArrayList<>()));

        // Carga y actualiza las tablas al iniciar la aplicación desde la base de datos
        actualizarTablaContactos();
        actualizarTablaEventos();
    }

    public class ModeloTablaContacto extends AbstractTableModel {
        private final String[] columnas = {"ID", "Nombre", "Teléfono", "Email"};
        private List<Contacto> lista;
        /**
         * Constructor del modelo de tabla de contactos.
         * @param lista La lista de objetos Contacto a mostrar.
         */
        public ModeloTablaContacto(List<Contacto> lista) {
            this.lista = lista;
        }
        /**
         * Establece una nueva lista de contactos y notifica a la tabla para que se actualice.
         * @param nuevaLista La nueva lista de contactos.
         */
        public void setListaContactos(List<Contacto> nuevaLista) {
            this.lista = nuevaLista;
            fireTableDataChanged(); // Notifica a la vista que los datos han cambiado
        }

        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return columnas.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Contacto c = lista.get(rowIndex);
            switch (columnIndex) {
                case 0: return c.getId(); // Retorna el ID del contacto
                case 1: return c.getNombres() + " " + c.getApellidos();
                case 2: return c.getTelefono();
                case 3: return c.getEmail();
                default: return "";
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnas[column];
        }
    }

    /**
     * Modelo de tabla personalizado para mostrar objetos Evento en un JTable.
     * 
     */
    public class ModeloTablaEvento extends AbstractTableModel {

        // Columnas para la tabla de eventos: ID, Nombre Evento, Fecha, Hora, Descripción
        private final String[] columnas = {"ID", "Nombre Evento", "Fecha", "Hora", "Descripción"};
        private List<Evento> lista;
        // Formateadores para mostrar la fecha y hora en un formato legible
        private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        /**
         * Constructor del modelo de tabla de eventos.
         * @param lista La lista de objetos Evento a mostrar.
         */
        public ModeloTablaEvento(List<Evento> lista) {
            this.lista = lista;
        }

        /**
         * Establece una nueva lista de eventos y notifica a la tabla para que se actualice.
         * @param nuevaLista La nueva lista de eventos.
         */
        public void setListaEventos(List<Evento> nuevaLista) {
            this.lista = nuevaLista;
            fireTableDataChanged(); // Notifica a la vista que los datos han cambiado
        }

        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return columnas.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Evento e = lista.get(rowIndex);
            switch (columnIndex) {
                case 0: return e.getId(); // Retorna el ID del evento
                case 1: return e.getNombreEvento();
                case 2: return e.getFecha() != null ? e.getFecha().format(dateFormatter) : "";
                case 3: return e.getHora() != null ? e.getHora().format(timeFormatter) : "";
                case 4: return e.getDescripcion();
                default: return "";
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnas[column];
        }
    }

    /**
     * Método para recargar la tabla de contactos desde la base de datos.
     */
    private void actualizarTablaContactos() {
        List<Contacto> contactos = contactoDAO.obtenerTodosContactos(); // Obtiene contactos de la BD
        ((ModeloTablaContacto) tablaContactos.getModel()).setListaContactos(contactos);
    }

    /**
     * Método para recargar la tabla de eventos desde la base de datos.
     */
    private void actualizarTablaEventos() {
        List<Evento> eventos = eventoDAO.obtenerTodosEventos(); // Obtiene eventos de la BD
        ((ModeloTablaEvento) tablaEventos.getModel()).setListaEventos(eventos);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaContactos = new javax.swing.JTable();
        btnBuscarContacto = new javax.swing.JButton();
        txtBusquedaContacto = new javax.swing.JTextField();
        btnAgregarContacto = new javax.swing.JButton();
        btnEditarContacto = new javax.swing.JButton();
        btnEliminarContacto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        btnAgregarEvento = new javax.swing.JButton();
        btnEditarEvento = new javax.swing.JButton();
        btnEliminarEvento = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agenda");

        tablaContactos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Telefono", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaContactos);
        if (tablaContactos.getColumnModel().getColumnCount() > 0) {
            tablaContactos.getColumnModel().getColumn(0).setResizable(false);
            tablaContactos.getColumnModel().getColumn(1).setResizable(false);
            tablaContactos.getColumnModel().getColumn(2).setResizable(false);
            tablaContactos.getColumnModel().getColumn(3).setResizable(false);
        }

        btnBuscarContacto.setText("Buscar");
        btnBuscarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarContactoActionPerformed(evt);
            }
        });

        btnAgregarContacto.setText("Agregar contacto");
        btnAgregarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarContactoActionPerformed(evt);
            }
        });

        btnEditarContacto.setText("Editar contacto");
        btnEditarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarContactoActionPerformed(evt);
            }
        });

        btnEliminarContacto.setText("Eliminar contacto");
        btnEliminarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarContactoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBusquedaContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarContacto, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarContacto)
                    .addComponent(txtBusquedaContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarContacto)
                    .addComponent(btnEliminarContacto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarContacto)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contactos", jPanel1);

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaEventos);

        btnAgregarEvento.setText("Agregar evento");
        btnAgregarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEventoActionPerformed(evt);
            }
        });

        btnEditarEvento.setText("Editar evento");
        btnEditarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEventoActionPerformed(evt);
            }
        });

        btnEliminarEvento.setText("Eliminar evento");
        btnEliminarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregarEvento)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarEvento)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEvento)
                    .addComponent(btnEditarEvento)
                    .addComponent(btnEliminarEvento))
                .addGap(18, 18, 18)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agenda", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEventoActionPerformed
        int filaSeleccionada = tablaEventos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtenemos el ID del evento de la tabla
            int eventoId = (int) tablaEventos.getValueAt(filaSeleccionada, 0);

            // Obtenemos el evento completo desde la base de datos usando su ID
            Evento eventoAEditar = eventoDAO.obtenerEventoPorId(eventoId);

            if (eventoAEditar != null) {
                FormularioEvento formulario = new FormularioEvento(this, true, eventoAEditar);
                formulario.setVisible(true);

                // Solo procede si el formulario fue aceptado
                if (formulario.getEvento() != null) { // isAccepted() se reemplaza por getEvento() != null
                    if (eventoDAO.actualizarEvento(formulario.getEvento())) { // Actualiza en la BD
                        actualizarTablaEventos(); // Recarga la tabla desde la BD
                        JOptionPane.showMessageDialog(this, "Evento actualizado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar evento.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar el evento para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un evento para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarEventoActionPerformed
        
    private void btnAgregarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEventoActionPerformed
     FormularioEvento formulario = new FormularioEvento(this, true);
        formulario.setVisible(true);
        // Solo procede si el formulario devolvió un evento no nulo
        if (formulario.getEvento() != null) { // isAccepted() se reemplaza por getEvento() != null
            Evento nuevo = formulario.getEvento();
            if (eventoDAO.guardarEvento(nuevo)) { // Guarda en la BD
                actualizarTablaEventos(); // Recarga la tabla desde la BD
                JOptionPane.showMessageDialog(this, "Evento agregado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar evento.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarEventoActionPerformed

    private void btnAgregarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarContactoActionPerformed
            FormularioContacto formulario = new FormularioContacto(this, true);
        formulario.setVisible(true);
        // Solo procede si el formulario fue aceptado y devolvió un contacto no nulo
        if (formulario.getContacto() != null) { // isAccepted() se reemplaza por getContacto() != null
            Contacto nuevo = formulario.getContacto();
            if (contactoDAO.guardarContacto(nuevo)) { // Guarda en la BD
                actualizarTablaContactos(); // Recarga la tabla desde la BD
                JOptionPane.showMessageDialog(this, "Contacto agregado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar contacto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarContactoActionPerformed

    private void btnEditarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarContactoActionPerformed
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtenemos el ID del contacto de la tabla (asumiendo que la columna 0 es el ID)
            int contactoId = (int) tablaContactos.getValueAt(filaSeleccionada, 0);
            // Obtenemos el contacto completo desde la base de datos usando su ID
            Contacto contactoAEditar = contactoDAO.obtenerContactoPorId(contactoId);
            if (contactoAEditar != null) {
                FormularioContacto formulario = new FormularioContacto(this, true, contactoAEditar);
                formulario.setVisible(true);
                // Solo procede si el formulario fue aceptado
                if (formulario.getContacto() != null) { // isAccepted() se reemplaza por getContacto() != null
                    // El objeto 'contactoAEditar' ya fue modificado directamente por el diálogo
                    // si el formulario opera de esa manera. Si devuelve un nuevo objeto,
                    // se usa el que devuelve el formulario.
                    if (contactoDAO.actualizarContacto(formulario.getContacto())) { // Actualiza en la BD
                        actualizarTablaContactos(); // Recarga la tabla desde la BD
                        JOptionPane.showMessageDialog(this, "Contacto actualizado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar contacto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cargar el contacto para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un contacto para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarContactoActionPerformed

    private void btnEliminarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarContactoActionPerformed
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este contacto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtenemos el ID del contacto de la tabla
                int contactoId = (int) tablaContactos.getValueAt(filaSeleccionada, 0);
                if (contactoDAO.eliminarContacto(contactoId)) { // Elimina de la BD
                    actualizarTablaContactos(); // Recarga la tabla desde la BD
                    JOptionPane.showMessageDialog(this, "Contacto eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar contacto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un contacto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarContactoActionPerformed

    private void btnBuscarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarContactoActionPerformed
        String textoBusqueda = txtBusquedaContacto.getText().trim();
        List<Contacto> resultadosBusqueda;
        if (textoBusqueda.isEmpty()) {
            resultadosBusqueda = contactoDAO.obtenerTodosContactos(); // Si está vacío, muestra todos
        } else {
            resultadosBusqueda = contactoDAO.buscarContactos(textoBusqueda); // Busca en la BD
        }
        ((ModeloTablaContacto) tablaContactos.getModel()).setListaContactos(resultadosBusqueda);
    }//GEN-LAST:event_btnBuscarContactoActionPerformed

    private void btnEliminarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEventoActionPerformed
        int filaSeleccionada = tablaEventos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este evento?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtenemos el ID del evento de la tabla
                int eventoId = (int) tablaEventos.getValueAt(filaSeleccionada, 0);
                if (eventoDAO.eliminarEvento(eventoId)) { // Elimina de la BD
                    actualizarTablaEventos(); // Recarga la tabla desde la BD
                    JOptionPane.showMessageDialog(this, "Evento eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar evento.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un evento para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarEventoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FastLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Ventana().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarContacto;
    private javax.swing.JButton btnAgregarEvento;
    private javax.swing.JButton btnBuscarContacto;
    private javax.swing.JButton btnEditarContacto;
    private javax.swing.JButton btnEditarEvento;
    private javax.swing.JButton btnEliminarContacto;
    private javax.swing.JButton btnEliminarEvento;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaContactos;
    private javax.swing.JTable tablaEventos;
    private javax.swing.JTextField txtBusquedaContacto;
    // End of variables declaration//GEN-END:variables
}
