package pe.spring.demo.cross;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import pe.spring.demo.entities.Empleado;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/appconfig-root.xml"})
@WebAppConfiguration
@Transactional
@Rollback
public class EmpleadoAdvanceRestControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() throws Exception {
		
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
    public void testListarPorId() throws Exception {
        mockMvc.perform(get("/empleados").param("id", "1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8")) 
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].departamento").exists())
            .andExpect(jsonPath("$[0].telefono").exists())
        ;
    }
	
	@Test
    public void testListarAll() throws Exception {
        mockMvc.perform(get("/empleados"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8")) 
            .andExpect(jsonPath("$.length()", is(6)))
        ;
    }
	
	@Test
    public void testRegistrarEmpleado_Ok() throws Exception {
		Empleado empl = new Empleado();
		empl.setNombre("nombre no nulo");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(empl);
		
		mockMvc.perform(post("/empleados").contentType("application/json;charset=UTF-8")
		        .content(requestJson))
		        .andExpect(status().isCreated());
	}
	
	@Test
    public void testRegistrarEmpleado_NotOk() throws Exception {
		Empleado empl = new Empleado();
		empl.setNombre("");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(empl);
		
		mockMvc.perform(post("/empleados").contentType("application/json;charset=UTF-8")
		        .content(requestJson))
		        .andExpect(status().isBadRequest());
	}
	
	@Test
    public void testEliminarEmpleado_NotFound() throws Exception {
		mockMvc.perform(delete("/empleados/999"))
	        .andExpect(status().isNotFound())
		;
	}
	
	@Test
    public void testEliminarEmpleado_Ok() throws Exception {
		mockMvc.perform(delete("/empleados/1"))
	        .andExpect(status().isOk())
		;
	}
}
