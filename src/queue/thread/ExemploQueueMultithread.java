package queue.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ExemploQueueMultithread {
    public static void main(String[] args) {
    	
        Queue<String> filaMensagens = new LinkedList<>();

        Thread produtora = new Thread(() -> {
        	
            for (int i = 1; i <= 5; i++) {
                String mensagem = "Mensagem " + i;
                filaMensagens.offer(mensagem);
                System.out.println("Adicionou Mensagem: " + mensagem);
            }
        });

        Thread consumidora = new Thread(() -> {
        	
            while (true) {
                if (!filaMensagens.isEmpty()) {
                    String mensagem = filaMensagens.poll();
                    System.out.println("Processou Mensagem: " + mensagem);
                }
            }
        });

        produtora.start();
        consumidora.start();
    }
}

