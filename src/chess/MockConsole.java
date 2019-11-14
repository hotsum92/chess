package chess;

import java.util.ArrayList;

class MockConsole extends Console {
    String result;
    ArrayList<String> inputList;
    int count = 0;
    int counter = 0;

    MockConsole(){
        super();
        inputList  = new ArrayList<>();
        counter = inputList.size();
    }

    @Override
    void show(String str) {
        result = str;
    }

    @Override
    String read() {
        return inputList.get(count++);
    }

    void add(String str){
        inputList.add(str);
        counter++;
    }
}