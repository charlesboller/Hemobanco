package model;

import java.util.Date;

public class Pessoa {
	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String endereco;
	private String bairro;
	private String cidade;
	private String cep;
	private String celular;
	private String telefone;
	private String estado;
	private int idUsuario;
	private Date data;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Pessoa(int idTxt, String nomeTxt, String cpfTxt, String emailTxt, String enderecoTxt, 
			String bairroTxt, String cidadeTxt, String celularTxt, String telefoneTxt, 
			String cepTxt, String estadoTxt, int idUsuarioInt) {
		// TODO Auto-generated constructor stub
		
		id = idTxt;
		nome = nomeTxt;
		cpf = cpfTxt;
		email = emailTxt;
		endereco = enderecoTxt;
		bairro = bairroTxt;
		cidade = cidadeTxt;
		celular = celularTxt;
		telefone = telefoneTxt;
		cep = cepTxt;
		estado = estadoTxt;
		idUsuario = idUsuarioInt;
		
	}
	
	public Pessoa(){
		
	}



}
