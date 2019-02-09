import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;

public class FileServiceTest {

    @Test
    public void isFileAcceptableTest(){
        FileService fileService = new FileService();

        FileInput fileInputAcceptable = new FileInput("doc");
        Assertions.assertTrue(fileService.isFileAcceptable(fileInputAcceptable));

        FileInput fileInputNotAcceptable = new FileInput("jar");
        Assertions.assertFalse(fileService.isFileAcceptable(fileInputNotAcceptable));
    }

    @Test
    public void verifyMagicNumbersTest(){
        FileService fileService = new FileService();
        InputStream input = FileService.class.getResourceAsStream("/test.jpg");
        DataInput dataInput = new DataInputStream(input);

        FileInput fileInputJpg = new FileInput("jpg");
        fileInputJpg.setMagicNumbers(FileExtensions.MAGIC_NUMBER.getMagicNumbers(fileInputJpg.getExtension()));

        Assertions.assertTrue(fileService.verifyMagicNumbers(fileInputJpg, dataInput));
    }
}
