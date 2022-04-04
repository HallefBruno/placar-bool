package placarbool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author sud
 */
public class PrincipalUI extends javax.swing.JFrame {

  Point location;
  MouseEvent pressed;
  DefaultComboBoxModel modelo;
  DefaultListModel modelList;
  StartPlacar sp;

  public PrincipalUI() {
    modelo = new DefaultComboBoxModel();
    modelList = new DefaultListModel();

    initComponents();
    lbaguarde.setVisible(false);
    setLocationRelativeTo(null);

    resultadoList.setCellRenderer(new SelectedListCellRenderer());

    jComboBox1.setModel(modelo);
    alterarCorCombo();
    add();

    resultadoList.setModel(modelList);

  }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jDialog1 = new javax.swing.JDialog();
        btnexit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultadoList = new javax.swing.JList<>();
        btnstart = new javax.swing.JButton();
        lbaguarde = new javax.swing.JLabel();
        btnstop = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        btnexit.setText("Sair");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Placar Bool");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 0));
        jLabel2.setText("Cole o link aqui");

        jTextField1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tempo de busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30 seg", "60 seg", "02 min" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(428, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        resultadoList.setBackground(new java.awt.Color(0, 0, 0));
        resultadoList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        resultadoList.setForeground(new java.awt.Color(0, 204, 51));
        resultadoList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(resultadoList);

