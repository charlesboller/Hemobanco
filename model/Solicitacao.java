package model;

import java.util.Date;

public class Solicitacao {
	
	private int idSolicitacao;
	private Date dataSolicitacao;
	private int idExame;
	private int idSolicitante;
	
	
	public Solicitacao(int idSolicitacao, Date dataReceptor, int idExame, int idSolicitante) {
		super();
		this.idSolicitacao = idSolicitacao;
		this.dataSolicitacao = dataReceptor;
		this.idExame = idExame;
		this.idSolicitante = idSolicitante;
	}
	
	
	public int getIdReceptor() {
		return idSolicitacao;
	}
	public void setIdReceptor(int idReceptor) {
		this.idSolicitacao = idReceptor;
	}
	public Date getDataReceptor() {
		return dataSolicitacao;
	}
	public void setDataReceptor(Date dataReceptor) {
		this.dataSolicitacao = dataReceptor;
	}
	public int getIdExame() {
		return idExame;
	}
	public void setIdExame(int idExame) {
		this.idExame = idExame;
	}
	public int getIdSolicitante() {
		return idSolicitante;
	}
	public void setNomeReceptor(int nomeReceptor) {
		this.idSolicitante = nomeReceptor;
	}
	
	

}
