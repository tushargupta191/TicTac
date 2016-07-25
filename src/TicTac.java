import java.io.PrintStream;

/**
 * Created by tushar.gu on 22/07/16.
 */
public class TicTac {

    char  arr[][] = new char[3][3];
    String player1;
    String player2;
    Boolean bool;
    int row;
    int col;
    int playerWon = 0;
    int currentPlayer;

    char currentChar;

    TicTac(){

        bool = false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                int val = (i*3 + j + 1);
                arr[i][j] = (char)(val + '0');
            }
        }
    }

    public void setPlayer1(String s1){
        player1 = s1;
    }

    public void setPlayer2(String s2){
        player2 = s2;
    }

    boolean isValid  =false;

    public void addSymbolPlayer(int num, char ch, PrintStream printStream){

        char notCh;

        if(num > 9 || num < 1){
            isValid = false;
            printStream.println("Enter a valid number between 1-9");
        }
        else {
            if (ch == 'X') {
                notCh = 'O';
                currentPlayer = 1;
            } else {
                notCh = 'X';
                currentPlayer = 2;
            }

            row = (num - 1) / 3;
            col = (num - 1) % 3;

            if (arr[row][col] == notCh) {
                isValid = false;
                printStream.println("Box already occupied. Try Again");

            } else {
                arr[row][col] = ch;
                isValid = true;
            }
        }
        currentChar = ch;
    }



    public void display(PrintStream printStream){
        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                printStream.print("[" + arr[i][j] + "]");
            }
            printStream.println();
        }
    }


    public boolean hasPlayerWonBool(){
        return bool;
    }

    public void print(PrintStream printStream){
            printStream.println("Game Over. Player " + playerWon + " wins");
    }

    public void hasPlayerWon(){


            boolean row_match = (arr[row][0] == currentChar);

            for(int i=1; i<3;i++){
                row_match = row_match && (arr[row][i]== currentChar);
            }

            boolean col_match = (arr[0][col] == currentChar);

            for(int i=1; i<3; i++){
                col_match = col_match && (arr[i][col] == currentChar);
            }

            boolean matrix_match = (arr[0][0] == currentChar);
            for(int i=1;i<3;i++){
                matrix_match = matrix_match && (arr[i][i] == currentChar);
            }

            if(row_match|| col_match|| matrix_match ){
                bool = true;
                playerWon = currentPlayer;
            }

            else{
                bool = false;
            }
    }


}
