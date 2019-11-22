package pe.spring.demo.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.spring.demo.dao.SeguroDao;
import pe.spring.demo.entities.Empleado;
import pe.spring.demo.entities.Seguro;
import pe.spring.demo.repo.IEmpleadoRepo;
import pe.spring.demo.services.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private IEmpleadoRepo repo;
	
	@Autowired
	private SeguroDao seguroDao;
	
	@Transactional
	public Empleado guardarEmpleado(Empleado obj) {
		
		Empleado empleado = null;
		if(obj.getId() == null) {
			empleado = this.darAltaEmpleado(obj);
		}
		else {
			empleado = this.modificar(obj);
		}
		
		return empleado;
	}
	
	private Empleado darAltaEmpleado(Empleado obj) {
		
		Empleado empleado = this.registrar(obj);
		
		Seguro seguro = new Seguro();
		seguro.setEmpId(empleado.getId());
		seguro.setAseguradora("RIMAC");
		seguro.setCobertura(new BigDecimal("ss"));
		//seguro.setCobertura(new BigDecimal(20000));
		
		seguroDao.insertSeguro(seguro);
		
		return empleado;
	}

	@Override
	public Empleado registrar(Empleado obj) {
		return repo.save(obj);
	}

	@Override
	public Empleado modificar(Empleado obj) {
		return repo.save(obj);
	}

	@Override
	public Empleado listarPorId(Integer id) {
		Optional<Empleado> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Empleado();
	}

	@Override
	public List<Empleado> listar() {
		return (List<Empleado>) repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

}
