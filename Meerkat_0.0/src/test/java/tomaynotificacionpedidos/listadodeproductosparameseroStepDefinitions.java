package tomaynotificacionpedidos;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class listadodeproductosparameseroStepDefinitions {
	
	Mesero mesero = null;
	
	@Given("^El mesero va a consultar los productos.$")
	public void El_mesero_va_a_consultar_los_productos() throws Throwable { 
		mesero = new Mesero();
	}

	@When("^No hay productos.$")
	public void No_hay_productos() throws Throwable {
	    mesero.consultarproductos();
	}

	@Then("^Notificar que no hay productos para vender.$")
	public void Notificar_que_no_hay_productos_para_vender() throws Throwable {
	    Assert.assertEquals("No hay productos", mesero.getMensaje());
	}

}
