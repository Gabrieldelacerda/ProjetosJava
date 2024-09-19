package com.example;

class Livro {
	private String titulo;
	private String autor;
	private Boolean disponivel;
	
	public Livro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
		this.disponivel = true;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}
	
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	@Override
	public String toString() {
		return "Título: " + titulo + ", Autor: " + autor; 
	}
}

class Usuario {
	private String nome;
	private String email;
	
	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + ", Email: " + email;
	}
}

class Emprestimo {
	private Livro livro;
	private Usuario usuario;
	
	public Emprestimo(Livro livro, Usuario usuario) {
		if (livro.isDisponivel()) {
			this.livro = livro;
			this.usuario = usuario;
			livro.setDisponivel(false);
			System.out.println("Empréstimo feito: " + livro.getTitulo() + " para " + usuario.getNome());
		} else {
			System.out.println("Livro indisponível: " + livro.getTitulo());
		}
	}
	
	public void devolverLivro() {
		livro.setDisponivel(true);
		System.out.println("Livro devolvido: " + livro.getTitulo() + " por " + usuario.getNome());
	}
}

public class GerenciamentoBiblioteca {
	public static void main(String[] args) {
		Livro livro1 = new Livro("Harry Potter", "J.K. Rowling");
		Livro livro2 = new Livro("Senhor dos Anéis", "J.R.R. Tolkien");
		
		Usuario usuario1 = new Usuario("Gabriel", "gabriel@outlook.com");
		Usuario usuario2 = new Usuario("Larissa", "larissa@gmail.com");
		
		Emprestimo emprestimo1 = new Emprestimo(livro1, usuario1);
		Emprestimo emprestimo2 = new Emprestimo(livro2, usuario2);

		Emprestimo emprestimo3 = new Emprestimo(livro1, usuario2);
		emprestimo1.devolverLivro();
		Emprestimo emprestimo4 = new Emprestimo(livro1, usuario2);
	}
}
