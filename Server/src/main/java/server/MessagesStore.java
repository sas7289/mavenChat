package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class MessagesStore implements Serializable {
    private final int STORE_MAX_SIZE_FOR_CLIENT;
    ArrayList<ArrayList<String>> store;

    public MessagesStore() {
        this(100);
    }

    public MessagesStore(int storeMaxSize) {
        store = new ArrayList<>();
        STORE_MAX_SIZE_FOR_CLIENT = storeMaxSize;
    }
    public void addMessage(String author, String message) {
        store.add(new ArrayList<>(Arrays.asList(author, message)));
    }

    public ArrayList<ArrayList<String>> getLastPartOfStore(int sizeOfPart) {
        int begin = 0;
        if(store.size() > STORE_MAX_SIZE_FOR_CLIENT) {
            begin = store.size() - STORE_MAX_SIZE_FOR_CLIENT;
        }
        return (ArrayList<ArrayList<String>>) store.subList(begin, store.size());
    }

    public ArrayList<ArrayList<String>> getStore() {
        return store;
    }
}
