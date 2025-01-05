package main;

import model.Video;
import repository.VideoRepositoryImpl;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;
import util.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoServiceImpl videoService = new VideoServiceImpl(new VideoRepositoryImpl("videos.txt"));
        SearchStrategy searchStrategy = new SearchStrategyImpl();
        Menu menu = new Menu(videoService, searchStrategy, scanner);

        while (true) {
            int opcao = menu.exibirMenu();

                switch (opcao) {
                    case 1:
                        adicionarVideo(menu);
                        break;

                    case 2:
                        listarVideos(videoService);
                        break;

                    case 3:
                       pesquisarTitulo(videoService, searchStrategy, scanner);
                        break;

                    case 9:
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }

        }

    private static void adicionarVideo(Menu menu) {
        menu.adicionarVideo();
    }

    private static void listarVideos(VideoServiceImpl videoService) {
        List<Video> videos = videoService.listVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    private static void pesquisarTitulo(VideoServiceImpl videoService, SearchStrategy searchStrategy, Scanner scanner) {
        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty())
        { System.out.println("Nenhum vídeo com esse título foi encontrado.");
        } for (Video video : resultados) {
            System.out.println(video);
        }
    }
    }