package com.airhacks;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class TimerBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4311308990111071868L;
	
	private Integer number;
	private Timestamp fhfinal;
	private String tiempo;
	private Integer valorTiempo;
	
	public TimerBean() {
		number = 0;
		tiempo = "00 : 00 : 00";
		cargarFecha();
		miTiempo();
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Timestamp getFhfinal() {
		return fhfinal;
	}
	
	public void setFhfinal(Timestamp fhfinal) {
		this.fhfinal = fhfinal;
	}
	
	public String getTiempo() {
		return tiempo;
	}
	
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	public Integer getValorTiempo() {
		return valorTiempo;
	}
	
	public void setValorTiempo(Integer valorTiempo) {
		this.valorTiempo = valorTiempo;
	}
	
	public void pollMethod(){
		incrementar();
		miTiempo();
	} 
	
	private void incrementar(){
		setNumber(getNumber()+1);
	}
		
	private void cargarFecha() {
		Calendar miCalendario = Calendar.getInstance();
		miCalendario.set(2016,4,22,11,8,30);//miCalendario.set(2016,4,14,22,0,0);
		setFhfinal(new Timestamp(miCalendario.getTime().getTime()));	
	}
	
	private void miTiempo(){
		setValorTiempo(this.tiempoRestante(getFhfinal()));
		setTiempo(this.tiempoRestanteHMS(getValorTiempo()));
		if(getValorTiempo()<=0)
			System.out.println("------------------> PROCESO SIGUIENTE");
	}
	
	public Integer tiempoRestante(Timestamp fFin){
		return ((Long) ((fFin.getTime() - (new Timestamp(new Date().getTime())).getTime())/1000)).intValue();
	}
	
	public String tiempoRestanteHMS(Integer segundos){
		int hor=segundos/3600;
        int min=(segundos-(3600*hor))/60;
        int seg=segundos-((hor*3600)+(min*60));
        System.out.println("--------------->TIEMPO "+String.format("%02d",hor)+" : "+String.format("%02d",min)+" : "+String.format("%02d",seg));
		return String.format("%02d",hor)+" : "+String.format("%02d",min)+" : "+String.format("%02d",seg);
	}

}
