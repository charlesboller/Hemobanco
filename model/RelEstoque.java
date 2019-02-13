package model;

import java.util.Date;

public class RelEstoque {
	
	private int idExame;
	private Date dataDoador;
	private int idTipoSangue;

	
	
	public RelEstoque(int idExame, Date dataDoador, int idTipoSangue) {
		super();
		this.idExame = idExame;
		this.dataDoador = dataDoador;
		this.idTipoSangue = idTipoSangue;
	}
	
	
	public RelEstoque() {
		super();
	}


	public int getIdExame() {
		return idExame;
	}
	public void setIdExame(int idDoador) {
		this.idExame = idDoador;
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
	
	
	


}
