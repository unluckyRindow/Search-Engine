package search;

public class App {
    public static void main(String[] args) {

        // provide program arguments: path_to_file column_separator
        // e.g.: Users/username/data/user_data.csv ;
        new Menu(args[0], args[1]).run();
    }
}
