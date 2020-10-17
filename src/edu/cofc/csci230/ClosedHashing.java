package edu.cofc.csci230;

/**
 * Closed hashing data structure using linear probing.
 *
 * @author CSCI 230: Data Structures and Algorithms
 *
 * Fall 2018 - Homework 7
 */
public class ClosedHashing extends HashTable {

  /* private instance variables */
  private String[] hash_table;
  private int[] collision_table;

  /**
   * Constructor
   */
  public ClosedHashing(int hash_function) {

    this.hash_function = hash_function;

  } // end constructor

  /**
   * Initialize the hash table
   *
   * The number of elements in the hash table is equal to 2 x the number of words (may or may not be
   * unique) in the word list.
   */
  public void initialize() {

    this.hash_table = new String[2 * words.size()];
    this.collision_table = new int[2 * words.size()];

    for (int i = 0; i < this.hash_table.length; i++) {

      this.hash_table[i] = null;

    }

  } // end initialize() method


  /**
   * Search for word in the hash table.
   *
   * The collision_table is used to indicate a word/key collision key has occurred. In the
   * collision_table the number at the computed key index position stores the total number of
   * collisions. For example, collision_table[16] = 3 means three collisions have occurred at key
   * index position 16 in the hash_table.
   *
   * Exceptions: If the word does not exist in the hash table, then throw a HashTableKeyException
   *
   * return: The number of linear probes needed to find the word in the hash table, e.g. Zero if no
   * probing, n if probed n times to find the word location.
   */
  public int search(String word) throws HashTableKeyException {

    int probes = 0;

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    int pos = calcHash(word);

    // Case 1: Collisions are zero
    if (this.collision_table[pos] == 0) {

      //Sub-Case 1: Key is not at home slot nor the rest of the HT
      if (this.hash_table[pos] == null || (this.hash_table[pos] != null && !this.hash_table[pos]
          .equals(word))) {
        throw new HashTableKeyException(
            String.format("Search | Word: %s Not Found | Probes: %d", word, probes));
      }

      //Sub-Case 2: Key is at home slot
      else if (this.hash_table[pos] != null && this.hash_table[pos].equals(word)) {
        return probes;
      }
    }

    // Case 2: Collisions
    else {
      //Sub-Case 1: Key is not at home but probing is required
      if (this.hash_table[pos] == null || !this.hash_table[pos].equals(word)) {

        int collision_matcher = 0;
        int i = 1;

        // Indexed positions will begin after pos and wrap around to pos - 1
        while (i < this.hash_table.length - 1) {
          //Probing has begun
          probes++;

          //If the current position is not null make these checks
          if (this.hash_table[(pos + i) % this.hash_table.length] != null) {

            // Keeps track of keys met with same hash
            if (calcHash(this.hash_table[(pos + i) % this.hash_table.length]) == pos) {
              collision_matcher++;
            }

            //If found return probes
            if (this.hash_table[(pos + i) % this.hash_table.length].equals(word)) {
              return probes;
            }
          }

          // If these two match before then
          if (collision_matcher == this.collision_table[pos]) {

            if (!this.hash_table[(pos + i) % this.hash_table.length].equals(word)) {
              throw new HashTableKeyException(
                  String.format("Search | Word: %s Not Found | Probes: %d", word, probes));
            }

            else {
              return probes;
            }
          }
          i++;
        }
      }

      // Sub-Case 2: Word matches
      else {
        return probes;
      }
    }
    return probes;
  } // end search() method

  /*
   * Insert word into hash table
   *
   * Exceptions: Duplicate words are not allowed, e.g., if "dog" already exists
   * in the hash table, then another "dog" word cannot be inserted.
   * For a duplicate word insert operation throw a HashTableKeyException.
   *
   * @param word
   */
  public void insert(String word) throws HashTableKeyException {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    int pos = calcHash(word);

    // Case 1: Hash is null and collision status doesn't matter
    if (this.hash_table[pos] == null) {
      this.hash_table[pos] = word;
    }

    // Case 2: Duplicate
    else if (this.hash_table[pos].equals(word)) {
      throw new HashTableKeyException("Duplicate Key!");
    }

    // Case 3: Collision
    else {
      this.collision_table[pos]++;

      int i = 1;
      while (this.hash_table[(pos + i) % this.hash_table.length] != null) {
        i++;
      }
      this.hash_table[(pos + i) % this.hash_table.length] = word;
    }
  } // end insert() method

