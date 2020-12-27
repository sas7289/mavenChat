package org.example.model;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class ArchiveMessages {
    private int startingCountMessage;
    private final int MAX_ARCHIVE_SIZE;
    File archive;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    FileReader fileReader;
    FileWriter fileWriter;
    public ArrayDeque<ArrayList<String>> archiveQueue;


    public ArchiveMessages() {
        this("archiveMessages", 100);
    }

    public ArchiveMessages(String pathToFile, int maxArchiveSize) {
        MAX_ARCHIVE_SIZE = maxArchiveSize;
        archive = new File(pathToFile);
        archiveQueue = new ArrayDeque<>();

        try {
            fileWriter = new FileWriter(archive, true);
            fileReader = new FileReader(archive);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String message;
            String[] splitMessage;
            while ((message = bufferedReader.readLine()) != null) {
                splitMessage = message.split("\\: ");
                this.addMessageToArchive(splitMessage[0], splitMessage[1]);
//                archiveQueue.add(new ArrayList<>(Arrays.asList(splitMessage)));
            }
            bufferedReader.close();
            startingCountMessage = archiveQueue.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListToQueue(ArrayList<ArrayList<String>> messagesList) {
//        if( messagesList == null) {return;}
//        for (ArrayList<String> arrayList : messagesList) {
//            this.addMessageToArchive(arrayList.get(0), arrayList.get(1));
//        }
        archiveQueue.addAll(messagesList);
    }
/*
    public void addMessageToArchive(String author, String message) {
        if(archiveQueue.size() >= MAX_ARCHIVE_SIZE) {
            archiveQueue.remove();
        }
        archiveQueue.add(new ArrayList<>(Arrays.asList(author , message)));
    }*/

    public void addMessageToArchive(String author, String message) {
        if(archiveQueue.size() >= MAX_ARCHIVE_SIZE) {
            archiveQueue.remove();
        }
        archiveQueue.add(new ArrayList<>(Arrays.asList(author, message)));
    }






    public void archiving() {
        if(archiveQueue == null) {
            return;
        }
        if(archiveQueue.size() <= MAX_ARCHIVE_SIZE) {
            Iterator<ArrayList<String>> iterator = archiveQueue.descendingIterator();
            while (iterator.hasNext()) {
                try {
                    ArrayList<String> message = iterator.next();
//                    new FileOutputStream(archive).close();
                    fileWriter.write(String.format("%s: %s", message.get(0), message.get(1)) /*s + System.lineSeparator()*/);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            for (int i = 0; i < MAX_ARCHIVE_SIZE; i++) {
                try {
                    new FileOutputStream(archive).close();
                    ArrayList<String> temp = archiveQueue.pollLast();
                    fileWriter.write(String.format("%s: %s", temp.get(0), temp.get(1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public int getArchiveSize() {
        return archiveQueue.size();
    }
}
