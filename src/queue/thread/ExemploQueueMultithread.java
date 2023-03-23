package queue.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ExemploQueueMultithread {
    public static void main(String[] args) {
        // Cria uma Queue compartilhada entre as threads
        Queue<String> filaMensagens = new LinkedList<>();

        // Cria a thread produtora
        Thread produtora = new Thread(() -> {
            // Produz algumas mensagens e as adiciona na fila
            for (int i = 1; i <= 5; i++) {
                String mensagem = "Mensagem " + i;
                filaMensagens.offer(mensagem);
                System.out.println("Adicionou Mensagem: " + mensagem);
            }
        });

        // Cria a thread consumidora
        Thread consumidora = new Thread(() -> {
            // Consome as mensagens da fila
            while (true) {
                if (!filaMensagens.isEmpty()) {
                    String mensagem = filaMensagens.poll();
                    System.out.println("Processou Mensagem: " + mensagem);
                }
            }
        });

        // Inicia as threads
        produtora.start();
        consumidora.start();
    }
}

