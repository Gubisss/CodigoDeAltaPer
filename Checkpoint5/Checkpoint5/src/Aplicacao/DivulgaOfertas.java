package Aplicacao;

import java.util.ArrayList;
import java.util.Scanner;

import ABB.ABBCliente;
import Objeto.Cliente;

public class DivulgaOfertas {
	/*
	 * NOMES E RM dos alunos que compõem o grupo : Gustavo Nascimento Aguilar - 93292 , Gabriel Jun Itikawa - 93540 ,
	 * Alexandre Biancolini dos Santos 93769 , Rafael de Lima Souza 92876 , João Pedro Marson 93432 .
	 */
	public static void main(String[] args) {
		Scanner le = new Scanner(System.in);
		/*
		 * Cria a uma árvore de busca binária para cada tipo de conta (pessoa física
		 * ou jurídica)
		 */
		ABBCliente fis = new ABBCliente();
		ABBCliente jur = new ABBCliente();

		int opcao, op, numeroConta;
		String nome, cpfCnpj;
		String tipoConta = null;
		double saldo;
		do {
			System.out.println(" 0 - Encerrar o programa");
			System.out.println(" 1 - Inscrição cliente");
			System.out.println(" 2 - Oferta de novo serviço e/ou aplicação");
			System.out.println(" 3 – Entrar no Submenu ");
			opcao = le.nextInt();
			switch (opcao) {
			case 1:
				System.out.print("Digite nome: ");
				nome = le.next();
				System.out.print("Digite cpf ou cnpj: ");
				cpfCnpj = le.next();
				System.out.print("Digite número da conta: ");
				numeroConta = le.nextInt();
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Fisica";
						break;
					case 2:
						tipoConta = "Juridica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
				} while (op == -1);
				System.out.print("Informe saldo em aplicações R$: ");
				saldo = le.nextDouble();
				/*
				 * Intancia um objeto da classe Cliente e insere na ABB correspondente a tipo de
				 * conta
				 */
				Cliente cli = new Cliente(nome, cpfCnpj, numeroConta, tipoConta, saldo);
				if (tipoConta == "Fisica") {
					fis.root = fis.inserirAVL(fis.root, cli);
					fis.atualizaAlturas(fis.root);
					fis.mostraFB(fis.root);
				} else {
					jur.root = jur.inserirAVL(jur.root, cli);
					jur.atualizaAlturas(jur.root);
					jur.mostraFB(jur.root);
				}

				break;
			case 2:
				System.out.print("Qual tipo de conta a oferta se destina? ");
				do {
					System.out.print("Digite 1- Pessoa Fisica 2- Pessoa Juridica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Fisica";
						break;
					case 2:
						tipoConta = "Juridica";
						break;
					default:
						System.out.println("Opcaoo invalida ");
						op = -1;
					}

				} while (op == -1);
				System.out.print("Qual o valor de saldo minimo exigido: R$ ");
				saldo = le.nextDouble();
				/*
				 * Fazendo uso de um método da classe ABB, desenvolvido para este problema, uma
				 * lista de clientes aptos para a oferta é gerada. Nesse trecho de programa que
				 * tentar fazer o contato com todos os clientes presente na lista.
				 */
				ArrayList<Cliente> l = new ArrayList<>();
				if (tipoConta == "Fisica") {
					System.out.println("\n **** Lista de Clientes ****");
					fis.listaSaldoMinimo(fis.root, saldo, l);
					System.out.println("******************************");

					for (Cliente cliente : l) {
						do {
							System.out.println("O cliente: " + cliente);
							System.out.println("Deseja aceitar a oferta do banco? 1-sim 2-nao");
							op = le.nextInt();
							switch (op) {
							case 1:
								System.out.println("Cliente aceitou a oferta");
								fis.root = fis.removeValor(fis.root,cliente);
								fis.atualizaAlturaBalanceamento(fis.root);
								fis.atualizaAlturas(fis.root);
								break;
							case 2:
								System.out.println("Cliente recusou a oferta");
								System.out.println();
								break;
							default:
								System.out.println("Opcao invalida ");
								op = -1;
							}
						} while (op == -1);
					}

				} else {
					System.out.println("\n **** Lista de Clientes ****");
					jur.listaSaldoMinimo(jur.root, saldo, l);
					System.out.println("******************************");

					for (Cliente cliente : l) {
						do {
							System.out.println("O cliente: " + cliente);
							System.out.println("Deseja aceitar a oferta do banco? 1-sim 2-nao");
							op = le.nextInt();
							switch (op) {
							case 1:
								System.out.println("Cliente aceitou a oferta");
								jur.root = jur.removeValor(jur.root,cliente);
								jur.atualizaAlturaBalanceamento(jur.root);
								jur.atualizaAlturas(jur.root);
								break;
							case 2:
								System.out.println("Cliente recusou a oferta");
								System.out.println();
								break;
							default:
								System.out.println("Opcao invalida ");
								op = -1;
							}
						} while (op == -1);
					}
				}
				break;
			case 3:
				do {
					/*
					 * Implemente o submenu descrito no texto
					 */
					System.out.println("Submenu");
					System.out.println("1 - Consulta cliente buscando pelo CPF ou CNPJ ");
					System.out.println("2 - Atualiza��o do saldo do cliente buscando pelo n�mero da conta");
					System.out.println("3 - Apresenta a quantidade de clientes armazenado na ABB");
					System.out.println("4 - Apresneta a quantidade de clientes com saldo acima de um valor");
					System.out.println("5 - Voltar ao menu principal");
					op = le.nextInt();
					switch (op) {
					case 1:
						System.out.print("Qual tipo de conta deseja consultar? ");
						do {
							System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
							op = le.nextInt();
							switch (op) {
							case 1:
								tipoConta = "Fisica";
								break;
							case 2:
								tipoConta = "Juridica";
								break;
							default:
								System.out.println("Opcao invalida ");
								op = -1;
							}
						} while (op == -1);
						if (tipoConta == "Fisica") {
							System.out.println("Insira o CPF a ser pesquisado: ");
							cpfCnpj = le.next();
							if (fis.consultaCpfCnpj(fis.root, cpfCnpj) == null) {
								System.out.println("Cliente n�o encontrado");
							} else {
								System.out.println("Cliente encontrado");
								jur.consultaCpfCnpj(jur.root, cpfCnpj);
								
							}

						} else {
							System.out.println("Insira o CNPJ a ser pesquisado: ");
							cpfCnpj = le.next();
							if (jur.consultaCpfCnpj(jur.root, cpfCnpj) == null) {
								System.out.println("Cliente n�o encontrado");
							} else {
								System.out.println("Cliente encontrado");
								jur.consultaCpfCnpj(jur.root, cpfCnpj);
							}
						}
						break;
					case 2:
						System.out.println("Insira o numero da conta que deseja atualizar: ");
						numeroConta = le.nextInt();
						break;
					case 3:
						System.out.println("Clientes PF: ");
						System.out.println(fis.apresentaQtd(fis.root));
						System.out.println("Clientes PJ: ");
						System.out.println(fis.apresentaQtd(fis.root));
						break;
					case 4:
						System.out.println("Insira um valor: ");
						saldo = le.nextInt();
						System.out.println("Quantidade de pessoas fisicas");
						fis.listaQtdSaldoAcima(fis.root, saldo);
						System.out.println("Quantidade de pessoas juridicas");
						jur.listaQtdSaldoAcima(jur.root, saldo);

						break;

					}

				} while (op != 5);
				break;
			case 4:
				System.out.println("Encerrando o programa");
				
				op = 0;
				break;
			}
		} while (opcao != 0);
		
		System.out.println("Clientes que não eram aptos ou não aceitaram a proposta");
		System.out.println("\nContas Físicas:");
		fis.zera(fis.root);
		System.out.println("\nContas Jurídicas:");
		jur.zera(jur.root);
		le.close();
		
	}
}