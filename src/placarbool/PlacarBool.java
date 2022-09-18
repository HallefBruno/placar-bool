package placarbool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PlacarBool extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel;
    private StartPlacar startPlacar;
    private long temp;
    private TableColumn columnStatus;
    private TableColumn columnPrimeiroTime;
    private TableColumn columnSegundoTime;
    private TableColumn columnGolsPrimeiroTime;
    private TableColumn columnGolsSegundoTime;
    private TableColumn columnLink;

    public PlacarBool() {
        initComponents();

        jLabel4.setOpaque(true);
        lbStop.setToolTipText("Parar");
        lbStop.setEnabled(false);
        
        lbBuscando.setVisible(false);

        lbStart.setBackground(Color.WHITE);
        lbStart.setOpaque(true);

        lbStop.setBackground(Color.WHITE);
        lbStop.setOpaque(true);

        rbVinteSegundos.setOpaque(true);
        rbSessentaSegundos.setOpaque(true);
        rbCentoVinteSegundos.setOpaque(true);

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Status");
        defaultTableModel.addColumn("Primeiro Time");
        defaultTableModel.addColumn("Gols");
        defaultTableModel.addColumn("Segundo Time");
        defaultTableModel.addColumn("Gols");
        defaultTableModel.addColumn("Assistir partida");

        jTable1.setModel(defaultTableModel);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setDefaultEditor(Object.class, null);

        panelGifStopThread.setVisible(false);
        setLocationByPlatform(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);

    }

    public void jogosRodada(String link) throws IOException {
        
        Document html = Jsoup.connect(link).get();
        
        Element elementBoby = html.select("div#livescore").first();
        
        List<Placar> placars = new ArrayList<>();
        Placar placar = new Placar();
        
        for (Element elementPai : elementBoby.children()) {
            Elements containers = elementPai.getElementsByClass("container content");
            if (containers != null && !containers.isEmpty()) {
                for (Element containerPrincipal : containers.first().children()) {
                    placar = new Placar();
                    Element status = containerPrincipal.getElementsByTag("span").first();
                    String linkAcompanharAoVivo = containerPrincipal.select("a").attr("href");
                    if (Objects.nonNull(status) && Objects.nonNull(status.text())) {
                        if (status.text().contains("HOJE") || status.text().contains("AMANHÃ")) {
                            placar.setStatus(status.text());
                            placar.setPrimeiroTime(containerPrincipal.getElementsByClass("text-right team_link").first().text());
                            placar.setGolsPrimeiroTime("0");
                            placar.setSegundoTime(containerPrincipal.getElementsByClass("text-left team_link").first().text());
                            placar.setGolsSeguntoTime("0");
                            placars.add(placar);
                            placar = new Placar();
                        } else {
                            placar.setStatus(status.text());
                            placar.setPrimeiroTime(containerPrincipal.getElementsByClass("text-right team_link").first().text());
                            placar.setGolsPrimeiroTime(containerPrincipal.getElementsByClass("badge badge-default").first().text());
                            placar.setSegundoTime(containerPrincipal.getElementsByClass("text-left team_link").first().text());
                            placar.setGolsSeguntoTime(containerPrincipal.getElementsByClass("w-25 p-1 match-score d-flex justify-content-start").first().getElementsByClass("badge badge-default").first().text());

                            Element elementNarracao = containerPrincipal.getElementsByClass("w-25 p-1 link_to_match").first();
                            String linkImg = elementNarracao.select("img").first().absUrl("src");
                            if(Objects.nonNull(linkImg) && !linkImg.isEmpty() && linkImg.contains("tv")) {
                                CloneImg.saveImage(linkImg);
                                placar.setLink(linkAcompanharAoVivo);
                            }
                            placars.add(placar);
                            placar = new Placar();
                        }
                    }
                }
            }
        }

        popularTable();
        Object[] o = new Object[6];
        boolean mudarPosicaoHeader = true;
        for (Placar p : placars) {
            o[0] = p.getStatus();
            o[1] = p.getPrimeiroTime();
            o[2] = p.getGolsPrimeiroTime();
            o[3] = p.getSegundoTime();
            o[4] = p.getGolsSeguntoTime();
            o[5] = p.getLink();
            
            defaultTableModel.addRow(o);
            jTable1.setModel(defaultTableModel);

            ColumnColorRenderer columnColorRenderer = new ColumnColorRenderer();
            columnColorRenderer.setHorizontalAlignment(JLabel.CENTER);

            columnStatus = jTable1.getColumnModel().getColumn(0);
            columnStatus.setCellRenderer(new ColumnColorRenderer());

            columnPrimeiroTime = jTable1.getColumnModel().getColumn(1);
            columnPrimeiroTime.setCellRenderer(new ColumnColorRenderer());

            columnSegundoTime = jTable1.getColumnModel().getColumn(3);
            columnSegundoTime.setCellRenderer(new ColumnColorRenderer());

            columnGolsPrimeiroTime = jTable1.getColumnModel().getColumn(2);
            columnGolsSegundoTime = jTable1.getColumnModel().getColumn(4);
            
            
            columnLink = jTable1.getColumnModel().getColumn(5);
            columnLink.setCellRenderer(columnColorRenderer);

            columnGolsPrimeiroTime.setCellRenderer(columnColorRenderer);
            columnGolsSegundoTime.setCellRenderer(columnColorRenderer);
            

            if (mudarPosicaoHeader) {
                columnGolsPrimeiroTime.setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(SwingConstants.CENTER));
                columnGolsSegundoTime.setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(SwingConstants.CENTER));
                columnStatus.setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(SwingConstants.LEFT));
                columnPrimeiroTime.setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(SwingConstants.LEFT));
                columnSegundoTime.setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(SwingConstants.LEFT));
                mudarPosicaoHeader = false;
                this.repaint();
            }
        }
        jTable1.setRowHeight(30);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        lbBuscando.setVisible(false);
        
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        lbBuscando = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Placar - Copa Brasil");
        setType(java.awt.Window.Type.POPUP);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Link de busca:");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbStart)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(101, 101, 101)
                                .addComponent(panelGifStopThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbVinteSegundos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbSessentaSegundos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbCentoVinteSegundos)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayout(new java.awt.FlowLayout());

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Placar Bool");
        jLayeredPane1.add(jLabel1);

        jLayeredPane2.setLayout(new java.awt.BorderLayout());

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setSelectionBackground(new java.awt.Color(1, 31, 79));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLayeredPane2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jComboBox1.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brasileirão Serie A", "Jogos em Andamento", "Jogos de Hoje" }));

        lbBuscando.setBackground(new java.awt.Color(0, 0, 0));
        lbBuscando.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 12)); // NOI18N
        lbBuscando.setForeground(new java.awt.Color(102, 255, 0));
        lbBuscando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBuscando.setText("Buscando...");
        lbBuscando.setOpaque(true);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/placarbool/icons/football.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1232, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbBuscando)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBuscando))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbStartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStartMouseEntered
        lbStart.setBackground(new Color(60, 63, 65));
        lbStart.setOpaque(true);
    }//GEN-LAST:event_lbStartMouseEntered

    private void lbStartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStartMouseExited
        lbStart.setBackground(Color.WHITE);
        lbStart.setOpaque(true);
        //UIManager.put("JLabel.foreground", Color.black);
    }//GEN-LAST:event_lbStartMouseExited

    private void lbStopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStopMouseEntered
        lbStop.setBackground(new Color(60, 63, 65));
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
        String value = jComboBox1.getSelectedItem().toString();
        if (Objects.nonNull(value) && !value.isBlank() && temp > 0) {
            if (lbStart.isEnabled()) {
                startPlacar = new StartPlacar(temp);
                startPlacar.imediato = true;
                if(value.equals("Jogos de Hoje")) {
                    startPlacar.link = "https://www.placardefutebol.com.br/jogos-de-hoje";
                } else if (value.equals("Jogos em Andamento")) {
                    startPlacar.link = "https://www.placardefutebol.com.br/jogos-em-andamento";
                } else if (value.equals("Brasileirão Serie A")) {
                    startPlacar.link = "https://www.placardefutebol.com.br/brasileirao-serie-a";
                }
                startPlacar.running = true;
                startPlacar.start();

                lbStart.setEnabled(false);
                lbStop.setEnabled(true);
                lbBuscando.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor selecione o tempo de busca!");
        }
    }//GEN-LAST:event_lbStartMouseClicked

    private void lbStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStopMouseClicked
        if (lbStop.isEnabled()) {
            lbStart.setEnabled(false);
            startPlacar.running = false;
            panelGifStopThread.setVisible(true);
            lbStop.setToolTipText("Por favor aguarde!");
            lbBuscando.setVisible(false);
        }
    }//GEN-LAST:event_lbStopMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        TableModel tableModel = jTable1.getModel();
        Object link = tableModel.getValueAt(index, 5);
        if(Objects.nonNull(link)) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.placardefutebol.com.br"+link.toString()));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(PlacarBool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
