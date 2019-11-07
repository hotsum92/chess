package chess;

import java.util.Scanner;

class Console {

    Scanner scanner = new Scanner(System.in);

    void show(String str) {
        System.out.print(str);
    }

    String read() {
        return scanner.nextLine();
    }
}
