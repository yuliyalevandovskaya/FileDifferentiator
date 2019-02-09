import org.apache.commons.lang3.StringUtils;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileService {

    private FileInput fileInput;
    private static final Logger LOGGER = Logger.getLogger( FileService.class.getName());

    public boolean readFile(String fileName){
        InputStream input = FileService.class.getResourceAsStream("/" + fileName);
        DataInput dataInput = new DataInputStream(input);

        String fileExtension = StringUtils.substringAfter(fileName,".");
        this.fileInput = new FileInput(fileExtension);

        if(!isFileAcceptable(this.fileInput)){
            LOGGER.log( Level.INFO, "File with extension "
                    + this.fileInput.getExtension() + " is not acceptable. " +
                    "Please chose file with one of acceptable extensions: " +
                    Arrays.asList(Arrays.stream(FileExtensions.values()).skip(1).collect(Collectors.toList())));
            return false;
        }

        fileInput.setMagicNumbers(FileExtensions.MAGIC_NUMBER.getMagicNumbers(this.fileInput.getExtension()));


        return false;
    }

    public boolean isFileAcceptable(FileInput fileInput){
        return Arrays.stream(FileExtensions.values())
                .anyMatch((extension) -> extension.name().equalsIgnoreCase(fileInput.getExtension()));
    }

    public boolean verifyMagicNumbers(FileInput fileInput, DataInput dataInput) throws IOException {
        byte[] magicNumbers = fileInput.getMagicNumbers();
        for(int i = 0; i < magicNumbers.length; i++){
            if(dataInput.readByte() != magicNumbers[i]) {
                return false;
            }
        }

        return true;
    }

}
