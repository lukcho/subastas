package subastas.controller.gestion;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import subastas.general.connection.SingletonJDBC;

public class test {

	public static SingletonJDBC a;
	public static Integer b;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Calendar miCalendario = Calendar.getInstance();
			miCalendario.set(2016,4,12,11,8,0);
			Timestamp fhfinal = new Timestamp(miCalendario.getTime().getTime());
			int num = tiempoRestante(fhfinal);
			System.out.println(num);
			int hor=num/3600;
	        int min=(num-(3600*hor))/60;
	        int seg=num-((hor*3600)+(min*60));
	        System.out.println(String.format("%02d",hor)+" : "+String.format("%02d",min)+" : "+String.format("%02d",seg));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Integer tiempoRestante(Timestamp fFin){
		return ((Long) ((fFin.getTime() - (new Timestamp(new Date().getTime())).getTime())/1000)).intValue();
	}

}