  /**
   * Delete a word from the hash table.
   *
   * The collision_table is used to indicate a word/key collision key has occurred. In the
   * collision_table the number at the computed key index position stores the total number of
   * collisions. For example, collision_table[16] = 3 means three collisions have occurred at key
   * index position 16 in the hash_table.
   *
   * Every successful word deletion should decrement the corresponding collision_table value by one.
   * For example, if the key index position is 3 and the word has been successfully deleted then
   * collision_table[16] = collision_table[16] - 1; Note: the number of collisions will never be
   * less than zero. If it is, you have a bug in your code :)
   *
   * Exceptions: if they word does not exist in the hash table then throw a HashTableKeyException
   *
   * return: The number of linear probes needed to delete the word in the hash table, e.g. Zero if
   * no probing, n if probed n times to find the word location.
   */
  public int delete(String word) throws HashTableKeyException {

    int probes = 0;

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */
    int pos = calcHash(word);

    // Case 1: No collisions
    if (this.collision_table[pos] == 0) {
      //Sub-Case 1: Word Doesn't Exist
      if (this.hash_table[pos] == null || (this.hash_table[pos] != null && !this.hash_table[pos]
          .equals(word))) {
        throw new HashTableKeyException(
            String.format("Delete | first Word: %s Not Found | Probes: %d", word, probes));
      }// Sub-Case 2: Word found
      else {
        this.hash_table[pos] = null;
        return probes;
      }
    }

    // Case 2: Collisions
    else {

      // Sub-Case 1: Word not found at home, probing is required
      if (this.hash_table[pos] == null || !this.hash_table[pos].equals(word)) {

        int collision_matcher = 0;
        int i = 1;

        while (i < this.hash_table.length - 1) {
          //Probing has begun
          probes++;


          //If the current position is not null make these checks
          if (this.hash_table[(pos + i) % this.hash_table.length] != null) {

            // Keeps track of keys met with same hash
            if (calcHash(this.hash_table[(pos + i) % this.hash_table.length]) == pos) {
              collision_matcher++;
            }

            //If found: delete, update collision_table, return probes
            if (this.hash_table[(pos + i) % this.hash_table.length].equals(word)) {
              this.hash_table[(pos + i) % this.hash_table.length] = null;
              this.collision_table[pos]--;
              return probes;
            }
          }

          // If these two match before the end of the while loop
          if (collision_matcher == this.collision_table[pos]) {

            // If word is not found after collisions are found
            if (!this.hash_table[(pos + i) % this.hash_table.length].equals(word)) {
              throw new HashTableKeyException(
                  String.format("Delete | Word: %s Not Found | Probes: %d", word, probes));
            }

            // Word is found at the last collision position
            else {
              this.hash_table[(pos + i) % this.hash_table.length] = null;
              this.collision_table[pos]--;
              return probes;
            }
          }
          i++;
        }

      } // Sub-Case 2: Word Found at home slot
      else {
        this.hash_table[pos] = null;
        return probes;
      }
    }
    return probes;

  } // end delete() method

  /**
   * See page 271 in supplemental course textbook (chapter is provided as PDF on OAKS in content
   * section).
   *
   * Also, refer to your lecture notes. Note, for closed hashing, m is the number of locations in
   * the hash table.
   */
  public double loadFactor() {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */

    int n = 0;
    for (String key : this.hash_table) {
      if (key != null) {
        n++;
      }
    }
    return (double) n / this.hash_table.length;
  } // end loadFactor() method


  /**
   * See equation (7.5) on page 273 in supplemental course textbook (chapter is provided as PDF on
   * OAKS in content section).
   */
  public double successfulSearches() {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */

    return 0.5 * (1.0 + (1.0 / (1.0 - this.loadFactor())));

  } // end successfulSearches() method

  /**
   * See equation (7.5) on page 273 in supplemental course textbook (chapter is provided as PDF on
   * OAKS in content section).
   */
  public double unsuccessfulSearches() {

    /* ----------------------------------
     * DONE: Put your solution here
     * ----------------------------------
     */

    return 0.5 * (1.0 + (1.0 / Math.pow(1.0 - this.loadFactor(), 2.0)));

  } // end unsuccessfulSearches() method

} // end ClosedHashing class definition