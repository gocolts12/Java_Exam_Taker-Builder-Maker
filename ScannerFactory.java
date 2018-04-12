


import java.util.Scanner;

public class ScannerFactory {
	
	
	//Global Varaible
	private static Scanner keyboardScanner;
	
	public static Scanner getKeyboardScanner() {
		
		if (keyboardScanner == null) {
			keyboardScanner = new Scanner(System.in);
		}
		return keyboardScanner;
	}
	
}
