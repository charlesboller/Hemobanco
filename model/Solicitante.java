package model;

public class Solicitante {
	int idSolicitante;
	String nomeSolicitante;
	String cnpjSolicitante;
	String enderecoSolicitante;
	String telSolicitante;

	public Solicitante() {
	}

	public Solicitante(int idSolicitante, String nomeSolicitante, String cnpjSolicitante, String enderecoSolicitante,
			String telSolicitante) {
		super();
		this.idSolicitante = idSolicitante;
		this.nomeSolicitante = nomeSolicitante;
		this.cnpjSolicitante = cnpjSolicitante;
		this.enderecoSolicitante = enderecoSolicitante;
		this.telSolicitante = telSolicitante;
	}

	public int getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getCnpjSolicitante() {
		return cnpjSolicitante;
	}

	public void setCnpjSolicitante(String cnpjSolicitante) {
		this.cnpjSolicitante = cnpjSolicitante;
	}

	public String getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(String enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public String getTelSolicitante() {
		return telSolicitante;
	}

	public void setTelSolicitante(String telSolicitante) {
		this.telSolicitante = telSolicitante;
	}
	

	
}
