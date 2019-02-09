import java.util.HashMap;
import java.util.Map;

public enum FileExtensions {
    MAGIC_NUMBER,JPG,JPEG,GIF,PDF,DOC;

    private Map<String, byte[]> magicNumbersMap = new HashMap<>();

    public Map<String, byte[]> getMagicNumbersMap(){
        magicNumbersMap.put("jpg", new byte[] {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE0});
        magicNumbersMap.put("jpeg", new byte[] {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE0});
        magicNumbersMap.put("gif", new byte[] {0x47, 0x49, 0x46, 0x38});
        magicNumbersMap.put("doc", new byte[] {(byte)0xEC, (byte)0xA5, (byte)0xC1, (byte)0x00});

        return this.magicNumbersMap;
    }

    public byte[] getMagicNumbers(String fileExtension) {
        byte [] magicNumbers = new byte[4];
        for(Map.Entry<String, byte[]> entry : getMagicNumbersMap().entrySet()){
            if(getMagicNumbersMap().containsKey(fileExtension)){
                return entry.getValue();
            }
        }
        return magicNumbers;
    }

}
