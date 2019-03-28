package pct;

import static pct.ICompressionFactory.FiveBitMethod;

public class DecompressionFactoryAlgorithm implements IDecompressionFactory{
    
    //private IDecompressionAlgorithm iDecompressionAlgorithm;
    
    @Override
    public IDecompressionAlgorithm getDecompressionAlgorithm(int method){
        
        switch(method){
            case FiveBitMethod : return new FiveBitDecompression();
            case SimpleMethod : return new SimpleMethodDecompression();
        }
                
        return null;
    }
}
