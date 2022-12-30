import java.io.*;
import java.util.*;

public class Main {
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
     * Нахождение файлов в директории, удовлетворяющих переданному фильтру.
     * @param directory директория, в которой происходит поиск файлов.
     * @param filter фильтр для поиска.
     * @return массив файлов в директории, удовлетворяющих переданному фильтру.
     */
    public static File[] getAppropriateFiles(String directory, FilenameFilter filter) {
        File currentDirectory = new File(directory);
        return currentDirectory.listFiles(filter);
    }

    /**
     * Нахождение всех папок в директории.
     * @param directory директория, в которой происходит поиск папок.
     * @return массив папок в директории.
     */
    public static File[] getFolders(String directory) {
        FilenameFilter filter = (currentDirectory, name) -> new File(currentDirectory, name).isDirectory();
        return getAppropriateFiles(directory, filter);
    }

    /**
     * Нахождение всех текстовых файлов в директории.
     * @param directory директория, в которой происходит поиск.
     * @return массив текстовых файлов в директории.
     */
    public static File[] getTxtFiles(String directory) {
        FilenameFilter filter = (currentDirectory, name) -> name.toLowerCase().endsWith(".txt");
        return getAppropriateFiles(directory, filter);
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
            }
            catch (java.util.NoSuchElementException e) {
                System.out.println("Empty string was entered. Input process is terminated.");
                return "";
            }
            catch (Exception e) {
                System.out.println("Error occurred while entering directory.");
            }
        } while (isRootDirectoryNonExistent(directory));
        return directory;
    }

    /**
     * Поиск всех текстовых файлов в директории и в дочерних папках директории.
     * @param directory текущая директория.
     * @return список всех текстовых файлов в директории и в дочерних папках директории.
     */
    public static ArrayList<File> getAllTxtFilesInDirectory(String directory) {
        ArrayList<File> allTxtFiles = new ArrayList<>();
        File[] txtFiles = getTxtFiles(directory);
        if (txtFiles != null) {
            Collections.addAll(allTxtFiles, txtFiles);
        }
        File[] folders = getFolders(directory);
        if (folders != null) {
            for (File folder : folders) {
                ArrayList<File> folderTxtFiles = getAllTxtFilesInDirectory(folder.getPath());
                allTxtFiles.addAll(folderTxtFiles);
            }
        }
        return allTxtFiles;
    }

    /**
     * Извлечение из файла всех зависимостей.
     * @param rootDirectory корневая директория.
     * @param file файл, в котором ищутся зависимости.
     * @return список всех зависимостей.
     */
    public static ArrayList<String> getAllTxtFileRequirements(String rootDirectory, File file) {
        ArrayList<String> requirements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("require")) {
                    requirements.add(getFullRequirementPath(rootDirectory, line.split("\\s+")[1]));
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error occurred while reading the file " + file.getPath() + ".");
        }
        catch (Exception e) {
            System.out.println("Unhandled error occurred while reading the file " + file.getPath() + ".");
        }
        return requirements;
    }

    /**
     * Получение полного расположения файла, от которого зависит другой файл.
     * @param rootDirectory корневая директория.
     * @param requirement расположение файла, от которого зависит другой файл, начиная от корневой директории.
     * @return полное расположение файла.
     */
    public static String getFullRequirementPath(String rootDirectory, String requirement) {
        rootDirectory = rootDirectory.replace('/', '\\');
        requirement = requirement.replace('/', '\\');
        if (rootDirectory.endsWith("\\")) {
            return rootDirectory + requirement;
        } else {
            return rootDirectory + "\\" + requirement;
        }
    }

    /**
     * Получение полного пути до файла с результатом конкатенации.
     * @param rootDirectory корневая директория.
     * @return полный путь до файла с результатом конкатенации.
     */
    public static String getFullConcatenationFilePath(String rootDirectory) {
        rootDirectory = rootDirectory.replace('/', '\\');
        if (rootDirectory.endsWith("\\")) {
            return rootDirectory + "concatenation.txt";
        } else {
            return rootDirectory + "\\concatenation.txt";
        }
    }

    /**
     * Определение наличия циклической зависимости между файлами.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return есть ли циклическая зависимость между файлами.
     */
    public static boolean isCyclicalDependencePresent(HashMap<String, ArrayList<String>> requirements) {
        boolean cyclicalDependenceExists = false;
        for (String file : requirements.keySet()) {
            for (String requirement : requirements.get(file)) {
                cyclicalDependenceExists |= isCyclicalDependencePresentForFile(file, requirement, requirements);
                if (cyclicalDependenceExists) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Определение наличия циклической зависимости для файла.
     * @param file файл, который проверяется на наличие циклической зависимости.
     * @param requirement файл, от которого рассматриваемый файл зависит.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return есть ли циклическая зависимость у файла.
     */
    public static boolean isCyclicalDependencePresentForFile(String file, String requirement, HashMap<String, ArrayList<String>> requirements) {
        if (!requirements.containsKey(requirement)) {
            return false;
        }
        if (requirements.get(requirement).contains(file)) {
            return true;
        }
        boolean cyclicalDependenceExists = false;
        for (String requirementForRequirement : requirements.get(requirement)) {
            cyclicalDependenceExists |= isCyclicalDependencePresentForFile(file, requirementForRequirement, requirements);
            if (cyclicalDependenceExists) {
                return true;
            }
        }
        return false;
    }

    /**
     * Извлечение зависимостей для всех файлов в корневой директории.
     * @param rootDirectory корневая директория.
     * @param txtFiles список файлов в корневой директории.
     * @return словарь зависимостей для всех файлов в корневой директории.
     */
    public static HashMap<String, ArrayList<String>> getAllTxtFilesRequirements(String rootDirectory, ArrayList<File> txtFiles) {
        HashMap<String, ArrayList<String>> allTxtFilesRequirements = new HashMap<>();
        for (File txtFile : txtFiles) {
            ArrayList<String> requirements = getAllTxtFileRequirements(rootDirectory, txtFile);
            allTxtFilesRequirements.put(txtFile.getPath().replace("/", "\\"), requirements);
        }
        return allTxtFilesRequirements;
    }

    /**
     * Нахождение всех зависимостей любого уровня вложенности для файла.
     * @param file файл, для которого ищутся зависимости любого уровня вложенности.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return множество зависимостей всех уровней вложенности для файла.
     */
    public static HashSet<String> getAllParentalRequirements(String file, HashMap<String, ArrayList<String>> requirements) {
        HashSet<String> allRequirements = new HashSet<>();
        for (String requirement : requirements.get(file)) {
            if (requirements.containsKey(requirement)) {
                allRequirements.add(requirement);
                HashSet<String> requirementRequirements = getAllParentalRequirements(requirement, requirements);
                allRequirements.addAll(requirementRequirements);
            }
        }
        return allRequirements;
    }

    /**
     * Нахождение всех зависимостей любого уровня вложенности для всех файлов в корневой директории.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return словарь зависимостей всех уровней вложенности для всех файлов в корневой директории.
     */
    public static HashMap<String, HashSet<String>> getAllFilesParentalRequirements(HashMap<String, ArrayList<String>> requirements) {
        HashMap<String, HashSet<String>> allParentalRequirements = new HashMap<>();
        for (String file : requirements.keySet()) {
            HashSet<String> parentalRequirements = getAllParentalRequirements(file, requirements);
            allParentalRequirements.put(file, parentalRequirements);
        }
        return allParentalRequirements;
    }

    /**
     * Сортировка файлов по указанному в условии задачи правилу.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return отсортированный список файлов.
     */
    public static ArrayList<String> sortFiles(HashMap<String, HashSet<String>> requirements) {
        HashMap<Integer, ArrayList<String>> requirementsAmount = new HashMap<>();
        for (String file : requirements.keySet()) {
            if (!requirementsAmount.containsKey(requirements.get(file).size())) {
                requirementsAmount.put(requirements.get(file).size(), new ArrayList<>(Collections.singletonList(file)));
            } else {
                ArrayList<String> fixedSizeRequirements = requirementsAmount.get(requirements.get(file).size());
                fixedSizeRequirements.add(file);
                requirementsAmount.put(requirements.get(file).size(), fixedSizeRequirements);
            }
        }
        TreeMap<Integer, ArrayList<String>> sortedRequirementsAmount = new TreeMap<>(requirementsAmount);
        ArrayList<String> sortedFiles = new ArrayList<>();
        for (Integer amount : sortedRequirementsAmount.keySet()) {
            sortedFiles.addAll(sortedRequirementsAmount.get(amount));
        }
        return sortedFiles;
    }

    /**
     * Вывод найденных в корневой директории и ее дочерних директориях текстовых файлов.
     * @param txtFiles список текстовых файлов.
     */
    public static void writeFoundFiles(ArrayList<File> txtFiles) {
        System.out.println("Found txt files:");
        for (File file : txtFiles) {
            System.out.println(file.getPath());
        }
    }

    /**
     * Вывод найденных зависимостей для всех текстовых файлов.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     */
    public static void writeFoundRequirements(HashMap<String, ArrayList<String>> requirements) {
        System.out.println("Found requirements:");
        for (String file : requirements.keySet()) {
            System.out.print(file + " requires: ");
            if (requirements.get(file).isEmpty()) {
                System.out.print("nothing;");
            }
            for (String requirement : requirements.get(file)) {
                System.out.print(requirement + "; ");
            }
            System.out.println();
        }
    }

    /**
     * Вывод отсортированного списка файлов.
     * @param sortedFiles отсортированный список файлов.
     */
    public static void writeSortedFilesList(ArrayList<String> sortedFiles) {
        System.out.println("Sorted files list:");
        for (String file : sortedFiles) {
            System.out.println(file);
        }
    }

    /**
     * Вывод конкатенации содержимого файлов, запись конкатенации в новый файл.
     * @param rootDirectory корневая директория.
     * @param sortedFiles отсортированный список файлов.
     */
    public static void writeFilesConcatenation(String rootDirectory, ArrayList<String> sortedFiles) {
        System.out.println("Concatenation of found files:");
        ArrayList<String> allFilesLines = new ArrayList<>();
        for (String file : sortedFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    allFilesLines.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error occurred while reading the file " + file + ".");
            }
            catch (Exception e) {
                System.out.println("Unhandled error occurred while reading the file " + file + ".");
            }
        }
        try (PrintWriter writer = new PrintWriter(getFullConcatenationFilePath(rootDirectory))) {
            for (String line : allFilesLines) {
                writer.println(line);
            }
            writer.println("----------");
        } catch (IOException e) {
            System.out.println("Error occurred while writing the concatenation file.");
        }
        catch (Exception e) {
            System.out.println("Unhandled error occurred while writing the concatenation file.");
        }
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
        ArrayList<File> txtFiles = getAllTxtFilesInDirectory(rootDirectory);
        writeFoundFiles(txtFiles);
        HashMap<String, ArrayList<String>> allRequirements = getAllTxtFilesRequirements(rootDirectory, txtFiles);
        writeFoundRequirements(allRequirements);
        if (isCyclicalDependencePresent(allRequirements)) {
            System.out.println("Cyclical dependence was detected. Concatenation and list creation is impossible.");
            return;
        }
        HashMap<String, HashSet<String>> allParentalRequirements = getAllFilesParentalRequirements(allRequirements);
        ArrayList<String> sortedFiles = sortFiles(allParentalRequirements);
        writeSortedFilesList(sortedFiles);
        writeFilesConcatenation(rootDirectory, sortedFiles);
    }

    /**
     * Точка входа в программу.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        userInteraction();
    }
}