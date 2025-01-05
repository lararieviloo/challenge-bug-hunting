package util;

import model.Video;
import service.VideoService;
import strategy.SearchStrategy;

 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Scanner;

public class Menu {
    private VideoService videoService;
    private SearchStrategy searchStrategy;
    private Scanner scanner;

    public Menu(VideoService videoService, SearchStrategy searchStrategy, Scanner scanner) {
        this.videoService = videoService;
        this.searchStrategy = searchStrategy;
        this.scanner = scanner;
    }

    public int exibirMenu() {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Editar vídeo");
            System.out.println("5. Excluir vídeo");
            System.out.println("6. Filtrar vídeos por categoria");
            System.out.println("7. Ordenar vídeos por data de publicação");
            System.out.println("8. Relatório de estatísticas");
            System.out.println("9. Sair");
            return scanner.nextInt();
        }

    public void adicionarVideo() {
        scanner.nextLine();

        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();
        int duracao;
        while (true) {
            System.out.print("Digite a duração do vídeo (em minutos): ");
            String duracaoStr = scanner.nextLine();
            try {
                duracao = Integer.parseInt(duracaoStr);
                if (duracao > 0) {
                    break;
                } else {
                    System.out.println("A duração deve ser um número positivo!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Insira apenas números para a duração, por favor.");
            }
        }
        System.out.print("Digite a categoria do vídeo: ");
        String categoria = scanner.nextLine();

        Date dataPublicacao;
        SimpleDateFormat dateFormat = new SimpleDateFormat ("ss/MM/yyyy");
        dateFormat.setLenient(false);
        while (true) {
            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            try {
                dataPublicacao = dateFormat.parse(dataStr);
                break;
            } catch (ParseException e) {
                System.out.println("Por favor, insira uma data válida no formato dd/MM/yyyy");
            }
        }

        Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
        VideoService.adicionarVideo(video);

        System.out.println("\nVídeo adicionado com sucesso! Dados do vídeo:");
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("Categoria: " + categoria);
        System.out.println("Data de Publicação: " + dateFormat.format(dataPublicacao));
    }
}