package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Gerente;

@Named
@ViewScoped
public class GerentesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GerenteDao gerenteDao = new GerenteDao();
	private List<Gerente> gerentes;
	private Gerente gerente = new Gerente();

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public List<Gerente> getGerentes() {
		System.out.println("Listando os gerentes");

		if (this.gerentes == null) {
			this.gerentes = gerenteDao.lista();
		}
		return gerentes;
	}

	public void grava() {
		System.out.println("Gravando o gerente");

		if (this.gerente.getId() == null) {
			gerenteDao.adiciona(gerente);
		} else {
			gerenteDao.altera(gerente);
		}
		limpaFormularioDoJSF();
	}

	public void remove() {
		System.out.println("Removendo o gerente");

		gerenteDao.remove(this.gerente);
		limpaFormularioDoJSF();
	}

	private void limpaFormularioDoJSF() {
		this.gerente = new Gerente();
	}

}
