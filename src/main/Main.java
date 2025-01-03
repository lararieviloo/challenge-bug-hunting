package main;

import model.Video;
import repository.VideoRepositoryImpl;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;
import util.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoServiceImpl videoService = new VideoServiceImpl(new VideoRepositoryImpl("videos.txt"));
        SearchStrategy searchStrategy = new SearchStrategyImpl();
        Menu menu = new Menu();

        while (true) {
            int opcao = menu.exibirMenu(scanner);

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o título do vídeo: ");
                        String titulo = scanner.nextLine();
                        scanner.nextLine();
                        System.out.print("Digite a descrição do vídeo: ");
                        String descricao = scanner.nextLine();
                        int duracao = 0;
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
                                System.out.println("Por favor, insira apenas números para a duração.");
                            }
                        }
                        System.out.print("Digite a categoria do vídeo: ");
                        String categoria = scanner.nextLine();

                        Date dataPublicacao = null;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        dateFormat.setLenient(false);
                        while (true) {
                            System.out.println("Digite a data de publicação (dd/MM/yyyy)");
                            String dataStr = scanner.nextLine();
                            try {
                                dataPublicacao = dateFormat.parse(dataStr);
                                break;
                            } catch (ParseException e) {
                                System.out.println("Por favor, insira uma data válida no formato dd/MM/yyyy.");
                            }
                        }

                        System.out.println("\nVídeo adicionado! Dados do vídeo:");
                        System.out.println("Título: " + titulo);
                        System.out.println("Descrição: " + descricao);
                        System.out.println("Duração: " + duracao + " minutos");
                        System.out.println("Categoria: " + categoria);
                        System.out.println("Data de Publicação: " + dateFormat.format(dataPublicacao));

                    case 2:
                        List<Video> videos = videoService.listVideos();
                        for (Video video : videos) {
                            System.out.println(video);
                            System.out.println("Título: " + video.getTitulo());
                            System.out.println("Descrição: " + video.getDescricao());
                            System.out.println("Categoria: " + video.getCategoria());
                            System.out.println("Duração: " + video.getDuracao());
                            System.out.println("Data da publicação: " + video.getDataPublicacao());
                        }
                        break;

                    case 3:
                        System.out.print("Digite o título para busca: ");
                        String query = scanner.nextLine();
                        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
                        for (Video video : resultados) {
                            System.out.println(video);
                        }
                        break;

                    case 9:
                        System.out.println("Saindo do sistema...");
                        scanner.close();

                    default:
                        System.out.println("Opção inválida.");
                }
            }

        }
    }