//        int col = jTable1.columnAtPoint(evt.getPoint());
//        jTable1.setCursor(Cursor.getDefaultCursor());
//        if(col == 5) {
//            jTable1.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        }
    }//GEN-LAST:event_jTable1MouseEntered

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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
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
                    lbBuscando.setVisible(true);
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
                lbBuscando.setVisible(false);
                JOptionPane.showMessageDialog(getRootPane(), "Um erro ocorreu!");
                Logger.getLogger(PrincipalUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class ColumnColorRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setFont(new Font("Dejavu Sans Mono", Font.BOLD, 12));
            
            if (column == 5) {
                JLabel lbl = new JLabel();
                lbl.setText("Sem vídeo");
                lbl.setToolTipText("Sem vídeo");
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setForeground(Color.WHITE);
                
                if(Objects.nonNull(value) && !value.toString().isBlank()) {
                    ImageIcon icon = new ImageIcon(new ImageIcon("tv.png","TV").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                    lbl.setText("Link");
                    lbl.setIcon(icon);
                    lbl.setToolTipText("Acompanhar");
                }
                return lbl;
            }

            if (column == 1 || column == 3) {
                cell.setForeground(new Color(245, 246, 247));
            }

            if (column == 2 || column == 4) {
                if (Integer.parseInt(value.toString()) > 0) {
                    cell.setForeground(new Color(116, 232, 49));
                } else {
                    cell.setForeground(Color.RED);
                }
            }
            
            if (table.getValueAt(row, column).toString().contains("MIN")) {
                cell.setForeground(new Color(15, 144, 242));
            } else if (table.getValueAt(row, column).toString().contains("INTERVALO")) {
                cell.setForeground(new Color(232, 186, 49));
            } else if (table.getValueAt(row, column).toString().contains("ENCERRADO")) {
                cell.setForeground(Color.RED);
            } else if (table.getValueAt(row, column).toString().contains("HOJE")) {
                cell.setForeground(Color.CYAN);
            } else if (table.getValueAt(row, column).toString().contains("AMANHÃ")) {
                cell.setForeground(Color.GREEN);
            }

            return cell;
        }
    }

    public class HorizontalAlignmentHeaderRenderer implements TableCellRenderer {

        private int horizontalAlignment = SwingConstants.LEFT;

        public HorizontalAlignmentHeaderRenderer(int horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            TableCellRenderer r = table.getTableHeader().getDefaultRenderer();
            JLabel l = (JLabel) r.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            l.setHorizontalAlignment(horizontalAlignment);
            return l;
        }
    }
}
