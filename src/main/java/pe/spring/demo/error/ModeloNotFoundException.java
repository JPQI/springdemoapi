package pe.spring.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Metodo no encontrado")
public class ModeloNotFoundException extends RuntimeException{

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
}
