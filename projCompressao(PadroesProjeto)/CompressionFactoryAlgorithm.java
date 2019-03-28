package pct;

public class CompressionFactoryAlgorithm implements ICompressionFactory{   
    //ICompressionAlgorithm iCompressionAlgorithm;
    
    @Override        
    public ICompressionAlgorithm getCompressionAlgorithm(int method){         
        
        switch(method){
            case FiveBitMethod : return new FiveBitCompression();
            case SimpleMethod : return new SimpleMethodCompression();  
            //default: return null;
        }

        return null;
    }
}
