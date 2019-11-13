package chess;

import java.util.ArrayList;

class MockConsole extends Console {
    String result;
    ArrayList<String> inputList;
    int count = 0;

    @Override
    void show(String str) {
        result = str;
    }

    @Override
    String read() {
        return inputList.get(count++);
    }
}
