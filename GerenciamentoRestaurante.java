package com.example;
import java.util.ArrayList;
import java.util.List;

class Item {
	private String nome;
	private double preco;
	
	public Item(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getPreco() {
		return preco;
	}
	
	@Override
	public String toString() {
		return nome + " - R$ " + preco;
	}
}

class Cliente {
	private String nome;
	private String telefone;
	
	public Cliente(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	@Override
	public String toString() {
		return "Cliente: " + nome + ", Telefone: " + telefone;
	}
}

class Pedido {
	private Cliente cliente;
	private List<Item> itens;
	private boolean finalizado;
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
		this.itens = new ArrayList<>();
		this.finalizado = false;
	}
	
	public void adicionarItem(Item item) {
		if (!finalizado) {
			itens.add(item);
			System.out.println("Item adicionado: " + item.getNome());
		} else {
			System.out.println("Não é possível adicionar itens. O pedido já foi finalizado!!");
		}
	}
	
	public void removerItem(Item item) {
		if (!finalizado) {
			if (itens.remove(item)) {
				System.out.println("Item removido: " + item.getNome());
			} else {
				System.out.println("Item não encontrado no pedido.");
			}
		} else {
			System.out.println("Não é possível remover itens, pois o pedido já foi finalizado!");
		}
	}
	
	public double calcularTotal() {
		return itens.stream().mapToDouble(Item::getPreco).sum();
	}
	
	public void finalizarPedido() {
		if (!finalizado) {
			finalizado = true;
			System.out.println("Pedido finalizado. Total: R$ " + calcularTotal());
		} else {
			System.out.println("O pedido foi finalizado.");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder detalhes = new StringBuilder();
		detalhes.append(cliente).append("\nItens do pedido:\n");
		for (Item item : itens) {
			detalhes.append(item).append("\n");
		}
		return detalhes.toString();
	}
}

public class GerenciamentoRestaurante {

	public static void main(String[] args) {
		Item item1 = new Item("Hambúrguer", 20.00);
		Item item2 = new Item("Pizza", 45.00);
		Item item3 = new Item("Batata Frita", 9.00);
		
		Cliente cliente1 = new Cliente("Gabriel", "1234-5678");
		Cliente cliente2 = new Cliente("João", "8765-4321");
		
		Pedido pedido1 = new Pedido(cliente1);
		Pedido pedido2 = new Pedido(cliente2);
		
		pedido1.adicionarItem(item1);
		pedido1.adicionarItem(item2);
		
		pedido2.adicionarItem(item3);
		pedido2.adicionarItem(item1);
		
		System.out.println(pedido1);
		System.out.println(pedido2);
		
		pedido1.finalizarPedido();
		pedido2.finalizarPedido();

	}

}
