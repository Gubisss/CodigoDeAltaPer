package ABB;

import java.util.ArrayList;

import Objeto.Cliente;

public class ABBCliente {

	private class ARVORE {
		Cliente dado;
		ARVORE dir;
		ARVORE esq;
		int hEsq;
		int hDir;
	}

	public ARVORE root = null;

	public void mostra(ARVORE p) {
		if (p != null) {
			mostra(p.esq);
			System.out.println(" " + p.dado);
			mostra(p.dir);
		}
	}

	public int listaQtdSaldoAcima(ARVORE p, double limite) {
		int cont = 0;
		if (p != null) {
			listaQtdSaldoAcima(p.esq, limite);
			if (p.dado.getSaldo() < limite)
				cont++;
			listaQtdSaldoAcima(p.dir, limite);
			return cont;
		}
		return cont;
	}

	public ArrayList<Cliente> listaSaldoMinimo(ARVORE p, double minimo, ArrayList<Cliente> l) {

		if (p != null) {
			listaSaldoMinimo(p.dir, minimo, l);
			if (p.dado.getSaldo() >= minimo) {
				l.add(p.dado);
				System.out.println(p.dado);
				listaSaldoMinimo(p.esq, minimo, l);
			}
		}
		return l;
	}

	public void mostraFB(ARVORE p) {
		if (p != null) {
			mostraFB(p.esq);
			mostraFB(p.dir);
			System.out.println(" Dado: " + p.dado + "\t FB = " + (p.hDir - p.hEsq));
		}
	}

	public ARVORE rotacaoEsquerda(ARVORE p) {
		// faz rotação para esquerda em relação ao nó apontado por p
		ARVORE q, temp;
		q = p.dir;
		temp = q.esq;
		q.esq = p;
		p.dir = temp;
		return q;
	}

	public ARVORE rotacaoDireita(ARVORE p) {
		// faz rotação para direita em relação ao nó apontado por p
		ARVORE q, temp;
		q = p.esq;
		temp = q.dir;
		q.dir = p;
		p.esq = temp;
		return q;
	}

	public ARVORE balanceamento(ARVORE c) {
		// analisa FB e realiza rotações necessárias para balancear árvore
		int FB = c.hDir - c.hEsq;
		if (FB > 1) {
			int fbFilhoDir = c.dir.hDir - c.dir.hEsq;
			if (fbFilhoDir >= 0)
				c = rotacaoEsquerda(c);
			else {
				c.dir = rotacaoDireita(c.dir);
				c = rotacaoEsquerda(c);
			}
		} else {
			if (FB < -1) {
				int fbFilhoEsq = c.esq.hDir - c.esq.hEsq;
				if (fbFilhoEsq <= 0)
					c = rotacaoDireita(c);
				else {
					c.esq = rotacaoEsquerda(c.esq);
					c = rotacaoDireita(c);
				}
			}
		}
		return c;
	}

	public ARVORE inserirAVL(ARVORE c, Cliente info) {
		if (c == null) { // nó inserido sempre será nó folha
			c = new ARVORE();
			c.dado = info;
			c.esq = null;
			c.dir = null;
			c.hDir = 0;
			c.hEsq = 0;
		} else if (info.getSaldo() < c.dado.getSaldo()) {
			c.esq = inserirAVL(c.esq, info);
			if (c.esq.hDir > c.esq.hEsq) // Altura do nó será a maior
				c.hEsq = c.esq.hDir + 1; // altura dos seus filhos
			else
				c.hEsq = c.esq.hEsq + 1;
			c = balanceamento(c);
		} else {
			c.dir = inserirAVL(c.dir, info);
			if (c.dir.hDir > c.dir.hEsq)
				c.hDir = c.dir.hDir + 1;
			else
				c.hDir = c.dir.hEsq + 1;
			c = balanceamento(c);
		}
		return c;
	}

