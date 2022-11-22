import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String output = "outputs/output";
    static String output_sdk = "outputs/output_sdk";
    static String output_vm = "outputs/output_vm";
    public static void main(String[] args) throws IOException {
        clean_file_to_ci(output);
        clean_file_to_ci(output_sdk);
        clean_file_to_ci(output_vm);
    }

    public static void clean_file_to_ci(String filename) throws IOException {
        File myObj = new File(filename);
        Scanner scanner = new Scanner(myObj);
        FileWriter myWriter = new FileWriter(filename+"_cleaned");
        ArrayList<Command> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("FFFF")){
                myWriter.write(line+"\n");
            }
            if(line.contentEquals("Read") || line.contentEquals("Write")){
                ArrayList<String> CIS = new ArrayList<>();
                for(int i=0; i<8; i++){
                    CIS.add(scanner.nextLine());
                }
                Command command = new Command(line, CIS);
                if(commands.size()==0 || !commands.get(commands.size()-1).equals(command)) {
                    commands.add(command);
                    myWriter.write(command+"\n");
                }
            }
        }
        scanner.close();
        myWriter.close();
    }
}