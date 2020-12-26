package server;

import java.util.ArrayList;

public class MessagesStore {
    private final int STORE_MAX_SIZE_FOR_CLIENT;
    ArrayList<String> store;

    public MessagesStore() {
        this(100);
    }

    public MessagesStore(int storeMaxSize) {
        STORE_MAX_SIZE_FOR_CLIENT = storeMaxSize;
    }
    public void addMessage(String message) {
        store.add(message);
    }

    public ArrayList<String> getLastPartOfStore(int sizeOfPart) {
        int begin = 0;
        if(store.size() > STORE_MAX_SIZE_FOR_CLIENT) {
            begin = store.size() - STORE_MAX_SIZE_FOR_CLIENT;
        }
        return (ArrayList<String>) store.subList(begin, store.size());
    }
}
