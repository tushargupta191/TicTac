import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tushar.gu on 25/07/16.
 */
public class Server extends TicTac {

    public static void main(String[] args){

        TicTac tc = new TicTac();

        try {
            ServerSocket myServerSocket = new ServerSocket(9999);


            Socket player1 = myServerSocket.accept();
            PrintStream myOutput_1 = new PrintStream(player1.getOutputStream());
            BufferedReader myInput_1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            myOutput_1.print("Enter your name ");
            String name_1 = myInput_1.readLine();
            tc.setPlayer1(name_1);
            myOutput_1.println("Welcome " + name_1);


            Socket player2 = myServerSocket.accept();
            PrintStream myOutput_2 = new PrintStream(player2.getOutputStream());
            BufferedReader myInput_2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            myOutput_2.print("Enter your name ");
            String name_2 = myInput_2.readLine();
            tc.setPlayer2(name_2);
            myOutput_2.println("Welcome "+name_2);


            tc.display(myOutput_1);
            tc.display(myOutput_2);

            int var = 0;
            int index;
            int count = 0;

            while(!tc.hasPlayerWonBool()){

                tc.isValid = false;
                if(var == 0){

                    while(!tc.isValid ){
                        myOutput_1.println(tc.player1 + " select a square");
                        index = Integer.parseInt(myInput_1.readLine());
                        tc.addSymbolPlayer(index,'X',myOutput_1);
                    }
                    var = 1;
                    tc.display(myOutput_2);
                    tc.hasPlayerWon();
                }
                else{

                    while(!tc.isValid ){
                        myOutput_2.println(tc.player2 + " select a square");
                        index = Integer.parseInt(myInput_2.readLine());
                        tc.addSymbolPlayer(index,'O',myOutput_2);
                    }
                    var = 0;
                    tc.display(myOutput_1);
                    tc.hasPlayerWon();

                }

                count++;
                if(tc.hasPlayerWonBool()){
                    tc.print(myOutput_1);
                    tc.print(myOutput_2);
                }
                if(count == 9){
                    break;
                }
            }

            if(!tc.hasPlayerWonBool() && count ==9){
                myOutput_1.println("Draw");
                myOutput_2.println("Draw");
            }


            // close the connection.
            player1.close();
            player2.close();
            System.out.println("Server is exiting");

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Whoops, something bad happened!  I'm outta here.");
        }
    }
}
