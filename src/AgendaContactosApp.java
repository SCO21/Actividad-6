import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgendaContactosApp extends JFrame {
    private ArrayList<Contacto> listaContactos;
    private DefaultListModel<Contacto> listModel;
    private JList<Contacto> contactosJList;

    public AgendaContactosApp() {
        listaContactos = new ArrayList<>();
        listModel = new DefaultListModel<>();
        contactosJList = new JList<>(listModel);

        // Configuración de la ventana
        setTitle("Agenda de Contactos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Crear los componentes
        JPanel panelBotones = new JPanel();
        JButton btnCreate = new JButton("Create");
        JButton btnRead = new JButton("Read");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        // Agregar los botones al panel
        panelBotones.add(btnCreate);
        panelBotones.add(btnRead);
        panelBotones.add(btnUpdate);
        panelBotones.add(btnDelete);

        // Agregar el panel de botones y la lista de contactos a la ventana
        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(contactosJList), BorderLayout.CENTER);

        // Manejadores de eventos para los botones
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(AgendaContactosApp.this, "Ingrese el nombre:");
                String telefono = JOptionPane.showInputDialog(AgendaContactosApp.this, "Ingrese el teléfono:");

                Contacto contacto = new Contacto(nombre, telefono);
                listaContactos.add(contacto);
                listModel.addElement(contacto);
            }
        });

        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto contactoSeleccionado = contactosJList.getSelectedValue();
                if (contactoSeleccionado != null) {
                    JOptionPane.showMessageDialog(AgendaContactosApp.this,
                            "Nombre: " + contactoSeleccionado.getNombre() +
                                    "\nTeléfono: " + contactoSeleccionado.getTelefono());
                } else {
                    JOptionPane.showMessageDialog(AgendaContactosApp.this,
                            "No se ha seleccionado ningún contacto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto contactoSeleccionado = contactosJList.getSelectedValue();
                if (contactoSeleccionado != null) {
                    String nuevoNombre = JOptionPane.showInputDialog(AgendaContactosApp.this,
                            "Ingrese el nuevo nombre:", contactoSeleccionado.getNombre());
                    String nuevoTelefono = JOptionPane.showInputDialog(AgendaContactosApp.this,
                            "Ingrese el nuevo teléfono:", contactoSeleccionado.getTelefono());

                    contactoSeleccionado.setNombre(nuevoNombre);
                    contactoSeleccionado.setTelefono(nuevoTelefono);
                    listModel.setElementAt(contactoSeleccionado, contactosJList.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(AgendaContactosApp.this,
                            "No se ha seleccionado ningún contacto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto contactoSeleccionado = contactosJList.getSelectedValue();
                if (contactoSeleccionado != null) {
                    int confirmacion = JOptionPane.showConfirmDialog(AgendaContactosApp.this,
                            "¿Está seguro de eliminar el contacto?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        listaContactos.remove(contactoSeleccionado);
                        listModel.removeElement(contactoSeleccionado);
                    }
                } else {
                    JOptionPane.showMessageDialog(AgendaContactosApp.this,
                            "No se ha seleccionado ningún contacto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AgendaContactosApp app = new AgendaContactosApp();
                app.setVisible(true);
            }
        });
    }
}

class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
