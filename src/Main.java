import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 1, 10, 232);
        GameProgress game2 = new GameProgress(70, 1, 17, 431);
        GameProgress game3 = new GameProgress(33, 2, 26, 1002);

        saveGame("F://Games/savegames/save1.dat", game1);
        saveGame("F://Games/savegames/save2.dat", game2);
        saveGame("F://Games/savegames/save3.dat", game3);

        zipFiles("F://Games/savegames/zip.zip", List.of("F://Games/savegames/save1.dat", "F://Games/savegames/save2.dat", "F://Games/savegames/save3.dat"));

        for (String filePath : List.of("F://Games/savegames/save1.dat", "F://Games/savegames/save2.dat", "F://Games/savegames/save3.dat")) {
            new File(filePath).delete();

        }
    }
    public static void saveGame(String filePath, GameProgress gameProgress)  {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void zipFiles(String zipFilePath, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(new File(file).getName());
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

