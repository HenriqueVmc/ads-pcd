package pct;

public class Corrida {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
					
			Corredor felipeMassa = new Corredor("Felipe Massa");
			Corredor lewisHamilton = new Corredor("Lewis Hamilton");
			
			Thread ferrari = new Thread(felipeMassa);
			Thread mercedes = new Thread(lewisHamilton);					
			
			ferrari.start();				
			mercedes.start();																	
			
			ferrari.join();
			mercedes.join();		
			
			felipeMassa.venceu(lewisHamilton);			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

