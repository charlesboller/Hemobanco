package model;

import java.sql.Date;

public class Consulta {
	
	private int id;
	private int idPessoa;
	private Date nascimento;
	private float altura;
	private float peso;
	private boolean gravidez;
	private boolean gripe;
	private boolean tatuagem;
	private boolean cirurgia;
	private boolean dit;
	private boolean drogas;
	private boolean apto;
	private int idUsuario;
	private Date data;
	
	public Consulta(int id, int idPessoa, Date nascimento, float altura, float peso, boolean gravidez, boolean gripe,
			boolean tatuagem, boolean cirurgia, boolean dit, boolean drogas, boolean apto, int idUsuario) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.nascimento = nascimento;
		this.altura = altura;
		this.peso = peso;
		this.gravidez = gravidez;
		this.gripe = gripe;
		this.tatuagem = tatuagem;
		this.cirurgia = cirurgia;
		this.dit = dit;
		this.drogas = drogas;
		this.apto = apto;
		this.idUsuario = idUsuario;
	}

	public Consulta() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public boolean isGravidez() {
		return gravidez;
	}
	public void setGravidez(boolean gravidez) {
		this.gravidez = gravidez;
	}
	public boolean isGripe() {
		return gripe;
	}
	public void setGripe(boolean gripe) {
		this.gripe = gripe;
	}
	public boolean isTatuagem() {
		return tatuagem;
	}
	public void setTatuagem(boolean tatuagem) {
		this.tatuagem = tatuagem;
	}
	public boolean isCirurgia() {
		return cirurgia;
	}
	public void setCirurgia(boolean cirurgia) {
		this.cirurgia = cirurgia;
	}
	public boolean isDit() {
		return dit;
	}
	public void setDit(boolean dit) {
		this.dit = dit;
	}
	public boolean isDrogas() {
		return drogas;
	}
	public void setDrogas(boolean drogas) {
		this.drogas = drogas;
	}
	public boolean isApto() {
		return apto;
	}
	public void setApto(boolean apto) {
		this.apto = apto;
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
	
	

}
