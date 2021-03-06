package br.com.os.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.os.domain.ContasPagar;
import br.com.os.domain.Fornecedor;
import br.com.os.domain.Funcionario;
import br.com.os.domain.Produto;
import br.com.os.util.HibernateUtil;

public class ContasPagarDAO extends GenericDAO<ContasPagar> {
	
	
	@SuppressWarnings("unchecked")
	public List<ContasPagar> buscarPorContasPagar(long fornecedorCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.eq("fornecedor.codigo", fornecedorCodigo));
			// restrictions.eq compara chave estrangeira
			consulta.addOrder(Order.asc("nome"));
			List<ContasPagar> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listarOrdenado() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			consulta.createAlias("forncecedor", "f");
			//alias consulta classe pessoa e captura o nome funcionario
			consulta.addOrder(Order.asc("e.nome"));
			List<Fornecedor> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
