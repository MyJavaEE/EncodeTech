package br.com.encodetech.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.encodetech.dao.complementos.OportunidadeDAO;
import br.com.encodetech.dao.empresas.EmpresaDAO;
import br.com.encodetech.dao.localizacao.CidadeDAO;
import br.com.encodetech.dao.localizacao.EstadoDAO;
import br.com.encodetech.domain.complementos.Oportunidade;
import br.com.encodetech.domain.empresas.Empresa;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Estado;

@ManagedBean
@SessionScoped
public class OportunidadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Oportunidade oportunidade;
	private OportunidadeDAO dao;
	private List<Oportunidade> listaOportunidade;
	private String filtrarCargo = "";
	private int totasVagas;

	private Estado estado;
	private EstadoDAO estadoDAO;
	private List<Estado> listaEstado;
	private String auxEstado = " Selecione um Estado";
	private Long filtrarEstado;

	
	private CidadeDAO cidadeDAO;
	private String auxCidade = "Selecione uma Cidade";
	private List<Cidade> listaCidade;
	private Long comboCidade;

	private EmpresaDAO empresaDAO;
	private List<Empresa> listaEmpresas;

	private Boolean botaoEditar = false;
	private Boolean botaoSalvar = false;

	
	private int filtrarSalario = 0;
	private String filtrarNivel="";
	private String filtrarModalidade="";
	private int		FiltrarPcD =3;
	
	
	
	

	// Salvar usuário
	// -------------------------------------------------------------------------------------
	public void salvar() {

		try {

			System.out.println("Método salvar");
			
			if (!oportunidade.getMostrarSalario()) {
				oportunidade.setSalarioAux(new BigDecimal(1.00));
			} else {
				oportunidade.setSalarioAux(oportunidade.getSalario().setScale(2));
			}

			oportunidade.setDataCadastro(new Date());
			oportunidade.setEstado(estado);
			dao.salvar(oportunidade);
			Messages.addGlobalInfo("Salvo com sucesso.");

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o usuário, tente novamente mais tarde ... ");
		} finally {
			System.out.println("finally: " + oportunidade.toString());

			fechar();

		}
	}

	// Novo
	// -------------------------------------------------------------------------------------------

	public void novo() {

		System.out.println("Método novo");

		botaoEditar = false;
		botaoSalvar = true;
		listarInfos();
		oportunidade = new Oportunidade();
		dao = new OportunidadeDAO();

	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		System.out.println("Método fechar");
		RequestContext.getCurrentInstance().reset("dialogform");
		oportunidade = new Oportunidade();
		dao = new OportunidadeDAO();
		auxCidade = "Selecione uma Cidade";
        auxEstado = " Selecione um Estado";
	}

	
	// CarregarFiltrando
	// -------------------------------------------------------------------------------------------

	public void carregarFiltrando() {
		
		
		
		

		try {
			dao = new OportunidadeDAO();
			
			
			listaOportunidade = dao.buscarVagas(filtrarCargo, filtrarEstado,comboCidade, filtrarSalario, filtrarNivel, filtrarModalidade, FiltrarPcD );
			totasVagas = listaOportunidade.size();
			Messages.addGlobalInfo("Lista atualizada com sucesso ");

			} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} finally {

		}

	}


	// Excluir usuário
	// -------------------------------------------------------------------------------------------
	public void excluir(ActionEvent evento) {

		try {

			oportunidade = (Oportunidade) evento.getComponent().getAttributes().get("meuSelect");
			OportunidadeDAO dao = new OportunidadeDAO();
			Messages.addGlobalInfo("Removido com sucesso!!!");
			dao.excluir(oportunidade);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: ");

		} finally {
			fechar();
		}

	}

	// Editar usuário
	// -------------------------------------------------------------------------------------------
	public void editar() {

		try {			

			if (!oportunidade.getMostrarSalario()) {
				oportunidade.setSalarioAux(new BigDecimal(1.00));

			} else {
				oportunidade.setSalarioAux(oportunidade.getSalario().setScale(2));
			}
			dao = new OportunidadeDAO();
			oportunidade.setEstado(estado);
			dao.merge(oportunidade);
			Messages.addGlobalInfo(" Editado com sucesso!!!");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar ");

		} finally {
			fechar();
		}

	}

	// Instanciar
	// -------------------------------------------------------------------------------------------

	public void getinstancia(ActionEvent evento) {

		try {

			botaoSalvar = false;
			botaoEditar = true;
			listarInfos();
			
			oportunidade = (Oportunidade) evento.getComponent().getAttributes().get("meuSelect");
			auxEstado = oportunidade.getEstado().getNome();
			auxCidade = oportunidade.getCidade().getNome();
			
			Messages.addGlobalInfo("Vaga de ID:  " + oportunidade.getCodigo());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: ");

		}

	}

	// Lista as empresas
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void listarInfos() {

		try {

			empresaDAO = new EmpresaDAO();
			listaEmpresas = empresaDAO.listar();

			estadoDAO = new EstadoDAO();
			listaEstado = estadoDAO.listar("nome");
			//listaCidade = cidadeDAO.listar("nome");

			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

	

	// Listar Estado
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	@PostConstruct
	public void BuscarEstados() {

		try {

			System.out.println("Listando estados...");

			estadoDAO = new EstadoDAO();
			listaEstado = estadoDAO.listar("nome");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

	// Listar de Cidade
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void buscarCidade() {

		try {

			System.out.println("Listando Cidade...");

			CidadeDAO cidadeDAO = new CidadeDAO();
			listaCidade = cidadeDAO.buscarPorEstado(filtrarEstado);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

	// Filtrar Cidade
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void filtrarCidade() {

		try {


			cidadeDAO = new CidadeDAO();
			listaCidade = cidadeDAO.buscarPorEstado(estado.getCodigo());
			auxCidade = "Selecione uma Cidade";

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Filtrar Cidade 2, precisei replicar o método, devido a um erro na linha
	// quando chamada dentro do getinstance, por conta do param (actionevent)
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	
	public Boolean getBotaoEditar() {
		return botaoEditar;
	}

	public Oportunidade getOportunidade() {
		return oportunidade;
	}

	public void setOportunidade(Oportunidade oportunidade) {
		this.oportunidade = oportunidade;
	}

	public List<Oportunidade> getListaOportunidade() {
		return listaOportunidade;
	}

	public void setListaOportunidade(List<Oportunidade> listaOportunidade) {
		this.listaOportunidade = listaOportunidade;
	}

	public void setBotaoEditar(Boolean botaoEditar) {
		this.botaoEditar = botaoEditar;
	}

	public Boolean getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(Boolean botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public String getAuxEstado() {
		return auxEstado;
	}

	public void setAuxEstado(String auxEstado) {
		this.auxEstado = auxEstado;
	}

	public String getAuxCidade() {
		return auxCidade;
	}

	public void setAuxCidade(String auxCidade) {
		this.auxCidade = auxCidade;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public String getFiltrarCargo() {
		return filtrarCargo;
	}

	public void setFiltrarCargo(String filtrarCargo) {
		this.filtrarCargo = filtrarCargo;
	}

	public Long getComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(Long comboCidade) {
		this.comboCidade = comboCidade;
	}

	public Long getFiltrarEstado() {
		return filtrarEstado;
	}

	public void setFiltrarEstado(Long filtrarEstado) {
		this.filtrarEstado = filtrarEstado;
	}

	public int getFiltrarSalario() {
		return filtrarSalario;
	}

	public void setFiltrarSalario(int filtrarSalario) {
		this.filtrarSalario = filtrarSalario;
	}

	public String getFiltrarNivel() {
		return filtrarNivel;
	}

	public void setFiltrarNivel(String filtrarNivel) {
		this.filtrarNivel = filtrarNivel;
	}

	public String getFiltrarModalidade() {
		return filtrarModalidade;
	}

	public void setFiltrarModalidade(String filtrarModalidade) {
		this.filtrarModalidade = filtrarModalidade;
	}

	public int getFiltrarPcD() {
		return FiltrarPcD;
	}

	public void setFiltrarPcD(int filtrarPcD) {
		FiltrarPcD = filtrarPcD;
	}

	public int getTotasVagas() {
		return totasVagas;
	}

	public void setTotasVagas(int totasVagas) {
		this.totasVagas = totasVagas;
	}

	
	

 
	
}