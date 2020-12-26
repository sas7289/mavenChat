package org.example.model;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class ArchiveMessages {
    private final int MAX_ARCHIVE_SIZE;
    File archive;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    FileReader fileReader;
    FileWriter fileWriter;
    public Queue<String> archiveQueue;


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
            while ((message = bufferedReader.readLine()) != null) {
                archiveQueue.add(message);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMessageToArchive(String message) {
        if(archiveQueue.size() >= MAX_ARCHIVE_SIZE) {
            archiveQueue.remove();
        }
        archiveQueue.add(message);
    }

    public void archiving() {
        for (String s : archiveQueue) {
            try {
                new FileOutputStream(archive).close();
                fileWriter.write(s + System.lineSeparator());
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



    public int getArchiveSize() {
        return archiveQueue.size();
    }
}
