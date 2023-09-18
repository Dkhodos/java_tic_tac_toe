public enum BoardPlayer {
    USER("X"), AI("O"), EMPTY("_");

    private final String value;

    BoardPlayer(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}