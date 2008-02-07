/**
 * 
 */
package salidaPantalla;

/**
 * 
 */
public class VentanaInfo extends javax.swing.JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private VPrincipal referencia;
	
	/**
	 * 
	 */
    public VentanaInfo() {
        initComponents();
    }
    
    /**
     * 
     * @param r
     * @param mensaje
     */
    public VentanaInfo(VPrincipal r,String mensaje) {
        referencia = r;
        initComponents();
        jTextArea1.setText(mensaje);
        referencia.setEnabled(false);
        jTextArea1.setEditable(false);
        
    }
    
    /**
     * 
     */
    public void cambiaTamanio(){
    	
    	this.setBounds(100, 100, 600, 400);
    	jScrollPane1.setBounds(2, 2, 590, 300);
    	jButton1.setBounds(250, 330, 80, 20);
    	jDesktopPane1.setBackground(new java.awt.Color(215, 115,15 ));
    }
    
    /**
 	 * 
 	 */
    // <editor-fold defaultstate="collapsed" desc=" C�digo Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(102, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane1.setBounds(2, 2, 296, 223);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Aceptar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton1.setBounds(110, 250, 80, 20);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        pack();
        setSize(307, 320);
        setLocation(100, 100);
        setResizable(false);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    	referencia.setEnabled(true);
     	dispose();
    }//GEN-LAST:event_jButton1MouseClicked
    
    
    // Declaraci�on de variables - no modificar//GEN-BEGIN:variables
    /**
     * 
     */
    private javax.swing.JButton jButton1;
    
    /**
     * 
     */
    private javax.swing.JDesktopPane jDesktopPane1;
    
    /**
     * 
     */
    private javax.swing.JScrollPane jScrollPane1;
    
    /**
     * 
     */
    private javax.swing.JTextArea jTextArea1;
    // Fin de declaraci�n de variables//GEN-END:variables
}
