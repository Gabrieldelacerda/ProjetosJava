package com.example;
import java.io.*;
import java.net.*;

public class Cliente {
    private static final String ENDERECO_SERVIDOR = "localhost";
    private static final int PORTA_SERVIDOR = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA_SERVIDOR);
        System.out.println("Você está conectado ao servidor.");

        new Thread(new TratadorReceberMensagens(socket)).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        String mensagem;
        while ((mensagem = teclado.readLine()) != null) {
            out.println(mensagem);
        }
    }

    static class TratadorReceberMensagens implements Runnable {
        private Socket socket;
        private BufferedReader in;

        public TratadorReceberMensagens(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                String mensagem;
                while ((mensagem = in.readLine()) != null) {
                    System.out.println("Mensagem do servidor: " + mensagem);
                }
            } catch (IOException e) {
                System.out.println("Erro ao receber mensagem: " + e.getMessage());
            }
        }
    }
}
