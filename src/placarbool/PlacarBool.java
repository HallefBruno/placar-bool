package placarbool;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PlacarBool extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel;
    private StartPlacar startPlacar;
    private long temp;

    public PlacarBool() {
        initComponents();
        setLocationRelativeTo(null);
        
        lbBuscando.setBackground(Color.WHITE);
        lbBuscando.setOpaque(true);
        lbBuscando.setVisible(false);
        
        jLabel4.setOpaque(true);
        lbStop.setToolTipText("Parar");
        lbStop.setEnabled(false);
        
        lbStart.setBackground(Color.WHITE);
        lbStart.setOpaque(true);
        
        lbStop.setBackground(Color.WHITE);
        lbStop.setOpaque(true);
        
        rbVinteSegundos.setOpaque(true);
        rbSessentaSegundos.setOpaque(true);
        rbCentoVinteSegundos.setOpaque(true);
        

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Status");
        defaultTableModel.addColumn("Time 01");
        defaultTableModel.addColumn("X");
        defaultTableModel.addColumn("Time 02");

        defaultTableModel.addRow(new Object[]{"Nehum", "Nenhum", "X", "Nenhum"});
        jTable1.setModel(defaultTableModel);
        
        panelGifStopThread.setVisible(false);

    }

    public void jogosRodada(String link) throws IOException {
        Document html = Jsoup.connect(link).get();
        Elements containerPrincipal = html.getElementsByClass("row align-items-center content");
        List<Placar> placars = new ArrayList<>();
        Placar placar = new Placar();
        for (Element element : containerPrincipal) {
            String status = element.getElementsByTag("span").first().text();
            if (!status.contains("HOJE")) {
                String primeiroTime = element.getElementsByClass("text-right team_link").first().text();
                String golPrimeiroTime = element.getElementsByClass("badge badge-default").first().text();
                String segundoTime = element.getElementsByClass("text-left team_link").first().text();
                String separator = element.getElementsByClass("h5 text-center").first().text();
                String golSegundoTime = element.getElementsByClass("w-25 p-1 match-score d-flex justify-content-start").first().getElementsByClass("badge badge-default").first().text();

                System.out.println(status);
                System.out.println(primeiroTime);
                System.out.println(golPrimeiroTime);
                System.out.println(separator);
                System.out.println(segundoTime);
                System.out.println(golSegundoTime);

                placar = new Placar(status, primeiroTime + " " + golPrimeiroTime, separator, segundoTime + " " + golSegundoTime);
                placars.add(placar);
            }
        }

        popularTable();
        Object[] o = new Object[4];
        for (Placar p : placars) {
            o[0] = p.getStatus();
            o[1] = p.getTime01();
            o[2] = p.getSeparador();
            o[3] = p.getTime02();
            defaultTableModel.addRow(o);
            jTable1.setModel(defaultTableModel);
        }
    }

    private void popularTable() {
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.fireTableDataChanged();
        defaultTableModel.setRowCount(0);
        jTable1.setModel(defaultTableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbVinteSegundos = new javax.swing.JRadioButton();
        rbSessentaSegundos = new javax.swing.JRadioButton();
        rbCentoVinteSegundos = new javax.swing.JRadioButton();
        lbStart = new javax.swing.JLabel();
        lbStop = new javax.swing.JLabel();
        panelGifStopThread = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbGif = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        lbBuscando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Placar - Copa Brasil");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Link de busca:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 153, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 255, 51));
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/placarbool/icons/time.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Me atualize a cada:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Controle");

        rbVinteSegundos.setBackground(new java.awt.Color(255, 51, 0));
        buttonGroup1.add(rbVinteSegundos);
        rbVinteSegundos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbVinteSegundos.setForeground(new java.awt.Color(255, 255, 255));
        rbVinteSegundos.setText("30 Segundos");
        rbVinteSegundos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbVinteSegundosActionPerformed(evt);
            }
        });

        rbSessentaSegundos.setBackground(new java.awt.Color(0, 255, 0));
        buttonGroup1.add(rbSessentaSegundos);
        rbSessentaSegundos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbSessentaSegundos.setForeground(new java.awt.Color(255, 255, 255));
        rbSessentaSegundos.setText("60 Segundos");
        rbSessentaSegundos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSessentaSegundosActionPerformed(evt);
            }
        });

        rbCentoVinteSegundos.setBackground(new java.awt.Color(51, 102, 255));
        buttonGroup1.add(rbCentoVinteSegundos);
        rbCentoVinteSegundos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbCentoVinteSegundos.setForeground(new java.awt.Color(255, 255, 255));
        rbCentoVinteSegundos.setText("120 Segundos");
        rbCentoVinteSegundos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCentoVinteSegundosActionPerformed(evt);
            }
        });

        lbStart.setBackground(new java.awt.Color(255, 255, 255));
        lbStart.setForeground(new java.awt.Color(60, 63, 65));
        lbStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/placarbool/icons/power.png"))); // NOI18N
        lbStart.setToolTipText("Iniciar");
        lbStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbStartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbStartMouseExited(evt);
            }
        });

        lbStop.setBackground(new java.awt.Color(255, 255, 255));
        lbStop.setForeground(new java.awt.Color(60, 63, 65));
        lbStop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/placarbool/icons/stop-button.png"))); // NOI18N
        lbStop.setToolTipText("");
        lbStop.setPreferredSize(new java.awt.Dimension(24, 27));
        lbStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStopMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbStopMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbStopMouseExited(evt);
            }
        });

        panelGifStopThread.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Encerrando busca!");

        lbGif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/placarbool/icons/1475.gif"))); // NOI18N

        javax.swing.GroupLayout panelGifStopThreadLayout = new javax.swing.GroupLayout(panelGifStopThread);
        panelGifStopThread.setLayout(panelGifStopThreadLayout);
        panelGifStopThreadLayout.setHorizontalGroup(
            panelGifStopThreadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGifStopThreadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelGifStopThreadLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbGif)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGifStopThreadLayout.setVerticalGroup(
            panelGifStopThreadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGifStopThreadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGif, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbVinteSegundos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbSessentaSegundos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbCentoVinteSegundos))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbStart)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(101, 101, 101)
                                .addComponent(panelGifStopThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbVinteSegundos)
                    .addComponent(rbSessentaSegundos)
                    .addComponent(rbCentoVinteSegundos))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbStart)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelGifStopThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLayeredPane1.setLayout(new java.awt.FlowLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Placar Bool");
        jLayeredPane1.add(jLabel1);

        lbBuscando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBuscando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/placarbool/icons/831.gif"))); // NOI18N
        lbBuscando.setToolTipText("Buscando atualizações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbBuscando))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbBuscando)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbStartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStartMouseEntered
        lbStart.setBackground(new Color(60,63,65));//(new Color(204, 204, 204));
        lbStart.setOpaque(true);
    }//GEN-LAST:event_lbStartMouseEntered

    private void lbStartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStartMouseExited
        lbStart.setBackground(Color.WHITE);
        lbStart.setOpaque(true);
        //UIManager.put("JLabel.foreground", Color.black);
    }//GEN-LAST:event_lbStartMouseExited

    private void lbStopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStopMouseEntered
        lbStop.setBackground(new Color(60,63,65));
        lbStop.setOpaque(true);
    }//GEN-LAST:event_lbStopMouseEntered

    private void lbStopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStopMouseExited
        lbStop.setBackground(Color.WHITE);
        lbStop.setOpaque(true);
    }//GEN-LAST:event_lbStopMouseExited

    private void rbVinteSegundosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbVinteSegundosActionPerformed
        temp = Long.parseLong(rbVinteSegundos.getText().subSequence(0, 2).toString());
    }//GEN-LAST:event_rbVinteSegundosActionPerformed

    private void rbSessentaSegundosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSessentaSegundosActionPerformed
        temp = Long.parseLong(rbSessentaSegundos.getText().subSequence(0, 2).toString());
    }//GEN-LAST:event_rbSessentaSegundosActionPerformed

    private void rbCentoVinteSegundosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCentoVinteSegundosActionPerformed
        temp = Long.parseLong(rbCentoVinteSegundos.getText().subSequence(0, 3).toString());
    }//GEN-LAST:event_rbCentoVinteSegundosActionPerformed

    private void lbStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStartMouseClicked
        if (jTextField1.getText() != null && !jTextField1.getText().isEmpty() && temp > 0) {
            if(lbStart.isEnabled()) {
                startPlacar = new StartPlacar(temp);
                startPlacar.imediato = true;
                startPlacar.link = jTextField1.getText();
                startPlacar.running = true;
                startPlacar.start();

                lbStart.setEnabled(false);
                lbStop.setEnabled(true);
                
                lbBuscando.setVisible(true);
            }

        } else if (jTextField1.getText() == null || jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor insira o link!");
            jTextField1.setFocusable(true);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor selecione o tempo de busca!");
        }
    }//GEN-LAST:event_lbStartMouseClicked

    private void lbStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStopMouseClicked
        if(lbStop.isEnabled()) {
            lbStart.setEnabled(false);
            startPlacar.running = false;
            panelGifStopThread.setVisible(true);
            lbStop.setToolTipText("Por favor aguarde!");
            lbBuscando.setVisible(false);
        }
    }//GEN-LAST:event_lbStopMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlacarBool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new PlacarBool().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbBuscando;
    private javax.swing.JLabel lbGif;
    private javax.swing.JLabel lbStart;
    private javax.swing.JLabel lbStop;
    private javax.swing.JPanel panelGifStopThread;
    private javax.swing.JRadioButton rbCentoVinteSegundos;
    private javax.swing.JRadioButton rbSessentaSegundos;
    private javax.swing.JRadioButton rbVinteSegundos;
    // End of variables declaration//GEN-END:variables

    public class StartPlacar extends Thread {

        public long temp;
        public boolean running;
        public boolean imediato;
        public String link;

        public StartPlacar() {
        }

        public StartPlacar(long temp) {
            this.temp = temp;
            if (this.temp == 30) {
                this.temp = this.temp * 1000;
            } else if (this.temp == 60) {
                this.temp = this.temp * 1000;
            }
        }

        @Override
        public void run() {
            try {
                while (this.running) {
                    if (this.imediato) {
                        this.temp = 100;
                        this.imediato = false;
                    } else {
                        this.temp = 30 * 1000;
                    }
                    Thread.sleep(this.temp);
                    jogosRodada(this.link);
                }
                if (!this.running) {
                    Thread.interrupted();
                    lbStart.setEnabled(true);
                    lbStop.setEnabled(false);
                    lbStop.setToolTipText("Parar");
                    panelGifStopThread.setVisible(false);
                }

            } catch (IOException | InterruptedException ex) {
                this.running = false;
                JOptionPane.showMessageDialog(getRootPane(), "Um erro ocorreu!");
                Logger.getLogger(PrincipalUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