        btnstart.setText("start");
        btnstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstartActionPerformed(evt);
            }
        });

        lbaguarde.setForeground(new java.awt.Color(0, 153, 153));
        lbaguarde.setText("Aguarde...");

        btnstop.setText("stop");
        btnstop.setEnabled(false);
        btnstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnexit))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addComponent(jLabel1)))
                        .addGap(0, 328, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(267, 267, 267)
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnstart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnstop)
                .addGap(65, 65, 65)
                .addComponent(lbaguarde, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnstart)
                    .addComponent(lbaguarde)
                    .addComponent(btnstop))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnexit)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
      Object[] options = {"Não", "Sim"};
      int n = JOptionPane.showOptionDialog(null, "Sair?", "Placar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
      if (n == 1) {
        System.exit(0);
      }
    }//GEN-LAST:event_btnexitActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
      Component component = evt.getComponent();
      location = component.getLocation(location);
      int x = location.x - pressed.getX() + evt.getX();
      int y = location.y - pressed.getY() + evt.getY();
      component.setLocation(x, y);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
      pressed = evt;
    }//GEN-LAST:event_formMousePressed

    private void btnstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstartActionPerformed
      if (jTextField1.getText() != null && !jTextField1.getText().isEmpty()) {
        long temp = Long.parseLong(modelo.getElementAt(jComboBox1.getSelectedIndex()).toString().substring(0, 1));
        sp = new StartPlacar(temp, true, jTextField1.getText());
        sp.start();
        btnstart.setEnabled(false);
        btnstop.setEnabled(true);
        lbaguarde.setVisible(true);
      } else {
        JOptionPane.showMessageDialog(null, "Por favor insira o link!");
        jTextField1.setFocusable(true);
        lbaguarde.setVisible(false);
      }
    }//GEN-LAST:event_btnstartActionPerformed

    private void btnstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstopActionPerformed
      sp.running = false;
      btnstart.setEnabled(true);
      lbaguarde.setVisible(false);
      modelList.clear();
    }//GEN-LAST:event_btnstopActionPerformed

  public static void main(String args[]) {

    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(PrincipalUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(() -> {
      new PrincipalUI().setVisible(true);
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnstart;
    private javax.swing.JButton btnstop;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbaguarde;
    private javax.swing.JList<String> resultadoList;
    // End of variables declaration//GEN-END:variables

  private void alterarCorCombo() {
    jComboBox1.setRenderer(new Colorir());
  }

  private void add() {
    String[] tempo = {"30 seg  -- não indicado", "60 seg  -- razoável", "120 seg -- indicado"};
    for (String s : tempo) {
      modelo.addElement(s);
    }
  }

  private class Colorir extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      setText(value.toString());

      if (value.toString().contains("30 seg")) {
        setBackground(Color.RED);
        jComboBox1.setBackground(Color.RED);
      } else if (value.toString().contains("60 seg")) {
        setBackground(Color.ORANGE);
        jComboBox1.setBackground(Color.ORANGE);
      } else {
        setBackground(Color.GREEN);
        jComboBox1.setBackground(Color.GREEN);
      }

      if (isSelected) {
        //setBackground(Color.WHITE);
      }

      return this;
    }
  }

  public class StartPlacar extends Thread {

    private long temp;
    private boolean running;
    private String link;

    public StartPlacar(long temp, boolean running, String link) {
      this.temp = temp;
      if (this.temp == 02) {
        this.temp = 2600;
      } else if (this.temp == 30) {
        this.temp = 300;
      } else {
        this.temp = 1000;
      }
      this.running = running;
      this.link = link;
    }

    public StartPlacar(boolean running) {
      this.running = running;
    }

    @Override
    public void run() {
      try {
        while (this.running) {
          Thread.sleep(this.temp);
          readPageHtml(this.link);
        }
      } catch (IOException | InterruptedException ex) {
        btnstart.setEnabled(true);
        btnstop.setEnabled(false);
        this.running = false;
        JOptionPane.showMessageDialog(null, "Link inválido!");
        Logger.getLogger(PrincipalUI.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  public void readPageHtml(String link) throws IOException {
    //String webPage = "https://www.google.com/search?client=ubuntu&hs=c4k&channel=fs&ei=abvgX_36PKyy5OUPqfyDqAM&q=goi%C3%A1s+x+s%C3%A3o+paulo&oq=goias+x+sao&gs_lcp=CgZwc3ktYWIQARgAMgQIABBHMgQIABBHMgQIABBHMgQIABBHMgQIABBHMgQIABBHMgQIABBHMgQIABBHUABYAGDspgNoAHACeACAAQCIAQCSAQCYAQCqAQdnd3Mtd2l6yAEIwAEB&sclient=psy-ab";
    //https://www.google.com.br/search?source=hp&ei=MFviX63NDp3Y5OUPnIOGgAk&q=goi%C3%A1s+x+corinthians&oq=GOIASX&gs_lcp=CgZwc3ktYWIQAxgAMgoIABCxAxCDARAKMgQILhAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgcIABCxAxAKOggIABCxAxCDAToICC4QsQMQgwE6BQgAELEDOgIIADoLCAAQsQMQxwEQowI6AgguOgsILhCxAxCDARCTAjoFCC4QsQM6CwgAELEDEMcBEK8BOggIABDHARCvAVDuD1jOGWCoKWgAcAB4AIABvwGIAcAGkgEDMC42mAEAoAEBqgEHZ3dzLXdpeg&sclient=psy-ab
    Document html = Jsoup.connect(link).get();
    Elements div = html.getElementsByClass("imso-ani imso_mh__tas");

    if (!div.isEmpty()) {
      div = html.getElementsByClass("imso-ani imso_mh__tas");
      Elements gol = html.getElementsByClass("imso_gs__gs-cont imso-medium-font imso_gs__gs-cont-ed");
      String times = "Times ####" + div.text() + "####";
      String gols = "Gols >>>>" + gol.text() + "<<<<";
      modelList.addElement(times);
      modelList.addElement(gols);
    } else {
      Elements table = html.getElementsByClass("ml-bs-u liveresults-sports-immersive__match-grid");
      Elements rows = table.select("tr");
      for (int i = 1; i < rows.size(); i++) {
        Element row = rows.get(i);
        Elements cell = row.select("td");
        Elements span = cell.select("span");
        System.out.println(span.select("img").attr("alt"));
        modelList.addElement(cell.text());
      }
    }

  }

  public class SelectedListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      if (isSelected) {
        c.setBackground(Color.RED);
        c.setForeground(Color.WHITE);
      }
      return c;
    }
  }

}
