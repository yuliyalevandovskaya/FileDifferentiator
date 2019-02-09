
public class Main {

    public static void main(String[] args) {
        FileService fileService = new FileService();
        System.out.println(fileService.readFile("test.jpg"));
    }
}
