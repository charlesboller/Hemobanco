package model;

public class RelSolicitar {
	
	private int idPessoa;
	private String nome;
	private String email;
	private String telefone;
	private int idTipoSangue;
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getIdTipoSangue() {
		return idTipoSangue;
	}
	public void setIdTipoSangue(int idTipoSangue) {
		this.idTipoSangue = idTipoSangue;
	}
	
	
	public RelSolicitar(int idPessoa, String nome, String email, String telefone, int idTipoSangue) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.idTipoSangue = idTipoSangue;
	}
	

}
