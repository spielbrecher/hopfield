public class Hopfield {
    private double[] neurons;
    private double[][] synapses;
    private double[] nodes;
    
   
    Hopfield(int width, int height){
        int len = width * height;
        neurons = new double[len];
        nodes = new double[len];
        synapses = new double[len][len];
        
        for(int i=0; i<synapses.length; i++)
            for(int j=0; j<synapses[i].length; j++)
                synapses[i][j] = 0;
    }
    
    public void setNeurons(double[] neurons){
        System.arraycopy(neurons, 0, this.neurons, 0, neurons.length);
    }
    
    public double[] getNeurons(){
        return neurons;
    }
        
    
    public void remember(){
        //System.arraycopy(image, 0, neurons, 0, image.length);
        for(int i=0; i<neurons.length; i++)
            for(int j=0; j<neurons.length; j++){
                if(i==j) continue;
                synapses[i][j] += neurons[i] * neurons[j];
            }                
    }
    
    public void countOut(){
        for(int i=0; i<neurons.length; i++){
            neurons[i] = 0;
            for(int j=0; j<nodes.length; j++)
                neurons[i] += nodes[j] * synapses[j][i];
            neurons[i] = neurons[i]>0? 1: -1;
        }
    }
    
    public void annealing(){
        boolean ok = false;
        do{
            System.arraycopy(neurons, 0, nodes, 0, neurons.length);
            countOut();
            // nodes == neurons
            ok = true;
            for(int i=0; i<nodes.length; i++){
                if(neurons[i] != nodes[i]){
                    ok = false;
                    break;
                }
            }
        }while(!ok);
        
        
    }
            
}
