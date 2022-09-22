
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class NewMain extends JFrame{
    
    PaintPanel paintPanel;
    JPanel controlPanel;
    JButton btnOpen;
    JButton btnMemory;
    JButton btnAnnealing;
    
    Hopfield hopfield;
    
    NewMain(){
        super("Hopfield");
        setSize(200, 200);
        
        hopfield = new Hopfield(40,40);
        
        paintPanel = new PaintPanel();
        btnOpen = new JButton("Open");
        btnMemory = new JButton("Memory");
        btnAnnealing = new JButton("Annealing");
        controlPanel = new JPanel();
        
        btnOpen.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                JFileChooser jfc = new JFileChooser();
                int res = jfc.showOpenDialog(null);
                java.io.File file = null;
                if(res == JFileChooser.APPROVE_OPTION){
                    file = jfc.getSelectedFile();                        
                }else
                    javax.swing.JOptionPane.showMessageDialog(null, "Filenot chosen");
                
                try{
                    paintPanel.readFromFile(file);
                    //double[] h = paintPanel.getHopfieldFormat();
                }catch(java.io.FileNotFoundException ex){
                    javax.swing.JOptionPane.showMessageDialog(null, "FOF");
                }catch(java.io.IOException ex){
                    javax.swing.JOptionPane.showMessageDialog(null, "IO");
                }
                
            }
        });
        
        btnMemory.addActionListener((java.awt.event.ActionEvent e) ->{
            hopfield.setNeurons(paintPanel.getHopfieldFormat());
            hopfield.remember();
        });
        
        btnAnnealing.addActionListener((java.awt.event.ActionEvent e) ->{
            hopfield.setNeurons(paintPanel.getHopfieldFormat());
            hopfield.annealing();
            double[] neurons = hopfield.getNeurons();
            for(int i=0; i<neurons.length; i++){
                if(i % 40 == 0) System.out.println();
                if(neurons[i]==-1){
                    System.out.print("  ");
                }
                else{
                    System.out.print("* ");
                }                
            }
            paintPanel.loadFromHopfield(neurons);
        });
        
        setLayout(new java.awt.BorderLayout());
        
        controlPanel.add(btnOpen);
        controlPanel.add(btnMemory);
        controlPanel.add(btnAnnealing);
        
        add(controlPanel, java.awt.BorderLayout.SOUTH);
        add(paintPanel, java.awt.BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
        

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new NewMain();
            }
        });
    }
    
}
