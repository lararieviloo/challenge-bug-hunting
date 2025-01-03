package util;

 import service.VideoManager;
 import service.VideoService;
 import strategy.SearchStrategy;
 import java.util.Scanner;

public class Menu {

    VideoManager videoManager = new VideoManager();
    FileHandler fileHandler = new FileHandler();

    public int exibirMenu(Scanner scanner) {
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
        int opcao = scanner.nextInt();

        return opcao;
    }
}
