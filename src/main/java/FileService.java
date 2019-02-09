import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileService {

    private FileInput fileInput;
    private static final Logger LOGGER = Logger.getLogger( FileService.class.getName());

    public boolean readFile(String fileName){
        InputStream input = FileService.class.getResourceAsStream("/" + fileName);
        DataInput dataInput = null;
        if(input!= null) {
            dataInput = new DataInputStream(input);
        } else {
           LOGGER.log(Level.SEVERE, "There is no such file. Please add file to read");
           return false;
        }

        String fileExtension = StringUtils.substringAfter(fileName,".");

        if(fileExtension.equals("txt")){
            return true;
        }

        this.fileInput = new FileInput(fileExtension);

        if(!isFileAcceptable(this.fileInput)){
            LOGGER.log( Level.INFO, "File with extension "
                    + this.fileInput.getExtension() + " is not acceptable. " +
                    "Please chose file with one of acceptable extensions: " +
                    Arrays.asList(Arrays.stream(FileExtensions.values()).skip(1).collect(Collectors.toList())));
            return false;
        }

        fileInput.setMagicNumbers(FileExtensions.MAGIC_NUMBER.getMagicNumbers(this.fileInput.getExtension()));

        if(!verifyMagicNumbers(fileInput, dataInput)) {
            LOGGER.log(Level.SEVERE, "File extension is not " + this.fileInput.getExtension());
            return false;
        }

        return true;
    }

    public boolean isFileAcceptable(FileInput fileInput){
        return Arrays.stream(FileExtensions.values())
                .anyMatch((extension) -> extension.name().equalsIgnoreCase(fileInput.getExtension()));
    }

    public boolean verifyMagicNumbers(FileInput fileInput, DataInput dataInput) {
        byte[] magicNumbers = fileInput.getMagicNumbers();
        try{
            for(int i = 0; i < magicNumbers.length; i++){
                if(dataInput.readByte() != magicNumbers[i]) {
                    return false;
                }
            }
        } catch (IOException exception){
            LOGGER.log(Level.SEVERE, exception.getMessage() + exception);
        }

        return true;
    }
}
