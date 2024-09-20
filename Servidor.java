package com.example;
import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static Set<Socket> socketsClientes = new HashSet<>();
    private static final int PORTA = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket servidorSocket = new ServerSocket(PORTA);
        System.out.println("Servidor iniciando, aguardando conexões.");

        while (true) {
            Socket socketCliente = servidorSocket.accept();
            socketsClientes.add(socketCliente);
            System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

            new Thread(new TratadorCliente(socketCliente)).start();
        }
    }

    static class TratadorCliente implements Runnable {
        private Socket socket;
        private BufferedReader in;

        public TratadorCliente(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                String mensagem;
                while ((mensagem = in.readLine()) != null) {
                    System.out.println("Mensagem recebida: " + mensagem);
                    enviarMensagemParaTodos(mensagem);
                }
            } catch (IOException e) {
                System.out.println("Erro de comunicação: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    socketsClientes.remove(socket);
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o socket: " + e.getMessage());
                }
            }
        }

        private void enviarMensagemParaTodos(String mensagem) throws IOException {
            for (Socket s : socketsClientes) {
                if (!s.equals(socket)) {
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    out.println(mensagem);
                }
            }
        }
    }
}
