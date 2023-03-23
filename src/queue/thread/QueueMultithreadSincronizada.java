package queue.thread;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMultithreadSincronizada {
    public static void main(String[] args) {
    	
        Queue<String> filaMensagens = new LinkedList<>();

        Thread produtora = new Thread(() -> {
        	
            synchronized (filaMensagens) {
                for (int i = 1; i <= 5; i++) {
                    String mensagem = "Mensagem " + i;
                    filaMensagens.offer(mensagem);
                    System.out.println("Adicionou Mensagem: " + mensagem);
                    filaMensagens.notify(); 
                    try {
                        filaMensagens.wait(); 
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                filaMensagens.notify(); 
            }
        });

        Thread consumidora = new Thread(() -> {
        	
            synchronized (filaMensagens) {
                while (true) {
                    while (filaMensagens.isEmpty()) {
                        try {
                            filaMensagens.wait(); 
                            Thread.sleep(2000); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String mensagem = filaMensagens.poll();
                    System.out.println("Processou Mensagem: " + mensagem);
                    filaMensagens.notify(); 
                }
            }
        });

        produtora.start();
        consumidora.start();
    }
}

