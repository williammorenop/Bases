/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import controller.GrupoJpaController;
import controller.HistorialJpaController;
import controller.MiembroJpaController;
import controller.UsuarioJpaController;
import entities.Grupo;
import entities.Historial;
import entities.Miembro;
import entities.Rol;
import entities.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author johan
 */
public class CrearGrupo extends javax.swing.JFrame {

	private String[] nombresContacts={
			"Nombre","Email"
			};
        private Object[][] matriz;
        
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalPU");
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        GrupoJpaController controladorGrupo = new GrupoJpaController(emf);
        MiembroJpaController controladorMiembro = new MiembroJpaController(emf);
        HistorialJpaController controladorHistorial = new HistorialJpaController(emf);
        /**
     * Creates new form CrearGrupo
     */
    public CrearGrupo( String user ) {
        
        initComponents();
        this.crearTablaInfo(user);
        this.jPanel2.setVisible(false);

        other(user);
    }
    public CrearGrupo( String user , int grupoId ) {
        
        initComponents();
        this.crearTablaInfo(user);
        this.jPanel2.setVisible(true);
        
        
         
         short _grupoId = (short) grupoId;
         
         this.jTextField1.setText(controladorGrupo.findGrupo(_grupoId).getNombre());
         
         this.agregarLider(_grupoId);
         this.agregarMiembro(user, _grupoId);
         this.eliminarMiembro();
         this.disolverGrupo(user, _grupoId);
         
         
         
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Administrar grupos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Email"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Listo");

        jButton2.setText("+miembro");

        jButton3.setText("-miembro");

        jButton4.setText("Disolver grupo");

        jButton5.setText("+Lider");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)))
        );

        jTextField1.setText("Ingrese nombre");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(212, 212, 212))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(189, 189, 189))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//    //    /* Set the Nimbus look and feel */
//    //    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//    //    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//    //     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//    //     */
//    //    try {
//    //    //    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//    //    //    //    if ("Nimbus".equals(info.getName())) {
//    //    //    //    //    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//    //    //    //    //    break;
//    //    //    //    }
//    //    //    }
//    //    } catch (ClassNotFoundException ex) {
//    //    //    java.util.logging.Logger.getLogger(CrearGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    //    } catch (InstantiationException ex) {
//    //    //    java.util.logging.Logger.getLogger(CrearGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    //    } catch (IllegalAccessException ex) {
//    //    //    java.util.logging.Logger.getLogger(CrearGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    //    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//    //    //    java.util.logging.Logger.getLogger(CrearGrupo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    //    }
//    //    //</editor-fold>

