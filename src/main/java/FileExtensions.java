public enum FileExtensions {
    MAGIC_NUMBER,JPG,JPEG,GIF,PDF,DOC;

    private final byte[] jpg = new byte[] {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xDB};

    private final byte[] gif = new byte[] {0x47, 0x49, 0x46, 0x38};

    private final byte[] pdf = new byte[] {0x25, 0x50, 0x44, 0x46};

    private final byte[] doc = new byte[] {(byte)0xEC, (byte)0xA5, (byte)0xC1, (byte)0x00};

    public byte[] getMagicNumbers(String fileExtension) {
        byte [] magicNumbers = new byte[4];
        switch(fileExtension){
            case "JPG":
                magicNumbers = jpg;
                break;
            case "JPEG":
                magicNumbers = jpg;
            case "GIF":
                magicNumbers = gif;
                break;
            case "PDF":
                magicNumbers = pdf;
                break;
            default:
                return magicNumbers;
        }

        return magicNumbers;
    }
}
