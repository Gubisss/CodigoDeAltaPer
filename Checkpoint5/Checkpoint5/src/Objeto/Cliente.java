package Objeto;

public class Cliente {

	private String nome;
	private String cpfCnpj;
	private int numeroConta;
	private String tipoConta;
	private double saldo;
	
	
	
	
	
	public Cliente(String nome, String cpfCnpj, int numeroConta, String tipoConta, double saldo) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.numeroConta = numeroConta;
		this.tipoConta = tipoConta;
		this.saldo = saldo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return "Cliente [nome = " + nome + ", cpf = " + cpfCnpj + ", numero da conta = " + numeroConta + ", tipo da conta = " + tipoConta + ", saldo = " + saldo +  "]";
	}
	
	
	
	
	
}
