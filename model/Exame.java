package model;

import java.util.Date;

public class Exame {
	
	private int IdExame;
	private int GlicoseExame;
	private int ColesterolExame;
	private int HemaciasExame;
	private int LeucocitosExame;
	private int PlaquetasExame;
	private Boolean PossuiDITExame;
	private Boolean AptoExame;
	private Boolean DisponivelSangueExame;
	private Date dataDoador; 
	int IdTipoSangue;
	int IdDoador;
	
	public Exame() {
		super();
	}
	
	
	public Exame(int id_Exame, Date data_Doador, int Id_TipoSangue) {
		IdExame = id_Exame;
		IdTipoSangue = Id_TipoSangue;
		dataDoador = data_Doador;
	}
	
	
	public Exame(int idExame, int glicoseExame, int colesterolExame, int hemaciasExame, int leucocitosExame,
			int plaquetasExame, Boolean possuiDITExame, Boolean aptoExame, Boolean disponivelSangueExame,
			int idTipoSangue, int idDoador) {
		super();
		IdExame = idExame;
		GlicoseExame = glicoseExame;
		ColesterolExame = colesterolExame;
		HemaciasExame = hemaciasExame;
		LeucocitosExame = leucocitosExame;
		PlaquetasExame = plaquetasExame;
		PossuiDITExame = possuiDITExame;
		AptoExame = aptoExame;
		DisponivelSangueExame = disponivelSangueExame;
		IdTipoSangue = idTipoSangue;
		IdDoador = idDoador;
	}

	public int getIdExame() {
		return IdExame;
	}

	public void setIdExame(int idExame) {
		IdExame = idExame;
	}

	public int getGlicoseExame() {
		return GlicoseExame;
	}

	public void setGlicoseExame(int glicoseExame) {
		GlicoseExame = glicoseExame;
	}

	public int getColesterolExame() {
		return ColesterolExame;
	}

	public void setColesterolExame(int colesterolExame) {
		ColesterolExame = colesterolExame;
	}

	public int getHemaciasExame() {
		return HemaciasExame;
	}

	public void setHemaciasExame(int hemaciasExame) {
		HemaciasExame = hemaciasExame;
	}

	public int getLeucocitosExame() {
		return LeucocitosExame;
	}

	public void setLeucocitosExame(int leucocitosExame) {
		LeucocitosExame = leucocitosExame;
	}

	public int getPlaquetasExame() {
		return PlaquetasExame;
	}

	public void setPlaquetasExame(int plaquetasExame) {
		PlaquetasExame = plaquetasExame;
	}

	public Boolean getPossuiDITExame() {
		return PossuiDITExame;
	}

	public void setPossuiDITExame(Boolean possuiDITExame) {
		PossuiDITExame = possuiDITExame;
	}

	public Boolean getAptoExame() {
		return AptoExame;
	}

	public void setAptoExame(Boolean aptoExame) {
		AptoExame = aptoExame;
	}

	public Boolean getDisponivelSangueExame() {
		return DisponivelSangueExame;
	}

	public void setDisponivelSangueExame(Boolean disponivelSangueExame) {
		DisponivelSangueExame = disponivelSangueExame;
	}

	public int getIdTipoSangue() {
		return IdTipoSangue;
	}

	public void setIdTipoSangue(int idTipoSangue) {
		IdTipoSangue = idTipoSangue;
	}

	public int getIdDoador() {
		return IdDoador;
	}

	public void setIdDoador(int idDoador) {
		IdDoador = idDoador;
	}

	public Date getDataDoador() {
		return dataDoador;
	}

	public void setDataDoador(Date dataDoador) {
		this.dataDoador = dataDoador;
	}

	
	

}
