public enum FileExtensions {
    MAGIC_NUMBER,JPG;

    private final byte[] jpg = new byte[] {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xDB};

    public byte[] getMagicNumbers(String fileExtension) {
        byte [] magicNumbers = new byte[4];
        if(fileExtension.equalsIgnoreCase("JPG")){
            magicNumbers = jpg;
        }
        return magicNumbers;
    }
}
