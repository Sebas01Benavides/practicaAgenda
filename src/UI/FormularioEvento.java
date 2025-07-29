/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;
import Modelo.Evento; 
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate; 
import java.time.LocalTime; 
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author sebas
 */
public class FormularioEvento extends javax.swing.JDialog {
    private Evento evento;
    private boolean esEdicion;
    private boolean accepted = false; // <-- NUEVA BANDERA: Indica si se aceptó el formulario

    /**
     * Creates new form FormularioEvento
     */
    public FormularioEvento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent); // Centra el formulario respecto a la ventana principal
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Si el usuario cierra con la 'X', se considera una cancelación
                accepted = false;
                // En modo edición, no anulamos el evento, simplemente no lo guardamos
                // ya que el objeto original ya existe y no queremos perder sus datos si se cancela.
                // Sin embargo, si tu lógica de 'Ventana' espera 'null' para no actualizar,
                // entonces sí deberías poner 'evento = null;'.
                // Para consistencia con el flujo de "cancelar", lo dejaremos como null.
                evento = null;
            }
        });
        this.esEdicion = false; // Modo agregar
        this.evento = new Evento(); // Se crea un nuevo evento vacío
        spinnerHora.setModel(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerHora, "HH:mm"); // Formato 24 horas
        spinnerHora.setEditor(timeEditor);
        limpiarCampos(); // Limpia los campos al iniciar en modo agregar
    }
    //
    public FormularioEvento(java.awt.Frame parent, boolean modal, Evento eventoAEditar) { // <--- THIS IS THE MISSING CONSTRUCTOR
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent); // Centra el formulario respecto a la ventana principal
        this.esEdicion = true; // Modo edición
        this.evento = eventoAEditar; // Se referencia el evento existente
        spinnerHora.setModel(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerHora, "HH:mm"); // Formato 24 horas
        spinnerHora.setEditor(timeEditor);
        cargarDatosEvento(); // Carga los datos del evento en los campos del formulario
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Si el usuario cierra con la 'X', se considera una cancelación
                accepted = false;
                // En modo edición, no anulamos el evento, simplemente no lo guardamos
                // ya que el objeto original ya existe y no queremos perder sus datos si se cancela.
                // Sin embargo, si tu lógica de 'Ventana' espera 'null' para no actualizar,
                // entonces sí deberías poner 'evento = null;'.
                // Para consistencia con el flujo de "cancelar", lo dejaremos como null.
                evento = null;
            }
        });
    }
    private void cargarDatosEvento() {
        if (evento != null) {
            txtNombreEvento.setText(evento.getNombreEvento());
            if (evento.getFecha() != null) {
                dateChooserEvento.setDate(Date.from(evento.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            // Lógica para cargar la hora en el JSpinner
            if (evento.getHora() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, evento.getHora().getHour());
                calendar.set(Calendar.MINUTE, evento.getHora().getMinute());
                spinnerHora.setValue(calendar.getTime());
            }
            txtDescripcion.setText(evento.getDescripcion());
            // Se elimina la lógica para txtUbicacion
            // if (txtUbicacion != null) {
            //     txtUbicacion.setText(evento.getUbicacion());
            // }
        }
    }
    
    private void limpiarCampos() {
        txtNombreEvento.setText("");
        dateChooserEvento.setDate(null);
        spinnerHora.setValue(new Date()); // Establece la hora actual por defecto
        txtDescripcion.setText("");
        // Se elimina la lógica para txtUbicacion
        // if (txtUbicacion != null) {
        //     txtUbicacion.setText("");
        // }
    }
    public Evento getEvento() { // <--- ¡ESTE MÉTODO ES CRÍTICO Y FALTABA!
        return evento;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateChooserEvento = new com.toedter.calendar.JDateChooser();
        txtNombreEvento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregarEvento = new javax.swing.JButton();
        btnCancelarEvento = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        spinnerHora = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre del evento");

        jLabel2.setText("Fecha");

        jLabel3.setText("Descripción");

        btnAgregarEvento.setText("Aceptar");
        btnAgregarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEventoActionPerformed(evt);
            }
        });

        btnCancelarEvento.setText("Cancelar");
        btnCancelarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEventoActionPerformed(evt);
            }
        });

        jLabel4.setText("Hora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarEvento)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreEvento)
                    .addComponent(dateChooserEvento, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(spinnerHora)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelarEvento)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDescripcion))
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dateChooserEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEvento)
                    .addComponent(btnCancelarEvento))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEventoActionPerformed
// Validación básica: Nombre del evento, Fecha y Descripción son obligatorios
        if (txtNombreEvento.getText().trim().isEmpty() ||
            dateChooserEvento.getDate() == null ||
            txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre del evento, Fecha y Descripción son campos obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate fechaEvento = dateChooserEvento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Date horaSpinnerValue = (Date) spinnerHora.getValue();
        LocalTime horaEvento = horaSpinnerValue.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        
        // Asigna los valores a la instancia de Evento
        evento.setNombreEvento(txtNombreEvento.getText().trim());
        evento.setFecha(fechaEvento);
        evento.setHora(horaEvento); // ¡Se asigna la hora!
        evento.setDescripcion(txtDescripcion.getText().trim());
        dispose(); // Cierra el JDialog

    }//GEN-LAST:event_btnAgregarEventoActionPerformed

    private void btnCancelarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEventoActionPerformed
        this.evento = null;
        dispose(); // Cierra el JDialog    
    }//GEN-LAST:event_btnCancelarEventoActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioEvento dialog = new FormularioEvento(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarEvento;
    private javax.swing.JButton btnCancelarEvento;
    private com.toedter.calendar.JDateChooser dateChooserEvento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner spinnerHora;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreEvento;
    // End of variables declaration//GEN-END:variables
}
