package WeightedProduct;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WP extends JFrame{
    int alter;
    int kriter;
    double[] atrib;
    double[] bobot;
    double totbobot=0;
    double[][] array;
    double[] S;
    double totS=0;
    double[] V;
    
    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.TRAILING,10,10));
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.TRAILING,10,10));
    JPanel p3 = new JPanel(new FlowLayout(FlowLayout.TRAILING,10,10));
    JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEADING,10,10));
    
    JLabel lAlter = new JLabel("Jumlah Alternatif : ");
    JTextField tfAlter = new JTextField();
    JLabel lKriteria = new JLabel ("Jumlah Kriteria : ");
    JTextField tfKriteria = new JTextField();
    
    JTable tabel1;
    JTable tabel2;
    JButton bOk = new JButton("OK");
    JButton bOk2 = new JButton("OK");
    JButton bOk3 = new JButton("OK");
    
    Dimension l = new Dimension(110,20);
    Dimension tf = new Dimension(60,20);
        
    public WP(){
        setTitle("Weigted Product");
        setSize(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        p1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        p2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        p3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        p4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lAlter.setPreferredSize(l);
        lKriteria.setPreferredSize(l);
        tfAlter.setPreferredSize(tf);
        tfKriteria.setPreferredSize(tf);
        bOk.setPreferredSize(tf);
        p1.setPreferredSize(new Dimension(200,170));
        add(p1);
        pack();
        p1.add(lAlter);
        p1.add(tfAlter);
        p1.add(lKriteria);
        p1.add(tfKriteria);
        p1.add(bOk);
        bOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TabelAB();
            }
        });
    }
    
    public void TabelAB(){
        p2.removeAll();
        p2.setPreferredSize(new Dimension(200,170));
        add(p2);
        pack();
        alter = Integer.parseInt(tfAlter.getText());
        kriter = Integer.parseInt(tfKriteria.getText());
        String[] nama1 = {"Atribut","Bobot"};
        tabel1 = new JTable(new DefaultTableModel(nama1,kriter));
        tabel1.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(tabel1);
        scrollPane.setPreferredSize(new Dimension(180,120));
        p2.add(scrollPane);
        bOk2.setPreferredSize(tf);
        p2.add(bOk2);
        bOk2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TabelAK();
            }
        });
    }
    
    public void TabelAK(){
        p3.removeAll();
        p3.setPreferredSize(new Dimension((80*alter)+40,170));
        add(p3);
        pack();
        atrib = new double[kriter];
        bobot = new double[kriter];
        for(int a=0;a<kriter;a++){
            atrib[a]=Double.parseDouble(tabel1.getValueAt(a,0).toString());
            bobot[a]=Double.parseDouble(tabel1.getValueAt(a,1).toString());
            totbobot=totbobot+bobot[a];
        }
        for (int a=0;a<kriter;a++){
            bobot[a]=bobot[a]/totbobot;
            if(atrib[a]==0){
                bobot[a]=-1*bobot[a];
            }
        }
        String[] nama2 = new String[alter];
        for (int a=0;a<alter;a++){
            nama2[a] = "Alternatif "+(a+1);
        }
        tabel2 = new JTable(new DefaultTableModel(nama2,kriter));
        tabel2.setRowHeight(20);
        JScrollPane scrollPane1 = new JScrollPane(tabel2);
        scrollPane1.setPreferredSize(new Dimension((80*alter)+20,120));
        p3.add(scrollPane1);
        bOk3.setPreferredSize(tf);
        p3.add(bOk3);
        bOk3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Hasil();
            }
        });
    }
    
    public void Hasil(){
        p4.removeAll();
        add(p4);
        p4.setPreferredSize(new Dimension(200,170));
        pack();
        array = new double[alter][kriter];
        S = new double[alter];
        V = new double[alter];
        for(int a=0;a<alter;a++){
            for(int b=0;b<kriter;b++){
                array[a][b]=Double.parseDouble(tabel2.getValueAt(b,a).toString());
                array[a][b]=Math.pow(array[a][b],bobot[b]);
                if (b==0){
                    S[a]=1;
                }
                S[a]=S[a]*array[a][b];
            }
            totS=totS+S[a];
        }
        for (int a=0;a<alter;a++){
            V[a]=S[a]/totS;
            JLabel v = new JLabel("V"+(a+1)+" : "+V[a]);
            p4.add(v);
        }
    }
}
