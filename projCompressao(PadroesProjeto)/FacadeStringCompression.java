package pct;


public class FacadeStringCompression {

    CompressionFactoryAlgorithm compression;
    DecompressionFactoryAlgorithm decompression;
    ICompressionAlgorithm iCompressionAlgorithm;
    IDecompressionAlgorithm iDecompressionAlgorithm;
    
    public FacadeStringCompression(){
        compression = new CompressionFactoryAlgorithm();
        decompression = new DecompressionFactoryAlgorithm();        
    }
    
    public void print(String str1) {   
        System.out.println("Original String: " + str1);                         
        System.out.println("String Length = " + str1.length());         
        System.out.println("Compressed string: " + compress(str1));
        System.out.println("Decompressed string: " + decompress(compress(str1)));
        System.out.println("Compression Algorithm: " + iCompressionAlgorithm.getClass().getSimpleName());
        System.out.println("Decompression Algorithm: " + iDecompressionAlgorithm.getClass().getSimpleName());
    }

    public String compress(String str) {    
        
        compression = new CompressionFactoryAlgorithm();        
        iCompressionAlgorithm = (str.length() > 20) ? compression.getCompressionAlgorithm(compression.FiveBitMethod) : compression.getCompressionAlgorithm(compression.SimpleMethod);
        return iCompressionAlgorithm.compress(str);
    }

    public String decompress(String str) {
        decompression = new DecompressionFactoryAlgorithm();                
        iDecompressionAlgorithm = (str.length() > 20) ? decompression.getDecompressionAlgorithm(decompression.FiveBitMethod) : decompression.getDecompressionAlgorithm(decompression.SimpleMethod);        
        return iDecompressionAlgorithm.decompress(str);
    }

    public void isEqual(String str1, String str2) {
        
        if (str1.equalsIgnoreCase(str2)) {
            System.out.println("OK");
        } else {
            System.out.println("ERRO");
        }
    } 
}
