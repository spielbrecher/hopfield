public class PaintPanel extends javax.swing.JPanel{
    java.awt.image.BufferedImage image = null;
    
    PaintPanel(){
        
    }
    
    public void setPicture(java.awt.image.BufferedImage image){
        this.image = image;
    }
    
    @Override
    public void paint(java.awt.Graphics g){
        super.paint(g);
        if(image != null)
            g.drawImage(image, 
                    this.getWidth()/2-image.getWidth()/2, 
                    this.getHeight()/2-image.getHeight()/2, this);
    }
    
    public void readFromFile(java.io.File file) 
            throws java.io.FileNotFoundException, java.io.IOException{
         
        image = javax.imageio.ImageIO.read(file);
        setPicture(image);    
        repaint();
                
    }
    
    public void loadFromHopfield(double[] neurons){
        int x=0;
        int y=0;
        for(int i=0; i<neurons.length; i++){
            System.out.println(x+" "+y);
            if(neurons[i]==-1)
                image.setRGB(x, y, new java.awt.Color(255,255,255).getRGB());
            else
                image.setRGB(x, y, new java.awt.Color(0,0,0).getRGB());
            x++;
            if(x == 40){
                y++;
                x=0;
            }
            
        }
        
        repaint();
    }
    
    public double[] getHopfieldFormat(){
        double[] result = new double[image.getWidth()*image.getHeight()];
        int i=0;
        for(int x=0; x<image.getWidth(); x++){
            System.out.println();
            for(int y=0; y<image.getHeight(); y++){
                java.awt.Color color = new java.awt.Color(image.getRGB(y, x));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int grey = (int) (0.3*red + 0.6*green + 0.1*blue);
                if(grey==255){
                    result[i] = -1;
                    System.out.print("  ");
                }
                else{
                    result[i] = 1;
                    System.out.print("* ");
                }
                
                i++;
            }
        }
        return result;
        
    }
        
    
}
