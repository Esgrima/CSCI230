package edu.cofc.csci230;

/**
 * Open hashing data structure
 *
 * @author CSCI 230: Data Structures and Algorithms
 *
 * Fall 2018 - Homework 7
 */
public class OpenHashing extends HashTable {

  /* private instance variables */
  private ArrayList<String>[] hash_table;

  /**
   * Constructor
   */
  public OpenHashing(int hash_function) {

    this.hash_function = hash_function;

  } // end constructor

  /**
   * Initialize the hash table
   *
   * The number of elements in the hash table (m) is equal M.
   */
  @SuppressWarnings("unchecked")
  public void initialize() {

    this.hash_table = (ArrayList<String>[]) new ArrayList[HashTable.M];

    for (int i = 0; i < this.hash_table.length; i++) {

      this.hash_table[i] = new ArrayList<>();

    }

  } // end initialize() method


  /**
   * Search for word in the hash table
   *
   * Exceptions: If the key does not exist in the hash table, the throw a HashTableKeyException
   *
   * return: The number of list probes needed to find the word in a list at at the computed key
   * location in the hash table, e.g. One if the word is the first element in the list, n if the
   * word is the very last element in the list (where n is the number of elements in the list at a
   * the computed key location in the hash_table).
   */
  public int search(String word) throws HashTableKeyException {

    int probes = 0;

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    int pos = calcHash(word);

    // Empty bucket
    if (this.hash_table[pos].size() == 0) {
      probes++;
      throw new HashTableKeyException(
          String.format("Search | Word %s Not Found | Probes: %d", word, probes));
    }
    // Non-empty bucket
    else {
      for (int i = 0; i < this.hash_table[pos].size(); i++) {
        probes++;

        // Checks word
        if (this.hash_table[pos].get(i).equals(word)) {
          return probes;
        }
      }
      // Word is not in bucket
      throw new HashTableKeyException(
          String.format("Search | Word %s Not Found | Probes: %d", word, probes));
    }


  } // end search() method

  /**
   * Insert word into hash table
   *
   * Exceptions: Duplicate words are not allowed, e.g., if "dog" already exists in the hash table,
   * then another "dog" word cannot be inserted. For a duplicate word insert operation throw a
   * HashTableKeyException.
   */
  public void insert(String word) throws HashTableKeyException {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    int pos = calcHash(word);
    // Empty bucket
    if (this.hash_table[pos].size() == 0) {
      this.hash_table[pos].add(word);
    }
    // Non-empty bucket
    else {
      for (int i = 0; i < this.hash_table[pos].size(); i++) {
        // Checks for duplicates
        if (this.hash_table[pos].get(i).equals(word)) {
          throw new HashTableKeyException("Duplicate Key!");
        }
      }
      // Word is not in bucket
      this.hash_table[pos].add(word);
    }


  } // end insert() method

  /**
   * Delete a word from the hash table
   *
   * Exceptions: if they word does not exist in the hash table, then throw a HashTableKeyException
   *
   * return: The number of list probes needed to delete the word in the list located at the key
   * location in the hash table, e.g. One if the word is the first element in the list, n if the
   * word is the very last element in the list (where n is the number of elements in the list at a
   * the computed key location in the hash_table).
   */
  public int delete(String word) throws HashTableKeyException {

    int probes = 0;

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    int pos = calcHash(word);

    // Empty bucket
    if (this.hash_table[pos].size() == 0) {
      probes++;
      throw new HashTableKeyException(
          String.format("Delete | Word %s Not Found | Probes: %d", word, probes));
    }
    // Non-empty bucket
    else {
      for (int i = 0; i < this.hash_table[pos].size(); i++) {
        probes++;

        // Checks word
        if (this.hash_table[pos].get(i).equals(word)) {
          this.hash_table[pos].remove(i);
          return probes;
        }
      }
      // Word is not in bucket
      throw new HashTableKeyException(
          String.format("Delete | Word %s Not Found | Probes: %d", word, probes));
    }
  } // end delete() method

  /**
   * See page 271 in supplemental course textbook (chapter is provided as PDF on OAKS in content
   * section).
   *
   * Also, refer to your lecture notes.
   */
  public double loadFactor() {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    double keys = 0;
    double cells = 0;

    // Iterates through each slot's ArrayList
    for (ArrayList<String> slot : this.hash_table) {
      //Increments keys by the size of the
      keys += slot.size();

      //Increments occupied cells by 1
      if (slot.size() > 0) {
        cells++;
      }
    }


    return keys / cells;
  } // end loadFactor() method


  /**
   * See equation (7.4) on page 271 in supplemental course textbook (chapter is provided as PDF on
   * OAKS in content section).
   */
  public double successfulSearches() {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */

    return 1.0 + (this.loadFactor() / 2);

  } // end successfulSearches() method

  /**
   * See equation (7.4) on page 271 in supplemental course textbook (chapter is provided as PDF on
   * OAKS in content section).
   */
  public double unsuccessfulSearches() {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */

    return this.loadFactor();

  } // end unsuccessfulSearches() method

} // end OpenHashing class definition
