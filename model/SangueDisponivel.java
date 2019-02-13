package model;

public class SangueDisponivel {
	
	private int TipoSangue;
	private int QtdSangue;
	
	public int getTipoSangue() {
		return TipoSangue;
	}

	public void setTipoSangue(int tipoSangue) {
		TipoSangue = tipoSangue;
	}

	public int getQtdSangue() {
		return QtdSangue;
	}

	public void setQtdSangue(int qtdSangue) {
		QtdSangue = qtdSangue;
	}

	public SangueDisponivel(int tipoSangue, int qtdSangue) {
		super();
		TipoSangue = tipoSangue;
		QtdSangue = qtdSangue;
	}

}
