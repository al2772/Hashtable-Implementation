// --== CS400 Project One File Header ==--
// Name: Adam Lewandowski
// CSL Username: alewandowski
// Email: aclewandowski@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

public interface MapADT <KeyType, ValueType> {

    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();
    
}