//    //    /* Create and display the form */
//    //    java.awt.EventQueue.invokeLater(new Runnable() {
//    //    //    public void run() {
//    //    //    //    new CrearGrupo().setVisible(true);
//    //    //    }
//    //    });
//    }

    private void crearTablaInfo( String user ) {
                List< Usuario > temp =  controlador.getContactos(user) ;
                 matriz = new Object[temp.size()][2];
                for( int i = 0 ; i  < temp.size() ; ++i )
                {
                    matriz[ i ][ 0 ] = temp.get( i ).getNickName();
                    System.out.println( temp.get( i ).getNickName() );
                    matriz[ i ][ 1 ] = temp.get( i ).getEmail();
                      System.out.println( temp.get( i ).getEmail() );

                }
                DefaultTableModel model = new DefaultTableModel( matriz , this.nombresContacts );
                this.jTable1.setModel( model );
                repaint();
	}
    
    private void other( String user)
    {
        

        this.jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                              int[] users = jTable1.getSelectedRows();
                              String name = jTextField1.getText();
                              String estado = "activo";
                              short idMiembro = (short)controladorMiembro.getMaxId();
                              short idGrupo = (short)controladorGrupo.getMaxId();
                              idGrupo++;
                              Grupo grupo = new Grupo( idGrupo );
                              grupo.setEstado(estado);
                              grupo.setNombre(name);
                              short tipo = 0;
                              Rol rol  = new Rol(tipo);
                            try {
                                controladorGrupo.create(grupo);
                                System.out.println("se creo el grupo "+ name);
                            } catch (Exception ex) {
                                Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            for( int i = 0 ; i < users.length ; ++i )
                            {
                               idMiembro++;
                                Miembro miembro = new Miembro( idMiembro  );
                                miembro.setMonto(BigInteger.ZERO);
                                miembro.setUsuarioNickName( new Usuario((String)matriz[ i ][ 0 ]) );
                                miembro.setGrupoGrupoId(grupo);
                                  try {
                                      controladorMiembro.create(miembro);
                                       System.out.println("se creo el miembro "+ idMiembro);

                                  } catch (Exception ex) {
                                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  GregorianCalendar gc = new GregorianCalendar();
                                 System.out.println(gc.getTime().toString()+" "+idMiembro+" "+ idGrupo+" " + tipo );
                                  try {
                                      controladorHistorial.create( new Historial(gc.getTime() ,miembro,grupo,rol) ) ;
                                                                      System.out.println("se creo el Historial "+ idMiembro + " " + idGrupo +" "+tipo);

                                  } catch (Exception ex) {
                                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                                  }

                            }
                            tipo = 1;
                            rol.setRolId(tipo);
                            idMiembro++;
                                Miembro miembro = new Miembro( idMiembro  );
                                miembro.setMonto(BigInteger.ZERO);
                                miembro.setUsuarioNickName( new Usuario(user) );
                                miembro.setGrupoGrupoId(grupo);

                                  try {
                                      controladorMiembro.create(miembro);
                                       System.out.println("se creo el miembro "+ idMiembro);

                                  } catch (Exception ex) {
                                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  GregorianCalendar gc = new GregorianCalendar();
                                 
                                  try {
                                     controladorHistorial.create( new Historial(gc.getTime() ,miembro,grupo,rol) ) ;
                                                                      System.out.println("se creo el Historial "+ idMiembro + " " + idGrupo +" "+tipo);

                                  } catch (Exception ex) {
                                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                             
                        }
		});
    }
    
    private void agregarMiembro(  String user ,short idGrupo )
    {
        this.jButton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int[] users = jTable1.getSelectedRows();
                short idMiembro = (short)controladorMiembro.getMaxId();
                for( int i = 0 ; i < users.length ; ++i )
               {
                idMiembro++;
                Miembro miembro = new Miembro( idMiembro  );
                miembro.setMonto(BigInteger.ZERO);
                miembro.setUsuarioNickName( new Usuario((String)matriz[ i ][ 0 ]) );
                Grupo grupo = new Grupo(idGrupo);
                miembro.setGrupoGrupoId(grupo);
                short tipo = 0;
                Rol rol = new Rol(tipo);
                if( controladorMiembro.isPosible(miembro) )
                {
                  try {
                      controladorMiembro.create(miembro);
                       System.out.println("se creo el miembro "+ idMiembro);

                  } catch (Exception ex) {
                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  GregorianCalendar gc = new GregorianCalendar();
                // System.out.println(gc.getTime().toString()+" "+idMiembro+" "+ idGrupo+" " + tipo );
                  try {
                      controladorHistorial.create( new Historial(gc.getTime() ,miembro,grupo,rol) ) ;
                                                      //System.out.println("se creo el Historial "+ idMiembro + " " + idGrupo +" "+tipo);
                         JOptionPane.showMessageDialog(null,"El miembro "+miembro.getUsuarioNickName().getNickName()+" se agrego correctamente","Agregar miembro",JOptionPane.INFORMATION_MESSAGE);                               //System.out.println("se creo el Historial "+ idMiembro + " " + idGrupo +" "+tipo);

                  } catch (Exception ex) {
                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                                             JOptionPane.showMessageDialog(null,"El miembro "+miembro.getUsuarioNickName().getNickName()+" no se pudo agregar correctamente","Agregar miembro",JOptionPane.ERROR_MESSAGE);                               //System.out.println("se creo el Historial "+ idMiembro + " " + idGrupo +" "+tipo);

                  }
                    }
                else
                {
                    JOptionPane.showMessageDialog(null,"El miembro "+miembro.getUsuarioNickName().getNickName()+" ya esta agregado","Agregar miembro",JOptionPane.ERROR_MESSAGE);
                }
               }
            };
        });
    }
    private void agregarLider( short grupoId )
    {
     this.jButton5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int users = jTable1.getSelectedRow();
                short idMiembro = (short)controladorMiembro.getMaxId();
                
                idMiembro++;
                Miembro miembro = new Miembro( idMiembro  );
                miembro.setMonto(BigInteger.ZERO);
                miembro.setUsuarioNickName( new Usuario((String)matriz[ users ][ 0 ]) );
                Grupo grupo = new Grupo(grupoId);
                miembro.setGrupoGrupoId(grupo);
                short tipo = 1;
                Rol rol = new Rol(tipo);
                  try {
                      controladorMiembro.create(miembro);
                       System.out.println("se creo el miembro "+ idMiembro);

                  } catch (Exception ex) {
                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  GregorianCalendar gc = new GregorianCalendar();
                // System.out.println(gc.getTime().toString()+" "+idMiembro+" "+ idGrupo+" " + tipo );
                  try {
                      controladorHistorial.create( new Historial(gc.getTime() ,miembro,grupo,rol) ) ;
                       JOptionPane.showMessageDialog(null,"El lider se agrego correctamente","Agregar Lider",JOptionPane.INFORMATION_MESSAGE);                               //System.out.println("se creo el Historial "+ idMiembro + " " + idGrupo +" "+tipo);

                  } catch (Exception ex) {
                      Logger.getLogger(CrearGrupo.class.getName()).log(Level.SEVERE, null, ex);
                      JOptionPane.showMessageDialog(null,"El lider no se pudo agregar","Agregar Lider",JOptionPane.ERROR_MESSAGE);
                  }
               
            };
        });
    }
    private void eliminarMiembro()
    {
         this.jButton3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
            };
        });
    }
    private void disolverGrupo( String user, short idGrupo )
    {
        this.jButton4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                controladorGrupo.findGrupo(idGrupo).setEstado("Disuelto");
            };
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
