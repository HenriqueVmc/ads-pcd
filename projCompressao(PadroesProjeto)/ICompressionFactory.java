package pct;

public interface ICompressionFactory {
    
    public static int SimpleMethod = 1;
    public static int FiveBitMethod = 2;

    ICompressionAlgorithm getCompressionAlgorithm(int Method);
}
