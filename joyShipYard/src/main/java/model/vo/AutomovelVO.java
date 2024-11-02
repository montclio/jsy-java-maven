package model.vo;

import java.util.Date;

public class AutomovelVO {
		private int idAutomovel;
	    private String cor;
	    private int crvl;
	    private String modelo;
	    private String fabricante;
	    private String placa;
		private boolean assegurado;
	    private int quilometragem;
	    private Date anoFabricacao;
	    private Date anoCirculacao;
	    
		public AutomovelVO(int idAutomovel, String cor, int crvl, String modelo, String fabricante, String placa,
				boolean assegurado, int quilometragem, Date anoFabricacao, Date anoCirculacao) {
			this.idAutomovel = idAutomovel;
			this.cor = cor;
			this.crvl = crvl;
			this.modelo = modelo;
			this.fabricante = fabricante;
			this.placa = placa;
			this.assegurado = assegurado;
			this.quilometragem = quilometragem;
			this.anoFabricacao = anoFabricacao;
			this.anoCirculacao = anoCirculacao;
		}
	    
	    
		public int getIdAutomovel() {
			return idAutomovel;
		}
		public void setIdAutomovel(int idAutomovel) {
			this.idAutomovel = idAutomovel;
		}
		public String getCor() {
			return cor;
		}
		public void setCor(String cor) {
			this.cor = cor;
		}
		public int getCrvl() {
			return crvl;
		}
		public void setCrvl(int crvl) {
			this.crvl = crvl;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public String getFabricante() {
			return fabricante;
		}
		public void setFabricante(String fabricante) {
			this.fabricante = fabricante;
		}
		public String getPlaca() {
			return placa;
		}
		public void setPlaca(String placa) {
			this.placa = placa;
		}
		public boolean isAssegurado() {
			return assegurado;
		}
		public void setAssegurado(boolean assegurado) {
			this.assegurado = assegurado;
		}
		public int getQuilometragem() {
			return quilometragem;
		}
		public void setQuilometragem(int quilometragem) {
			this.quilometragem = quilometragem;
		}
		public Date getAnoFabricacao() {
			return anoFabricacao;
		}
		public void setAnoFabricacao(Date anoFabricacao) {
			this.anoFabricacao = anoFabricacao;
		}
		public Date getAnoCirculacao() {
			return anoCirculacao;
		}
		public void setAnoCirculacao(Date anoCirculacao) {
			this.anoCirculacao = anoCirculacao;
		}

		
		

}
