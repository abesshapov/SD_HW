import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Класс, реализующий функционал, требующийся для извлечения зависимостей.
 */
public class RequirementsManagement {
    /**
     * Получение полного расположения файла, от которого зависит другой файл.
     * @param rootDirectory корневая директория.
     * @param requirement расположение файла, от которого зависит другой файл, начиная от
     *                    корневой директории.
     * @return полное расположение файла.
     */
    public static String getFullRequirementPath(String rootDirectory,
                                                String requirement) {
        rootDirectory = rootDirectory.replace('/', '\\');
        requirement = requirement.replace('/', '\\');
        if (rootDirectory.endsWith("\\")) {
            return rootDirectory + requirement;
        } else {
            return rootDirectory + "\\" + requirement;
        }
    }

    /**
     * Извлечение из файла всех зависимостей.
     * @param rootDirectory корневая директория.
     * @param file файл, в котором ищутся зависимости.
     * @return список всех зависимостей.
     */
    public static ArrayList<String> getAllTxtFileRequirements(String rootDirectory,
                                                              File file) {
        ArrayList<String> requirements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("require")) {
                    requirements.add(getFullRequirementPath(rootDirectory,
                            line.split("\\s+")[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file " +
                    file.getPath() + ".");
        } catch (Exception e) {
            System.out.println("Unhandled error occurred while reading the file " +
                    file.getPath() + ".");
        }
        return requirements;
    }

    /**
     * Извлечение зависимостей для всех файлов в корневой директории.
     * @param directory корневая директория.
     * @param files список файлов в корневой директории.
     * @return словарь зависимостей для всех файлов в корневой директории.
     */
    public static HashMap<String, ArrayList<String>> getAllTxtFilesRequirements(String
                                                                                        directory,
                                                                                ArrayList<File>
                                                                                        files) {
        HashMap<String, ArrayList<String>> allTxtFilesRequirements = new HashMap<>();
        for (File txtFile : files) {
            ArrayList<String> requirements = getAllTxtFileRequirements(directory, txtFile);
            allTxtFilesRequirements.put(txtFile.getPath().replace("/", "\\"),
                    requirements);
        }
        return allTxtFilesRequirements;
    }

    /**
     * Нахождение всех зависимостей любого уровня вложенности для файла.
     * @param file файл, для которого ищутся зависимости любого уровня вложенности.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return множество зависимостей всех уровней вложенности для файла.
     */
    public static HashSet<String> getAllParentalRequirements(String file,
                                                             HashMap<String, ArrayList<String>>
                                                                     requirements) {
        HashSet<String> allRequirements = new HashSet<>();
        for (String requirement : requirements.get(file)) {
            if (requirements.containsKey(requirement)) {
                allRequirements.add(requirement);
                HashSet<String> requirementRequirements =
                        getAllParentalRequirements(requirement, requirements);
                allRequirements.addAll(requirementRequirements);
            }
        }
        return allRequirements;
    }

    /**
     * Нахождение всех зависимостей любого уровня вложенности для всех файлов в корневой
     * директории.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return словарь зависимостей всех уровней вложенности для всех файлов в
     * корневой директории.
     */
    public static HashMap<String, HashSet<String>> getAllFilesParentalRequirements(HashMap<String,
            ArrayList<String>> requirements) {
        HashMap<String, HashSet<String>> allParentalRequirements = new HashMap<>();
        for (String file : requirements.keySet()) {
            HashSet<String> parentalRequirements =
                    getAllParentalRequirements(file, requirements);
            allParentalRequirements.put(file, parentalRequirements);
        }
        return allParentalRequirements;
    }

    /**
     * Определение наличия циклической зависимости для файла.
     * @param file файл, который проверяется на наличие циклической зависимости.
     * @param requirement файл, от которого рассматриваемый файл зависит.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     * @return есть ли циклическая зависимость у файла.
     */
    public static boolean isCyclicalDependencePresentForFile(String file,
                                                             String requirement,
                                                             HashMap<String, ArrayList<String>>
                                                                     requirements) {
        if (!requirements.containsKey(requirement)) {
            return false;
        }
        if (requirements.get(requirement).contains(file)) {
            return true;
        }
        boolean cyclicalDependenceExists = false;
        for (String requirementForRequirement : requirements.get(requirement)) {
            cyclicalDependenceExists |= isCyclicalDependencePresentForFile(file,
                    requirementForRequirement,
                    requirements);
            if (cyclicalDependenceExists) {
                return true;
            }
        }
        return false;
    }

    /**
     * Определение наличия циклической зависимости между файлами.
     * @param requirements словарь зависимостей для всех файлов в
     *                     корневой директории.
     * @return есть ли циклическая зависимость между файлами.
     */
    public static boolean isCyclicalDependencePresent(HashMap<String,
            ArrayList<String>> requirements) {
        boolean cyclicalDependenceExists = false;
        for (String file : requirements.keySet()) {
            for (String requirement : requirements.get(file)) {
                cyclicalDependenceExists |= isCyclicalDependencePresentForFile(file,
                        requirement, requirements);
                if (cyclicalDependenceExists) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Вывод найденных зависимостей для всех текстовых файлов.
     * @param requirements словарь зависимостей для всех файлов в корневой директории.
     */
    public static void writeFoundRequirements(HashMap<String, ArrayList<String>>
                                                      requirements) {
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
}