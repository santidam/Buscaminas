/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crm.gui;
import com.mycompany.crm.exceptions.ComandaException;
import com.mycompany.crm.validator.Validations;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.netbeans.validation.api.ui.swing.ValidationPanel;

public class PruebaRegistro extends JDialog {
    private ValidationGroup group;
    private Validations validations = Validations.getInstance();
    
    private JTextField nombre;
    private JPasswordField password;
    private JButton jButton1;
    private ValidationPanel validationPanel1;
    private JPanel mainPanel;
    
    public PruebaRegistro(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Registro");
        setSize(440, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lbNombre = new JLabel(validations.valGetBundle().getString("LABEL_USUARIO"));
        nombre = new JTextField(15);
        nombre.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lbPassword = new JLabel(validations.valGetBundle().getString("LABEL_CONTRASENA"));
        password = new JPasswordField(15);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        
        nombre.setName("nombre");
        password.setName("contraseña");


        jButton1 = new JButton(validations.valGetBundle().getString("BTN_REGISTRARSE"));
        jButton1.setEnabled(false);
        jButton1.setBackground(new Color(34, 167, 240));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new Dimension(120, 40));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        validationPanel1 = new ValidationPanel();
        validationPanel1.setBackground(Color.WHITE);
        
        // Agregar componentes al panel
        gbc.gridx = 0; gbc.gridy = 0; mainPanel.add(lbNombre, gbc);
        gbc.gridx = 1; mainPanel.add(nombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; mainPanel.add(lbPassword, gbc);
        gbc.gridx = 1; mainPanel.add(password, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER; mainPanel.add(validationPanel1, gbc);
        gbc.gridy = 3; mainPanel.add(jButton1, gbc);

        // Validaciones
        group = validationPanel1.getValidationGroup();
        group.add(nombre, StringValidators.REQUIRE_NON_EMPTY_STRING, StringValidators.NO_WHITESPACE);
        group.add(password, StringValidators.REQUIRE_NON_EMPTY_STRING);

        // Listener para activar el botón solo si los campos son válidos
        nombre.getDocument().addDocumentListener(new FieldValidationListener());
        password.getDocument().addDocumentListener(new FieldValidationListener());
    }
    
    private class FieldValidationListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) { validarCampos(); }
        @Override
        public void removeUpdate(DocumentEvent e) { validarCampos(); }
        @Override
        public void changedUpdate(DocumentEvent e) { validarCampos(); }
    }
    
    private void validarCampos() {
        boolean nombreValido = !nombre.getText().trim().isEmpty();
        boolean passwordValido = !new String(password.getPassword()).trim().isEmpty();
        jButton1.setEnabled(nombreValido && passwordValido);
    }
    
    private void jButton1ActionPerformed(ActionEvent evt) {
        try {
            validations.valRegistrarUser(nombre.getText(), new String(password.getPassword()));
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (ComandaException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PruebaRegistro(null, true).setVisible(true));
    }
}
