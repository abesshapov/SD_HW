import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс, осуществляющий взаимодействие пользователя с программой.
 * Координирует выполнение программы по заданному условию задачи.
 */
public class UserInteraction {
    /**
     * Проверка существования введенной корневой директории.
     * @param directory введенная корневая директория.
     * @return существует ли введенная корневая директория.
     */
    public static boolean isRootDirectoryNonExistent(String directory) {
        File rootDirectory = new File(directory);
        return !rootDirectory.exists();
    }

    /**
     * Ввод пользователем корневой директории.
     * @param scanner считывающий ввод сканер.
     * @return введенная пользователем корневая директория.
     */
    public static String getRootDirectory(Scanner scanner) {
        String directory = "";
        do {
            try {
                System.out.print("Enter root directory: ");
                directory = scanner.nextLine();
                if (isRootDirectoryNonExistent(directory)) {
                    System.out.println("Entered root directory is non-existent.");
                }
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Empty string was entered. Input process is terminated.");
                return "";
            } catch (Exception e) {
                System.out.println("Error occurred while entering directory.");
            }
        } while (isRootDirectoryNonExistent(directory));
        return directory;
    }

    /**
     * Взаимодействие с пользователем и промежуточные вычисления с выводом.
     */
    public static void userInteraction() {
        Scanner scanner = new Scanner(System.in);
        String rootDirectory = getRootDirectory(scanner);
        scanner.close();
        if (rootDirectory.equals("")) {
            System.out.println("Program is closed.");
            return;
        }
        ArrayList<File> txtFiles = FilesManagement.getAllTxtFilesInDirectory(rootDirectory);
        FilesManagement.writeFoundFiles(txtFiles);
        HashMap<String, ArrayList<String>> allRequirements =
                RequirementsManagement.getAllTxtFilesRequirements(rootDirectory, txtFiles);
        RequirementsManagement.writeFoundRequirements(allRequirements);
        if (RequirementsManagement.isCyclicalDependencePresent(allRequirements)) {
            System.out.println("Cyclical dependence was detected. " +
                    "Concatenation and list creation is impossible.");
            return;
        }
        HashMap<String, HashSet<String>> allParentalRequirements =
                RequirementsManagement.getAllFilesParentalRequirements(allRequirements);
        ArrayList<String> sortedFiles = FilesManagement.sortFiles(allParentalRequirements);
        FilesManagement.writeSortedFilesList(sortedFiles);
        FilesManagement.writeFilesConcatenation(rootDirectory, sortedFiles);
    }
}