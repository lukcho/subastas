package subastas.controller.access;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import com.google.gson.JsonArray;
//
//import subastas.model.dao.entities.Persona;
//import subastas.model.generic.ConsumeREST;
//import subastas.model.manager.ManagerCarga;
//import subastas.model.manager.ManagerGestion;

public class test {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// http://yachay-ws.yachay.gob.ec/data/WSParametrosEntity
//
//		ManagerCarga mg = new ManagerCarga();
//		ManagerGestion mges = new ManagerGestion();
//		try {
////			String a = "aquina";
////			Persona per = mg.funcionarioByDNI(a);
////			System.out.println(per.getPerNombres() + " "
////					+ per.getPerApellidos());
//			
//			Integer item_id = 15;
//			System.out.println(item_id);
//			Integer automatico = mges.ofertaXItem(item_id);
//			System.out.println("no sale nada : "+automatico);
//		//	mges.ganadorItemAutom(item_id, "I",mges.itemByID(item_id),automatico);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int numeroAleatorio = (int)Math.round(Math.random()*1);
		int numeroAleatorio1 = (int)Math.round(Math.random()*0);
		if(numeroAleatorio1 < numeroAleatorio){
		System.out.println("valor real");
		}else {
			System.out.println("valor nada");
		}
//		try {
//			System.out.println(ConsumeREST
//					.consumeGetRestEasyObject("http://yachay-ws.yachay.gob.ec/data/WSParametrosEntity/SRV_IMG_SYS_SUBASTAS")
//					.get("parValor"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
