package tictactoeapplication;

public class TicTacToeApplication {
    protected char[] board;
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;

    public TicTacToeApplication(char userMarker, char aiMarker){
        this.userMarker= userMarker;
        this.aiMarker= aiMarker;
        this.winner= '-';
        this.board= setBoard();
    }

    public TicTacToeApplication() {
        this.board= new char[9];
    }


    private static char[] setBoard() {
        char[] board= new char[9];
        for(int i= 0 ; i< board.length; i++){
            board[i]= '-';
        }
        return board;
    }

    public boolean playTurn(int spot){
        boolean isValid= isWithinRange(spot) && isSpotTaken(spot);
        if(isValid){
            board[spot -1] = currentMarker;
            currentMarker= (currentMarker == userMarker) ? aiMarker: userMarker;
        }
        return isValid;
    }

    private boolean isSpotTaken(int spot) {
        return (board[spot] != '-');
    }

    private boolean isWithinRange(int spot) {
        return (spot-1 > 0 && spot-1 < board.length + 1);
    }

    public void printBoard(){

        for(int i = 0; i< this.board.length; i++){
            if(i != 0 && i % 3 == 0){
                System.out.println("\n---------------");
            }
            System.out.print(board[i] + " | ");
        }
        System.out.println();
    }

    public void printIndexBoard(){
        for(int i = 0; i< this.board.length; i++){
            if(i != 0 && i % 3 == 0){
                System.out.println("\n---------------");
            }
            System.out.print((i+ 1) + " | ");
        }
        System.out.println();
    }

    public Boolean isThereAWinner(){
        boolean diagonalsAndMiddle= rightDiagonal() || leftDiagonal() || middleRow() || secondCol() && board[4] != '-';
        boolean topAndFirstCol= topRow() || firstCol() && board[0] != '-';
        boolean bottomAndThirdCol= bottomRow() || thirdCol() && board[8] != '-';
        if(diagonalsAndMiddle){
            this.winner= board[4];
        }else if(topAndFirstCol){
            this.winner= board[0];
        }else if(bottomAndThirdCol){
            this.winner= board[8];
        }
         return diagonalsAndMiddle || topAndFirstCol || bottomAndThirdCol;
    }

    private boolean thirdCol() {
        return !(board[2] ==  board[5] && board[5] == board[8]);
    }

    private boolean bottomRow() {
        return !(board[6] ==  board[7] && board[7] == board[8]);
    }

    private boolean firstCol() {
        return !(board[0] ==  board[3] && board[3] == board[6]);
    }

    private boolean topRow() {
        return !(board[0] ==  board[1] && board[1] == board[2]);
    }

    private boolean secondCol() {
        return !(board[1] ==  board[4] && board[4] == board[7]);
    }

    private boolean middleRow() {
        return !(board[3] ==  board[4] && board[4] == board[5]);
    }

    private boolean leftDiagonal() {
        return !(board[0] ==  board[4] && board[4] == board[8]);
    }

    private boolean rightDiagonal() {
        return board[2] == board[4]  &&  board[4] == board[6];
    }

    public boolean isTheBoardFilled(){
        boolean isFilled= true;
        for(int i =0; i< board.length; i++){
            if(board[i] == '-'){
                isFilled= false;
            }
        }
        return isFilled;
    }

    public String gameOver(){
        if(isThereAWinner()){
            return "there is a winner. the winner is "+ this.winner ;
        }else if(isTheBoardFilled()){
            return "the board is filled. game is over. start with a clean board";
        }
        return "the game is not over";
    }


    public static void main(String[] args) {
        TicTacToeApplication app = new TicTacToeApplication();
        app.printBoard();
        app.printIndexBoard();
    }

}
