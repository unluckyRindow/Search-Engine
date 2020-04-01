package search;

import search.strategy.AllSearch;
import search.strategy.AnySearch;
import search.strategy.NoneSearch;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    private Data data;
    Scanner scanner;


    public Data getData(){
        return data;
    }

    public void setData(Data data){
        this.data = data;
    }

    Menu(String filename, String columnSeparator){
        scanner = new Scanner(System.in);
        try (BufferedReader bf = new BufferedReader(new FileReader(filename))){
            data = new Data(bf, columnSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        mainLoop: while(true){
            System.out.println("\n=== Menu ====\n" +
                    "1. Find a record\n" +
                    "2. Print all records\n" +
                    "3. Search instruction\n" +
                    "0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 0:
                    break mainLoop;
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();

                    switch (strategy){
                        case "ALL":
                            getData().setSearchStrategy(new AllSearch());
                            break;
                        case "ANY":
                            getData().setSearchStrategy(new AnySearch());
                            break;
                        case "NONE":
                            getData().setSearchStrategy(new NoneSearch());
                            break;
                        default:
                            System.out.println("Unrecognized choice.");
                            continue mainLoop;
                    }

                    System.out.println("Enter a keywords separated by space.");
                    Set<String> output = getData().search(scanner.nextLine());

                    if(!output.isEmpty()){
                        System.out.println(output.size() + " records found:");
                        output.forEach(System.out::println);
                    } else {
                        System.out.println("No matching records found.");
                    }
                    break;
                case 2:
                    System.out.println("\n=== Data ===");
                    System.out.println(data);
                    break;
                case 3:
                    System.out.println("ALL -- lines that contains all entered keywords\n" +
                                        "ANY -- lines that contain at lest one entered keyword\n" +
                                        "NONE -- lines that don't contain any of entered keywords\n");
            }
        }
    }
}
