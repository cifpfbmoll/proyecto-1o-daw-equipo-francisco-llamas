package java_proyecto;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.lang.Object;

/**
 *
 * @author Usuario
 */
public class main_Window extends javax.swing.JFrame {

    /**
     * Creates new form main_Window
     */
    String imgRuta = null;
    int pos = 0;

    /**
     *
     */
    public main_Window() {
        initComponents();
        obtenerConexion();
        mostrarDatosTabla();
    }

    /**
     *
     * @return
     */
    public Connection obtenerConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/coches_db", "root", "");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(main_Window.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //metodo para revisar que se rellenan los inputs
    public boolean revisarInputs() {
        if (txt_matricula.getText() == null || txt_marca.getText() == null
                || txt_modelo.getText() == null || txt_precio.getText() == null
                || txt_fecha.getText() == null || txt_color.getText() == null) {
            return false;
        } else {
            try {
                Float.parseFloat(txt_precio.getText());
                //el precio es un float, y por ello la reconversión.
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
    }

    //metodo para reescalar las imagenes y que quepan en el lbl de la imagen

    /**
     *
     * @param imgRuta
     * @param pic
     * @return
     */
    public ImageIcon RedimensionarImagen(String imgRuta, byte[] pic) {
        ImageIcon miImagen = null;
        if (imgRuta != null) {
            miImagen = new ImageIcon(imgRuta);
        } else {
            miImagen = new ImageIcon(pic);
        }
        Image img = miImagen.getImage();
        Image img2 = img.getScaledInstance(lbl_imagen.getWidth(), lbl_imagen.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(img2);
        return imagen;
    }

    //metodos para mostrar los datos en la tabla
    //1 - Llenar una Arraylist con los datos.

    /**
     *
     * @return
     */
    public ArrayList<coche> getData() {
        ArrayList<coche> listaCoches = new ArrayList<coche>();
        Connection con = obtenerConexion();
        String query = "SELECT * FROM coches";
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            coche Coche;
            while (rs.next()) {
                Coche = new coche(rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"),
                        Float.parseFloat(rs.getString("precio")), rs.getString("fecha_añadido"), rs.getString("color"),
                        rs.getBytes("imagen"));
                listaCoches.add(Coche);
            }
        } catch (SQLException ex) {
            Logger.getLogger(main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCoches;
    }
    
    //2 Poner los datos en la tabla

    /**
     *
     */
    public void mostrarDatosTabla(){
        ArrayList<coche> lista = getData();
        DefaultTableModel model = (DefaultTableModel)jTable_coches.getModel();
        model.setRowCount(0);
        Object[] fila = new Object[6];
        for(int i = 0; i< lista.size(); i++){
            fila[0]=lista.get(i).getMatricula();
            fila[1]=lista.get(i).getMarca();
            fila[2]=lista.get(i).getModelo();
            fila[3]=lista.get(i).getPrecio();
            fila[4]=lista.get(i).getFecha_añadido();
            fila[5]=lista.get(i).getColor();
            model.addRow(fila);
    }
        
    }
    
    /**
     *
     * @param indice
     */
    //muestra los datos en las entradas de informacion
    public void mostrarObjeto(int indice){
        txt_matricula.setText(getData().get(indice).getMatricula());
        txt_marca.setText(getData().get(indice).getMarca());
        txt_modelo.setText(getData().get(indice).getModelo());
        txt_precio.setText(Float.toString(getData().get(indice).getPrecio()));
        txt_fecha.setText(getData().get(indice).getFecha_añadido());
        txt_color.setText(getData().get(indice).getColor());
        lbl_imagen.setIcon(RedimensionarImagen(null, getData().get(indice).getImagen()));
        
    }
    
    /**
     *
     */
    public void limpiarCampos(){
        txt_matricula.setText(null);
        txt_marca.setText(null);
        txt_modelo.setText(null);
        txt_precio.setText(null);
        txt_fecha.setText(null);
        txt_color.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        precio_label = new javax.swing.JLabel();
        matricula_label = new javax.swing.JLabel();
        marca_label = new javax.swing.JLabel();
        fecha_label = new javax.swing.JLabel();
        color_label = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        modelo_label = new javax.swing.JLabel();
        txt_matricula = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        txt_marca = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_color = new javax.swing.JTextField();
        txt_modelo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_coches = new javax.swing.JTable();
        Btn_Escoger_Imagen = new javax.swing.JButton();
        update_button = new javax.swing.JButton();
        btn_añadir = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        btn_ultimo = new javax.swing.JButton();
        btn_primer = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lbl_imagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        precio_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        precio_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        precio_label.setText("Introduzca el precio del vehículo:");

        matricula_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        matricula_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        matricula_label.setText("Introduzca la matricula:");

        marca_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        marca_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        marca_label.setText("Introduzca la marca del vehículo:");

        fecha_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        fecha_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fecha_label.setText("Introduzca la fecha:");

        color_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        color_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        color_label.setText("Introduzca el color del vehículo:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Imagen:");

        modelo_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        modelo_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        modelo_label.setText("Introduzca el modelo del vehículo:");

        txt_matricula.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_matricula.setPreferredSize(new java.awt.Dimension(59, 45));
        txt_matricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_matriculaActionPerformed(evt);
            }
        });

        txt_fecha.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_fecha.setPreferredSize(new java.awt.Dimension(59, 45));
        txt_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fechaActionPerformed(evt);
            }
        });

        txt_marca.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_marca.setPreferredSize(new java.awt.Dimension(59, 45));
        txt_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_marcaActionPerformed(evt);
            }
        });

        txt_precio.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_precio.setPreferredSize(new java.awt.Dimension(59, 45));
        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });

        txt_color.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_color.setPreferredSize(new java.awt.Dimension(59, 45));
        txt_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_colorActionPerformed(evt);
            }
        });

        txt_modelo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_modelo.setPreferredSize(new java.awt.Dimension(59, 45));
        txt_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_modeloActionPerformed(evt);
            }
        });

        jTable_coches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Marca", "Modelo", "Precio", "Fecha", "Color"
            }
        ));
        jTable_coches.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_cochesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_coches);

        Btn_Escoger_Imagen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Escoger_Imagen.setText("Escoger imagen");
        Btn_Escoger_Imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Escoger_ImagenActionPerformed(evt);
            }
        });

        update_button.setBackground(new java.awt.Color(153, 153, 153));
        update_button.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        update_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes.icons/update.png"))); // NOI18N
        update_button.setText("Actualizar Vehiculo");
        update_button.setIconTextGap(10);
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        btn_añadir.setBackground(new java.awt.Color(153, 153, 153));
        btn_añadir.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_añadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes.icons/add.png"))); // NOI18N
        btn_añadir.setText("Añadir Vehiculo");
        btn_añadir.setIconTextGap(10);
        btn_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_añadirActionPerformed(evt);
            }
        });

        delete_button.setBackground(new java.awt.Color(153, 153, 153));
        delete_button.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        delete_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes.icons/delete.png"))); // NOI18N
        delete_button.setText("Borrar Vehiculo");
        delete_button.setIconTextGap(10);
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        btn_ultimo.setBackground(new java.awt.Color(153, 153, 153));
        btn_ultimo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_ultimo.setText("Ultimo Vehiculo");
        btn_ultimo.setIconTextGap(10);
        btn_ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ultimoActionPerformed(evt);
            }
        });

        btn_primer.setBackground(new java.awt.Color(153, 153, 153));
        btn_primer.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_primer.setText("Primer Vehiculo");
        btn_primer.setIconTextGap(10);
        btn_primer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_primerActionPerformed(evt);
            }
        });

        btn_next.setBackground(new java.awt.Color(153, 153, 153));
        btn_next.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes.icons/next.png"))); // NOI18N
        btn_next.setText("Siguiente");
        btn_next.setIconTextGap(10);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_back.setBackground(new java.awt.Color(153, 153, 153));
        btn_back.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes.icons/previous.png"))); // NOI18N
        btn_back.setText("Anterior");
        btn_back.setIconTextGap(10);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Felix Titling", 3, 36)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("VEHICULOS LLAMAS");
        jLabel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 255), 5, true));

        lbl_imagen.setBackground(new java.awt.Color(204, 255, 255));
        lbl_imagen.setOpaque(true);

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(matricula_label, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(modelo_label, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(marca_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(precio_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecha_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(color_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_color, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txt_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txt_marca, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txt_matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Escoger_Imagen, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(lbl_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(426, 426, 426)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_primer, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(173, 173, 173)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(416, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(matricula_label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marca_label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modelo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(precio_label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fecha_label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(color_label, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lbl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Escoger_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_primer, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 625, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_matriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_matriculaActionPerformed

    }//GEN-LAST:event_txt_matriculaActionPerformed

    private void txt_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaActionPerformed

    private void txt_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_marcaActionPerformed

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioActionPerformed

    private void txt_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_colorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_colorActionPerformed

    private void txt_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_modeloActionPerformed

    private void Btn_Escoger_ImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Escoger_ImagenActionPerformed

        JFileChooser archivo = new JFileChooser();
        archivo.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png");
        archivo.addChoosableFileFilter(filter);
        int result = archivo.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = archivo.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_imagen.setIcon(RedimensionarImagen(path, null));
            imgRuta = path;
        } else {
            System.out.println("No has escogido ningun archivo.");
        }
    }//GEN-LAST:event_Btn_Escoger_ImagenActionPerformed
    //metodo para hacer deletes en la BBDD
    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        if (!txt_matricula.getText().equals("")) {
            try {
                Connection con = obtenerConexion();
                PreparedStatement ps = con.prepareStatement("DELETE FROM coches WHERE matricula = ?");
                String matricula = txt_matricula.getText();
                ps.setString(1, matricula);
                ps.executeUpdate();
                mostrarDatosTabla();
                JOptionPane.showMessageDialog(null, "Vehiculo borrado");
            } catch (SQLException ex) {
                Logger.getLogger(main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "El vehiculo no se ha borrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El vehiculo no se ha borrado, no hay ningúna ID como la que ha escrito.");
        }
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void btn_ultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ultimoActionPerformed
        pos = getData().size()-1;
        mostrarObjeto(pos);
    }//GEN-LAST:event_btn_ultimoActionPerformed

    //metodo para hacer inserts en la BBDD
    private void btn_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_añadirActionPerformed
        if (revisarInputs() && imgRuta != null) {
            try {
                Connection con = obtenerConexion();
                PreparedStatement ps = con.prepareStatement("INSERT INTO coches(matricula,marca,modelo,precio,fecha_añadido,color,imagen)"
                        + "values(?,?,?,?,?,?,?) ");
                ps.setString(1, txt_matricula.getText());
                ps.setString(2, txt_marca.getText());
                ps.setString(3, txt_modelo.getText());
                ps.setString(4, txt_precio.getText());
                ps.setString(5, txt_fecha.getText());
                ps.setString(6, txt_color.getText());

                InputStream img = new FileInputStream(new File(imgRuta));
                ps.setBlob(7, img);
                ps.executeUpdate();
                mostrarDatosTabla();
                JOptionPane.showMessageDialog(null, "Vehiculo añadido");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Uno o mas campos están vacíos. Debe añadir todos los campos.");
        }
        File archivoSalida = new File("C:/Users/Usuario/Desktop/ficherosCoches/vehiculo.txt");
        try {
            FileWriter escrito = new FileWriter(archivoSalida);
            escrito.write(matricula_label.getText() + " : "+txt_matricula.getText());
            escrito.write(marca_label.getText() + " : "+txt_marca.getText());
            escrito.write(modelo_label.getText() + " : "+txt_modelo.getText());
            escrito.write(precio_label.getText() + " : "+txt_precio.getText());
            escrito.write(fecha_label.getText() + " : "+txt_fecha.getText());
            escrito.write(color_label.getText() + " : "+txt_color.getText());
            escrito.close();
        } catch (IOException ex) {
            Logger.getLogger(main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_añadirActionPerformed

    //metodo para hacer updates en la BBDD
    //checkea que los inputs no esten vacios.
    //Si la ruta de la imagen se ha rellenado, se hace update de la imagen
    //De lo contrario, no se actualiza la imagen
    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        if (revisarInputs() && txt_matricula.getText() != null) {
            String updateQuery = null;
            PreparedStatement ps = null;
            Connection con = obtenerConexion();
            if (imgRuta == null) {
                try {
                    updateQuery = "UPDATE coches SET marca = ?, modelo = ?, precio = ?, fecha_añadido = ?, color = ?, WHERE matricula = ?";
                    ps = con.prepareStatement(updateQuery);
                    ps.setString(1, txt_marca.getText());
                    ps.setString(2, txt_modelo.getText());
                    ps.setString(3, txt_precio.getText());
                    ps.setString(4, txt_fecha.getText());
                    ps.setString(5, txt_color.getText());
                    ps.setString(6, txt_matricula.getText());
                    ps.executeUpdate();
                    mostrarDatosTabla();
                    JOptionPane.showMessageDialog(null, "Vehiculo Actualizado");
                } catch (SQLException ex) {
                    Logger.getLogger(main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    InputStream img = new FileInputStream(new File(imgRuta));
                    updateQuery = "UPDATE coches SET marca = ?, modelo = ?, precio = ?, fecha_añadido = ?, color = ?, imagen = ? WHERE matricula = ?";
                    ps = con.prepareStatement(updateQuery);
                    ps.setString(1, txt_marca.getText());
                    ps.setString(2, txt_modelo.getText());
                    ps.setString(3, txt_precio.getText());
                    ps.setString(4, txt_fecha.getText());
                    ps.setString(5, txt_color.getText());
                    ps.setBlob(6, img);
                    ps.setString(7, txt_matricula.getText());
                    ps.executeUpdate();
                    mostrarDatosTabla();
                    JOptionPane.showMessageDialog(null, "Vehiculo Actualizado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Uno o mas campos están vacíos o son erróneos");
        }
    }//GEN-LAST:event_update_buttonActionPerformed

    private void jTable_cochesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_cochesMouseClicked
        int indice = jTable_coches.getSelectedRow();
        mostrarObjeto(indice);
    }//GEN-LAST:event_jTable_cochesMouseClicked

    private void btn_primerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_primerActionPerformed
        pos = 0;
        mostrarObjeto(pos);
    }//GEN-LAST:event_btn_primerActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if(pos >=getData().size()){
            pos = getData().size()-1;
        }
        mostrarObjeto(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        pos--;
        if(pos < 0){
            pos = 0;
        }
        mostrarObjeto(pos);
    }//GEN-LAST:event_btn_backActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     * @param args
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
            java.util.logging.Logger.getLogger(main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Escoger_Imagen;
    private javax.swing.JButton btn_añadir;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_primer;
    private javax.swing.JButton btn_ultimo;
    private javax.swing.JLabel color_label;
    private javax.swing.JButton delete_button;
    private javax.swing.JLabel fecha_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_coches;
    private javax.swing.JLabel lbl_imagen;
    private javax.swing.JLabel marca_label;
    private javax.swing.JLabel matricula_label;
    private javax.swing.JLabel modelo_label;
    private javax.swing.JLabel precio_label;
    private javax.swing.JTextField txt_color;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_matricula;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables

}
