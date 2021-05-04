package edu.Utility;

import edu.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import edu.Collection.*;


import java.util.*;
public class CollectionManager {
    private ArrayList<Flat> flats;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.loadCollection();
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }
    /**
     * @return The collection
     */
    public ArrayList<Flat> getFlats(){
        return flats;
    }

    /**
     * Adds new flat to the collection
     * @return true if flat added successfully
     */
    public boolean add(Flat flat) throws ValidationException {
        finishTheFlat(flat);
        return flats.add(flat);
    }
    /**
     * @return Flat by it's ID
     */
    public Flat getById(int id){
        for (Flat flat : flats) {
            if (flat.getId().equals(id)) return flat;
        }
        return null;
    }
    /**
     * Removes element by it's id
     * @return true if completed successfully
     */
    public boolean remove(int id) {
        return flats.removeIf(f -> id == f.getId());
    }

    //public boolean update(Flat flat) throws ValidationException {
      //  remove(flat.getId());
        //return add(flat);
   // }
    /**
     * @return Collection size
     */
    public int collectionSize(){
        return flats.size();
    }
    /**
     * Clears the collection
     */
    public void clear() {
        flats.clear();
    }
    /**
     * Removes First element of collection
     * @return True if completed successfully
     */
    public Flat removeFirst() {
        return flats.remove(0);
    }
    /**
     * Removes Last element of collection
     * @return True if completed successfully
     */
    public Flat removeLast() {
        return flats.remove(flats.size() - 1);
    }
    /**
     * Adds new element if it's value is more than max value in collection
     * @return True if completed successfully
     */
    public boolean addIfMax(Flat flat) throws ValidationException {
        Flat maxElement = null;
        for (Flat f : flats) {
            if (f.compareTo(maxElement) > 0) {
                maxElement = f;
            }
        }

        return flat.compareTo(maxElement) > 0 && add(flat);
    }
    /**
     *
     * @return Count of elements with view better than argument
     */
    public int countGreaterThanView(View view) {
        int count = 0;
        for (Flat f : flats) {
            if (f.getView().compareTo(view) > 0) {
                count++;
            }
        }
        return count;
    }
    /**
     *
     * @return List of unique views in collection
     */
    public Set<View> getUniqueView() {
        Set<View> unique = new HashSet<>();
        for (Flat f : flats) {
            unique.add(f.getView());
        }
        return Collections.unmodifiableSet(unique);
    }
    /**
     *
     * @return The list of houses from min to max
     */
    public List<House> getNumberDescending() {
        ArrayList<House> houses = new ArrayList<>();

        for (Flat f : flats) {
            if (f.getHouse() != null){
            houses.add(f.getHouse());}
        }
        Collections.sort(houses);
        return houses;
    }


    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */
    public Integer generateNextId() {
        if (flats.size() == 0) return 1;
        return flats.get(flats.size()-1).getId()+1;
    }
    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        flats = (ArrayList<Flat>) fileManager.read();
        lastInitTime = LocalDateTime.now();
        Date date = new Date();
        int i = 0;
        for (Flat f : flats) {
            f.setId(i);
            f.setCreationDate(date);
            i++;
        }

    }
    /**
     * Generates ID and CreationDate
     *
     */
    public void finishTheFlat(Flat flat){
        flat.setId(generateNextId());
        flat.setCreationDate(new Date());
        lastInitTime = LocalDateTime.now();
    }
    public void saveCollection(){
        fileManager.write(flats);
    }
    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return flats.getClass().getName();
    }

}

