package chess;

import java.util.ArrayList;

class MockConsole extends Console {
    String result;
    String input;
    ArrayList<String> inputList;
    int count = 0;

    @Override
    void show(String str) {
        result = str;
    }

    @Override
    String read() {
        if(input instanceof String) {
            return input;
        }else {
            return inputList.get(count++);
        }
    }
}
