import java.io.*;
import java.util.*;

/**
 * Класс, реализующий функционал, требующийся для поиска и сортировки файлов.
 */
public class FilesManagement {
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
        FilenameFilter filter = (currentDirectory, name) ->
                new File(currentDirectory, name).isDirectory();
        return getAppropriateFiles(directory, filter);
    }

    /**
     * Нахождение всех текстовых файлов в директории.
     * @param directory директория, в которой происходит поиск.
     * @return массив текстовых файлов в директории.
     */
    public static File[] getTxtFiles(String directory) {
        FilenameFilter filter = (currentDirectory, name) ->
                name.toLowerCase().endsWith(".txt");
        return getAppropriateFiles(directory, filter);
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
                ArrayList<File> folderTxtFiles =
                        getAllTxtFilesInDirectory(folder.getPath());
                allTxtFiles.addAll(folderTxtFiles);
            }
        }
        return allTxtFiles;
    }

    /**
     * Сортировка файлов по указанному в условии задачи правилу.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return отсортированный список файлов.
     */
    public static ArrayList<String> sortFiles(HashMap<String, HashSet<String>>
                                                      requirements) {
        HashMap<Integer, ArrayList<String>> requirementsAmount = new HashMap<>();
        for (String file : requirements.keySet()) {
            if (!requirementsAmount.containsKey(requirements.get(file).size())) {
                requirementsAmount.put(requirements.get(file).size(),
                        new ArrayList<>(Collections.singletonList(file)));
            } else {
                ArrayList<String> fixedSizeRequirements =
                        requirementsAmount.get(requirements.get(file).size());
                fixedSizeRequirements.add(file);
                requirementsAmount.put(requirements.get(file).size(),
                        fixedSizeRequirements);
            }
        }
        TreeMap<Integer, ArrayList<String>> sortedRequirementsAmount =
                new TreeMap<>(requirementsAmount);
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
     * Вывод конкатенации содержимого файлов, запись конкатенации в новый файл.
     * @param rootDirectory корневая директория.
     * @param sortedFiles отсортированный список файлов.
     */
    public static void writeFilesConcatenation(String rootDirectory,
                                               ArrayList<String> sortedFiles) {
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
                System.out.println("Error occurred while reading the file " +
                        file + ".");
            } catch (Exception e) {
                System.out.println("Unhandled error occurred while reading the file " +
                        file + ".");
            }
        }
        try (PrintWriter writer =
                     new PrintWriter(getFullConcatenationFilePath(rootDirectory))) {
            for (String line : allFilesLines) {
                writer.println(line);
            }
            writer.println("----------");
        } catch (IOException e) {
            System.out.println("Error occurred while writing the concatenation file.");
        } catch (Exception e) {
            System.out.println("Unhandled error occurred while writing the " +
                    "concatenation file.");
        }
    }
}