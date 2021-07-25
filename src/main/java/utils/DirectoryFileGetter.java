package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryFileGetter {
    private static DirectoryFileGetter uniqueInstance;
    private static List<File> filesInFolder;

    public static synchronized DirectoryFileGetter getUniqueInstance() {

        if (uniqueInstance == null) {
            uniqueInstance = new DirectoryFileGetter();
        }
        return uniqueInstance;
    }
    public static List<File> getFileNames(String path) {
        getDirectoryFiles(path);
        return filesInFolder;
    }

    private static void getDirectoryFiles(String path) {
        try {
            filesInFolder = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
