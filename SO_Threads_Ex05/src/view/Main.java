package view;
import controller.ThreadLinux;

public class Main {
	public static void main(String[] args) {
		for(int i=1;i<=3;i++) {
			Thread threadLinux= new ThreadLinux(i);
			threadLinux.start();
		}
	}
	
}
