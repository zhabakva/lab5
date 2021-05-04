package edu.Utility;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import edu.Collection.*;


/**
 * Reads the JSON file and returns ready-made collection, or write the collection to file. All operations with file.
 * **/
public class FileManager {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private String filePath;
    public FileManager(String filePath) {
        this.filePath = filePath;
    }
    private FileManager() {
    }


    /**
     * Reads the JSON file and returns ready-made collection.
     * **/
    public List<Flat> read() {
        File file = new File(filePath);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
           // System.out.println(OBJECT_MAPPER.readValue(bis, List.class).get(0).getClass().toString());
            List<Flat> result = OBJECT_MAPPER.readValue(bis, new TypeReference<List<Flat>>(){});
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException exception) {
            Console.printerror("Загрузочный файл пуст!");
        } catch (NullPointerException exception) {
            Console.printerror("В загрузочном файле не обнаружена необходимая коллекция!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
            System.exit(0);
        }
        return List.of();
    }
    /**
     * Writes the collection to file
     * **/
    public void write(List<Flat> flats) {
        if (filePath != null) {
            File file = new File(filePath);
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
                OBJECT_MAPPER.writeValue(bos, flats);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
    }


}
