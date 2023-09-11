package controller;
import java.io.*;

public class ThreadLinux extends Thread {
	private String site;
	private static int [] mediaPing=new int [3];
	private int media;
	private String []ping=new String [3];
	private int i;
	
	public ThreadLinux(int i) {
		this.i=i;
		switch(i) {
			case 1: site="www.uol.com.br";break;
			case 2: site="www.terra.com.br";break;
			case 3: site="www.google.com.br";break;
		}
	}
	
	public void run() {
		int i=0;
		ping();
		mediaPing[i]=media;
		i++;
		if(i==2) {
			for(i=0;i<=2;i++) {
				switch(i) {
				case 1: System.out.println("UOL: tempo medio - "+mediaPing[i]+"ms");break;
				case 2: System.out.println("Terra: tempo medio - "+mediaPing[i]+"ms");break;
				case 3: System.out.println("Google: tempo medio - "+mediaPing[i]+"ms");break;
			}
			}
		}
		
	}
	
	private void ping() {
		String leitor;
		String comando="ping -4 -n 10 ";
		double tempoInicial;
		double tempoFinal;
		media=0;
		try {
			Process processos=Runtime.getRuntime().exec(comando+site);
			BufferedReader input=new BufferedReader(new InputStreamReader(processos.getInputStream()));
			while((leitor=input.readLine())!=null) {
				tempoInicial=System.nanoTime();
				ping=leitor.split("time=");
				if(ping.length==2) {
					tempoFinal=System.nanoTime();
					switch(i) {
					case 1: System.out.println("UOL: "+ping[1]+". Tempo de interacao: "+(tempoFinal-tempoInicial));break;
					case 2: System.out.println("Terra: "+ping[1]+". Tempo de interacao: "+(tempoFinal-tempoInicial));break;
					case 3: System.out.println("Google: "+ping[1]+". Tempo de interacao: "+(tempoFinal-tempoInicial));break;
					}
					ping=ping[1].split("ms");
					media=Integer.parseInt(ping[0])+media;
				}
			}
			input.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		media=media/10;
	}
}
