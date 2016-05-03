package subastas.controller.gestion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import subastas.entidades.help.UsuarioHelp;
import subastas.entidades.help.Utilidades;
import subastas.model.dao.entities.SubPostulante;
import subastas.model.generic.Mail;
import subastas.model.manager.ManagerGestion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean
public class SessionBean {
	private UsuarioHelp session;
    //log
    private String Cedula;
    private String pass;
    
    ManagerGestion managest;
    //devolver contraseña
    private String correocontra;
    String smscor="";

    //mostrar
    private String nom;
    
    private SubPostulante usr;
    
    /*Perfil de Usuario*/
    private String nombre, apellido, password, correo, cedula,alias; 
    
    public SessionBean() {
    	managest = new ManagerGestion();
		usr=new SubPostulante();
	}
    
    public String getNick() {
		return Cedula;
	}
    
    public String getPass() {
		return pass;
	}
    
    public UsuarioHelp getSession() {
		return session;
	}
    
  	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNick(String nick) {
		this.Cedula = nick;
	}
    
    public void setPass(String pass) {
		this.pass = pass;
	}
    
    /*Perfil Usuario*/
    public String getApellido() {
		return apellido;
	}
    
    public void setApellido(String apellido) {
		this.apellido = apellido;
	}
    
    public String getNombre() {
		return nombre;
	}
    
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    public String getCorreo() {
		return correo;
	}
    
    public void setCorreo(String correo) {
		this.correo = correo;
	}
    
    public String getPassword() {
		return password;
	}
    
    public void setPassword(String password) {
		this.password = password;
	}
    
    public String getCedula() {
		return cedula;
	}
    
    public void setCedula(String cedula) {
		this.cedula = cedula;
	}
    /**
	 * @return the correocontra
	 */
	public String getCorreocontra() {
		return correocontra;
	}

	/**
	 * @param correocontra the correocontra to set
	 */
	public void setCorreocontra(String correocontra) {
		this.correocontra = correocontra;
	}
 /**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	// login
	public void veri(){
 		System.out.println(Cedula);
 		int t=0;
 		List<SubPostulante> a = managest.findAllpostulantes();
 		for (SubPostulante u : a) {
 			if ((u.getPosId().equals(Cedula) || u.getPosCorreo().equals(Cedula))){
 				t=100;
 			}
 		}
 		if (t!=100){
 		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Alias Inexistente o Usuario Desactivado", null));
 		}
 	}	
 	
 	//metodo para ingresar al sistema	
 	public String login(){
 		String r="";
 		Integer t=0;
 		List<SubPostulante> u = managest.findAllpostulantes();
 		try {
 		for (SubPostulante y :u){
 			System.out.println("avr "+Utilidades.Encriptar(pass).toString());
 			if (y.getPosId().equals(Cedula) && y.getPosPassword().equals(Utilidades.Encriptar(pass))){
 				session = new UsuarioHelp(y.getPosId(), y.getPosApellido(),y.getPosCorreo() ,y.getPosNombre());
 				nom=y.getPosNombre()+" "+y.getPosApellido();
 				usr=y;
 				r="home?faces-redirect=true";
 				t=1;
 			}
 			else if (y.getPosCorreo().equals(Cedula) && y.getPosPassword().equals(Utilidades.Encriptar(pass))){
 				session = new UsuarioHelp(y.getPosId(), y.getPosApellido(),y.getPosCorreo() ,y.getPosNombre());
 				nom=y.getPosNombre()+" "+y.getPosApellido();
 				usr=y;
 				r="home?faces-redirect=true";
 				t=1;
 			}
 		}
 		if (t==0){ 			
 			FacesContext context = FacesContext.getCurrentInstance();
 			context.addMessage(null, new FacesMessage("Error..!!!",
 					"Usuario o Contrasena Incorrecta "));
 		}
 	}
		catch (Exception e)
		{
		e.printStackTrace();
		}
 		return r;
 	}

 	
 	//metodo para salir de el sistema
 	public String logout(){
 		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         session.invalidate();
 		nom="";
 		correo="";
 		pass="";
 		Cedula="";
 		System.out.println("si salio");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salió",null));
 		return "";
 	}
 	
 	 /**
      * Método para verifiar la existencia de la sesión
      * @param rol de usuario
      * @return Clase Usuario
      */
     public static UsuarioHelp verificarSession(){
     	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                 .getExternalContext().getSession(false);
         SessionBean user = (SessionBean) session.getAttribute("sessionBean");
         if (user==null || user.getSession() == null) {
             try {
                 FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
             } catch (IOException ex) {
             	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(),null));
             }
             return null;
         } else {
                 return user.getSession();
             } 
         }
     
    public String cargarDatosPerfil(){
    	String pag ="";
    	if(session != null){
    		try {
    			SubPostulante usr = managest.postulanteByID(session.getIdUsr());
    			setApellido(usr.getPosApellido());
    			setNombre(usr.getPosNombre());
    			setCorreo(usr.getPosCorreo());
    			setCedula(usr.getPosId());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al  cargar sus datos personales",null));
			}
    	}
    	return pag;
    }
    
        
    public String regresarHomeUser(){
    	return "home?faces-redirect=true";
    }
    
    
    //metodo para enviar el correo
    public String devolvercontra(){
 		String r="";
 		Integer t=0;
 		List<SubPostulante> u = managest.findAllpostulantes();
 		for (SubPostulante y :u){
 			if (y.getPosCorreo().equals(correocontra)){
 				System.out.println("si entra1");
 				enviarmensajerecuperarcontra(y); 				
 				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Enviado correctamente a su correo", null));	  						  							

 				t=1;
 			}
 		}
 		if (t==0){ 			
 			FacesContext context = FacesContext.getCurrentInstance();
 			context.addMessage(null, new FacesMessage("Error..!!!","Su correo no existe o es incorrecto."));
 		} 		
 		return r;
 	}
    String correoveri="";
    //Tomar el id de estado general id_estadoSolicitud
		public String enviarmensajerecuperarcontra(SubPostulante usr){
		try {
			if(!correocontra.equals(correoveri))
			{
					String passwordnuevo;
					cedula = usr.getPosId();
					nombre = usr.getPosNombre();
					apellido = usr.getPosApellido();
					correo = usr.getPosCorreo();
					password = usr.getPosPassword();
					passwordnuevo=Utilidades.Desencriptar(password);	
					
					smscor = "Sr/ra. "+nombre+" "+apellido+",sus datos son los siguientes: "
				             + "<br/> C&eacute;dula: "+cedula+""
				             + "<br/> Nombre: "+nombre+""
				             + "<br/> Apellido: "+apellido+""
				             + "<br/> Correo: "+correo+""				             
							 + "<br/> para ingresar su usuario es: "+cedula+" o su correo "+correo+", y su contrase&ntildea es: "+passwordnuevo+"";
	
					Mail.generateAndSendEmail(correo, "Recuperación de contraseña Subastas", smscor);
				    //limpiamos los datos
					cedula="";
			        nombre="";
			        apellido="";
			        correoveri=correo;
					correo="";
					password="";	
					passwordnuevo="";
					smscor="";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Enviado correctamente al correo su contraseña", null));
			}
			else if(correoveri.equals(correocontra))
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Ya se ha enviado al correo su contraseña", null));				
			}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error al enviar correo", null));
			}
    		return "index?faces-redirect=true";
		}			    
}