	public void atualizaAlturas(ARVORE p) {
		/*
		 * atualiza informação da altura de cada nó depois da remoção percorre a
		 * árvore usando percurso pós-ordem para ajustar primeiro os nós folhas
		 * (profundidade maior) e depois os níveis acima
		 */
		if (p != null) {
			atualizaAlturas(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else if (p.esq.hEsq > p.esq.hDir)
				p.hEsq = p.esq.hEsq + 1;
			else
				p.hEsq = p.esq.hDir + 1;
			atualizaAlturas(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				p.hDir = p.dir.hEsq + 1;
			else
				p.hDir = p.dir.hDir + 1;
		}
	}

	public ARVORE removeValor(ARVORE p, Cliente info) {
		if (p != null) {
			if (info.getNumeroConta() == p.dado.getNumeroConta()) {
				if (p.esq == null && p.dir == null)
					return null;
				else {
					if (p.esq == null)
						return p.dir;
					else if (p.dir == null)
						return p.esq;
					else {
						ARVORE aux, ref;
						ref = p.dir;
						aux = p.dir;
						while (aux.esq != null)
							aux = aux.esq;
						aux.esq = p.esq;
						return ref;
					}
				}
			} else { // procura dado a ser removido na ABB
				if (info.getNumeroConta() < p.dado.getNumeroConta())
					p.esq = removeValor(p.esq, info);
				else
					p.dir = removeValor(p.dir, info);
			}
		}
		return p;
	}

	public ARVORE atualizaAlturaBalanceamento(ARVORE p) {
		/*
		 * atualiza informação da altura de cada nó depois da remoção percorre a
		 * árvore usando percurso pós-ordem para ajustar primeiro os nós folhas
		 * (profundidade maior) e depois os níveis acima
		 */
		if (p != null) {
			p.esq = atualizaAlturaBalanceamento(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else if (p.esq.hEsq > p.esq.hDir)
				p.hEsq = p.esq.hEsq + 1;
			else
				p.hEsq = p.esq.hDir + 1;
			p.dir = atualizaAlturaBalanceamento(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				p.hDir = p.dir.hEsq + 1;
			else
				p.hDir = p.dir.hDir + 1;
			p = balanceamento(p);
		}
		return p;
	}

	public int contaNos(ARVORE p, int cont) {
		if (p != null) {
			cont++;
			cont = contaNos(p.esq, cont);
			cont = contaNos(p.dir, cont);
		}
		return cont;
	}

	public Cliente consultaSaldo(ARVORE p, int codigo) {
		if (p == null)
			return null;
		else {
			if (codigo == p.dado.getSaldo())
				return p.dado;
			else {
				if (codigo < p.dado.getSaldo())
					return consultaSaldo(p.esq, codigo);
				else
					return consultaSaldo(p.dir, codigo);
			}
		}
	}
	
	public boolean atualizaSaldo(ARVORE p, int numeroConta, double novoSaldo, boolean encontrou) {
		if (p != null && !encontrou) {
			if (p.dado.getNumeroConta() == numeroConta) {
				System.out.println("Saldo Anterior: " + p.dado.toString());
				root = removeValor(root, p.dado);
				root = atualizaAlturaBalanceamento(root);
				atualizaAlturas(root);
				p.dado.setSaldo(novoSaldo);
				System.out.println("Saldo Atual: " + p.dado.toString());
				root = inserirAVL(root, p.dado);
				atualizaAlturas(root);
				encontrou = true;
			}
			encontrou = atualizaSaldo(p.esq, numeroConta, novoSaldo, encontrou);
			encontrou = atualizaSaldo(p.dir, numeroConta, novoSaldo, encontrou);
		}
		return encontrou;
		
	}

	public Cliente consultaCpfCnpj(ARVORE p, String cpfCnpj) {

		Cliente b = null;
		if (p != null) {
			if (p.dado.getCpfCnpj().equals(cpfCnpj)) {
				b = p.dado;
			
			}
				b=consultaCpfCnpj(p.esq, cpfCnpj);
				b=consultaCpfCnpj(p.dir, cpfCnpj);
				
		}
		return b;
	}

	public int apresentaQtd(ARVORE p) {
		int cont = 0;
		if (p != null) {
			apresentaQtd(p.esq);
			cont++;
			apresentaQtd(p.dir);
			return cont;
		}
		return cont;

	}
	
	public void zera(ARVORE p) {
		if (p != null) {
			zera(p.esq);
			System.out.println(" "+p.dado.toString());
			zera(p.dir);
			root = removeValor(root, p.dado);
		}
	}

}
