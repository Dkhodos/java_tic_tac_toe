import java.util.Objects;

public class BoardNode{
    private final int i;
    private final int j;
    private BoardPlayer player= BoardPlayer.EMPTY;
    public BoardNode(int i, int j){
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getSymbol(){
        return this.player.getValue();
    }

    public boolean isEmpty(){
        return player == BoardPlayer.EMPTY;
    }

    public BoardPlayer getPlayer(){
        return player;
    }

    public void setState(BoardPlayer state){
        this.player = state;
    }

    public void clear(){
        player = BoardPlayer.EMPTY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}