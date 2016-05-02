package subastas.controller.access;

import subastas.model.dao.entities.Persona;
import subastas.model.manager.ManagerCarga;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerCarga mg = new ManagerCarga();
		try {
			String a = "aquina";
			Persona per = mg.funcionarioByDNI(a);
			System.out.println(per.getPerNombres()+" "+per.getPerApellidos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
