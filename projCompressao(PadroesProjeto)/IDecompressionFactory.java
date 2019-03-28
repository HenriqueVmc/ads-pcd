package pct;

public interface IDecompressionFactory {
    public static int SimpleMethod = 1;
    public static int FiveBitMethod = 2;
    
    IDecompressionAlgorithm getDecompressionAlgorithm(int method);
}
