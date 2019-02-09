public class FileInput {

    private String extension;
    private byte[] magicNumbers;

    public FileInput(String extension){
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getMagicNumbers() {
        return magicNumbers;
    }

    public void setMagicNumbers(byte[] magicNumbers) {
        this.magicNumbers = magicNumbers;
    }
}
