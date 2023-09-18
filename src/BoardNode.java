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

    public boolean setState(BoardPlayer state){
        if(isEmpty()){
            this.player = state;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}