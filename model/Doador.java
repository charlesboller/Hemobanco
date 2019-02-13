package model;

import java.util.Date;

public class Doador {
	
	private int idDoador;
	private Date dataDoador; 
	private int idTipoSangue;
	private boolean disponivelDoador;
	private int idPessoa;
	
	
	public int getIdDoador() {
		return idDoador;
	}


	public void setIdDoador(int idDoador) {
		this.idDoador = idDoador;
	}


	public Date getDataDoador() {
		return dataDoador;
	}


	public void setDataDoador(Date dataDoador) {
		this.dataDoador = dataDoador;
	}


	public int getIdTipoSangue() {
		return idTipoSangue;
	}


	public void setIdTipoSangue(int idTipoSangue) {
		this.idTipoSangue = idTipoSangue;
	}


	public boolean isDisponivelDoador() {
		return disponivelDoador;
	}


	public void setDisponivelDoador(boolean disponivelDoador) {
		this.disponivelDoador = disponivelDoador;
	}


	public int getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}


	public Doador(int idDoador, Date dataDoador, int idTipoSangue, boolean disponivelDoador, int idPessoa) {
		super();
		this.idDoador = idDoador;
		this.dataDoador = dataDoador;
		this.idTipoSangue = idTipoSangue;
		this.disponivelDoador = disponivelDoador;
		this.idPessoa = idPessoa;
	}
	
	public Doador() {
		
	}
	

}
