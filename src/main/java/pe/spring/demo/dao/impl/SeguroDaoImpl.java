package pe.spring.demo.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.spring.demo.dao.SeguroDao;
import pe.spring.demo.entities.Seguro;

@Repository
public class SeguroDaoImpl extends JdbcDaoSupport implements SeguroDao{
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void insertSeguro(Seguro seguro) {
		
		String sql = "INSERT INTO seguro " + "(emp_id, aseguradora, cobertura) VALUES (?, ?, ?)";
		getJdbcTemplate().update(sql, new Object[] { seguro.getEmpId(), seguro.getAseguradora(), seguro.getCobertura() });
		
	}

}
