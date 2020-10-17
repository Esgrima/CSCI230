package edu.cofc.csci230;

/**
 * Hash Simulator (main method)
 *
 * @author CSCI 230: Data Structures and Algorithms
 *
 * Fall 2018 - Homework 7
 */
public class HashSimulator {

  /**
   *
   * @param args
   */
  public static void main(String[] args) {

    HashTable hashDS = new ClosedHashing(HashTable.HASH_FUNC1);
    System.out.printf("\n%s ----------------------------------\n", "Closed Hashing: FUNC1");

    if (hashDS.loadWords()) {

      ((ClosedHashing) hashDS).initialize();

      System.out.printf("Number of non-unique words in file = %d\n", hashDS.getWords().size());

      /* ------------------------------------------------
       * DONE:
       * ------------------------------------------------
       * 1) Insert each word into hash data structure
       * 2) Calculate load factor value and print to
       *    console (using System.printf or System.println)
       * 3) Calculate successful searches value and print to
       *    console (using System.printf or System.println)
       * 4) Calculate unsuccessful searches value and print to
       *    console (using System.printf or System.println)
       * 5) For each word in words list, search in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 6) For a word (you choose) that does not exist in hashDS,
       *    try to search for it in the hashDS and then print the probe
       *    value to console (using System.printf or System.println)
       * 7) For each word in words list, delete word in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 8) For a word (you choose) that does not exist in hashDS,
       *    try to delete in the hashDS and then print the probe value to
       *    console (using System.printf or System.println)
       *
       */

      //1
//      // Part of Duplicate Verification
//      java.util.ArrayList<String> duplicates = new java.util.ArrayList<String>();
      int i = 0;
      List<String> words = hashDS.getWords();
      while (i < words.size()) {

        try {
//          System.out.println(hashDS.getWords().get(i));
          ((ClosedHashing) hashDS).insert(words.get(i));

        } catch (HashTableKeyException htke) {
//          System.out.println("Duplicate Key!!");
//          // Duplicate verification
//          duplicates.add(hashDS.getWords().get(i));
//          System.out.println(duplicates);
        } finally {
          i++;

        }
      }
      System.out.println("All words have been INSERTED and DUPLICATES have been handled.");
//      // Verifies No Duplicate Elements. <<Must make HashTable object's hashtable public>>
//      for (int x = 0; x < ((ClosedHashing) hashDS).hash_table.length - 1; x++) {
//        for (int j = x + 1; j < ((ClosedHashing) hashDS).hash_table.length; j++) {
//          if (((ClosedHashing) hashDS).hash_table[x] != null && ((ClosedHashing) hashDS).hash_table[j] != null ) {
//            if (((ClosedHashing) hashDS).hash_table[x]
//                .equals(((ClosedHashing) hashDS).hash_table[j])) {
//              System.out.println("DUPS Present");
//              return;
//            }
//          }
//        }
//      }
      System.out.println("=============================================");
      //2
      System.out.printf("Load Factor: %f\n", ((ClosedHashing) hashDS).loadFactor());
      //3
      System.out.printf("Successful Searches: %f\n", ((ClosedHashing) hashDS).successfulSearches());
      //4
      System.out.printf("Unsuccessful Searches: %f\n",
          ((ClosedHashing) hashDS).unsuccessfulSearches());
      System.out.println("=============================================");

      //5 Mean Probe Value for Search
      try {
        double mean_probe = 0;
        for (i = 0; i < hashDS.getWords().size(); i++) {
          mean_probe += ((ClosedHashing) hashDS).search(hashDS.getWords().get(i));
        }
        mean_probe /= hashDS.getWords().size();
        System.out.printf("Search Mean Probe Value: %f\n", mean_probe);
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("=============================================");
      }

      //6 Probe Value that's not in words.txt
      try {
        ((ClosedHashing) hashDS).search("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

      //7 Mean Probe Value for delete
      i = 0;
      double mean_probe = 0;
      while (i < words.size()) {
        try {
          mean_probe += ((ClosedHashing) hashDS).delete(words.get(i));
        } catch (HashTableKeyException htke) {
//          System.out.println(htke.getMessage());
        } finally {
          i++;
        }
      }
      mean_probe /= hashDS.getWords().size();
      System.out.printf("Delete Mean Probe Value: %f\n", mean_probe);
      System.out.println("==============================================");

      //8 Probe Value that's not in words.txt
      try {
        ((ClosedHashing) hashDS).delete("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }


    } else {

      System.out.println("Failed to load words from text file");
    }

    // ------------------------------------------------
    // Repeat for second hash function

    System.out.printf("\n%s ----------------------------------\n", "Closed Hashing: FUNC2");
    hashDS = new ClosedHashing(HashTable.HASH_FUNC2);

    if (hashDS.loadWords()) {

      ((ClosedHashing) hashDS).initialize();

      System.out.printf("Number of non-unique words in file = %d\n", hashDS.getWords().size());

      /* ------------------------------------------------
       * DONE:
       * ------------------------------------------------
       * 1) Insert each word into hash data structure
       * 2) Calculate load factor value and print to
       *    console (using System.printf or System.println)
       * 3) Calculate successful searches value and print to
       *    console (using System.printf or System.println)
       * 4) Calculate unsuccessful searches value and print to
       *    console (using System.printf or System.println)
       * 5) For each word in words list, search in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 6) For a word (you choose) that does not exist in hashDS,
       *    try to search for it in the hashDS and then print the probe
       *    value to console (using System.printf or System.println)
       * 7) For each word in words list, delete word in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 8) For a word (you choose) that does not exist in hashDS,
       *    try to delete in the hashDS and then print the probe value to
       *    console (using System.printf or System.println)
       *
       */
      //1
//      // Part of Duplicate Verification
//      java.util.ArrayList<String> duplicates = new java.util.ArrayList<String>();
      int i = 0;
      List<String> words = hashDS.getWords();
      while (i < words.size()) {

        try {
//          System.out.println(hashDS.getWords().get(i));
          ((ClosedHashing) hashDS).insert(words.get(i));

        } catch (HashTableKeyException htke) {
//          System.out.println("Duplicate Key!");
//          // Duplicate verification
//          duplicates.add(hashDS.getWords().get(i));
//          System.out.println(duplicates);
        } finally {
          i++;

        }
      }
      System.out.println("All words have been INSERTED and DUPLICATES have been handled.");
//      // Verifies No Duplicate Elements. <<Must make HashTable object's hashtable public>>
//      for (int x = 0; x < ((ClosedHashing) hashDS).hash_table.length - 1; x++) {
//        for (int j = x + 1; j < ((ClosedHashing) hashDS).hash_table.length; j++) {
//          if (((ClosedHashing) hashDS).hash_table[x] != null && ((ClosedHashing) hashDS).hash_table[j] != null ) {
//            if (((ClosedHashing) hashDS).hash_table[x]
//                .equals(((ClosedHashing) hashDS).hash_table[j])) {
//              System.out.println("DUPS Present");
//              return;
//            }
//          }
//        }
//      }
      System.out.println("=============================================");
      //2
      System.out.printf("Load Factor: %f\n", ((ClosedHashing) hashDS).loadFactor());
      //3
      System.out.printf("Successful Searches: %f\n", ((ClosedHashing) hashDS).successfulSearches());
      //4
      System.out.printf("Unsuccessful Searches: %f\n",
          ((ClosedHashing) hashDS).unsuccessfulSearches());
      System.out.println("==============================================");

      //5 Mean Probe Value for Search
      try {
        double mean_probe = 0;
        for (i = 0; i < hashDS.getWords().size(); i++) {
          mean_probe += ((ClosedHashing) hashDS).search(hashDS.getWords().get(i));
        }
        mean_probe /= hashDS.getWords().size();
        System.out.printf("Search Mean Probe Value: %f\n", mean_probe);
      } catch (HashTableKeyException htke) {
        System.out.println("This shouldn't print: Failed!");
      } finally {
        System.out.println("==============================================");
      }

      //6 Probe Value that's not in words.txt
      try {
        ((ClosedHashing) hashDS).search("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

      //7 Mean Probe Value for delete
      i = 0;
      double mean_probe = 0;
      while (i < words.size()) {
        try {
          mean_probe += ((ClosedHashing) hashDS).delete(words.get(i));
        } catch (HashTableKeyException htke) {
//          System.out.println(htke.getMessage());
        } finally {
          i++;
        }
      }
      mean_probe /= hashDS.getWords().size();
      System.out.printf("Delete Mean Probe Value: %f\n", mean_probe);
      System.out.println("==============================================");

      //8 Probe Value that's not in words.txt
      try {
        ((ClosedHashing) hashDS).delete("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }


    } else {

      System.out.println("Failed to load words from text file");
    }

    hashDS = new OpenHashing(HashTable.HASH_FUNC1);
    System.out.printf("\n%s ----------------------------------\n", "Open Hashing: FUNC1");

    if (hashDS.loadWords()) {

      ((OpenHashing) hashDS).initialize();

      System.out.printf("Number of non-unique words in file = %d\n", hashDS.getWords().size());

      /* -------------------------------------------------
       * DONE:
       * -------------------------------------------------
       * 1) Insert each word into hash data structure
       * 2) Calculate load factor value and print to
       *    console (using System.printf or System.println)
       * 3) Calculate successful searches value and print to
       *    console (using System.printf or System.println)
       * 4) Calculate unsuccessful searches value and print to
       *    console (using System.printf or System.println)
       * 5) For each word in words list, search in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 6) For a word (you choose) that does not exist in hashDS,
       *    try to search for it in the hashDS and then print the probe
       *    value to console (using System.printf or System.println)
       * 7) For each word in words list, delete word in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 8) For a word (you choose) that does not exist in hashDS,
       *    try to delete in the hashDS and then print the probe value to
       *    console (using System.printf or System.println)
       *
       */

      //1
//      // Part of Duplicate Verification
//      java.util.ArrayList<String> duplicates = new java.util.ArrayList<String>();
      int i = 0;
      List<String> words = hashDS.getWords();
      while (i < words.size()) {

        try {
//          System.out.println(hashDS.getWords().get(i));
          ((OpenHashing) hashDS).insert(words.get(i));

        } catch (HashTableKeyException htke) {
//          System.out.println("Duplicate Key!");
//          // Duplicate verification
//          duplicates.add(hashDS.getWords().get(i));
//          System.out.println(duplicates);
        } finally {
          i++;

        }
      }
      System.out.println("All words have been INSERTED and DUPLICATES have been handled.");
      System.out.println("==============================================");

      //2
      System.out.printf("Load Factor: %f\n", ((OpenHashing) hashDS).loadFactor());
      //3
      System.out.printf("Successful Searches: %f\n", ((OpenHashing) hashDS).successfulSearches());
      //4
      System.out.printf("Unsuccessful Searches: %f\n",
          ((OpenHashing) hashDS).unsuccessfulSearches());
      System.out.println("==============================================");

      //5 Mean Probe Value for Search
      try {
        double mean_probe = 0;
        for (i = 0; i < hashDS.getWords().size(); i++) {
          mean_probe += ((OpenHashing) hashDS).search(hashDS.getWords().get(i));
        }
        mean_probe /= hashDS.getWords().size();
        System.out.printf("Search Mean Probe Value: %f\n", mean_probe);
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("=============================================");
      }

      //6 Probe Value that's not in words.txt
      try {
        ((OpenHashing) hashDS).search("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

      //7 Mean Probe Value for delete
      i = 0;
      double mean_probe = 0;
      while (i < words.size()) {
        try {
          mean_probe += ((OpenHashing) hashDS).delete(words.get(i));
        } catch (HashTableKeyException htke) {
//          System.out.println(htke.getMessage());
        } finally {
          i++;
        }
      }
      mean_probe /= hashDS.getWords().size();
      System.out.printf("Delete Mean Probe Value: %f\n", mean_probe);
      System.out.println("==============================================");

      //8 Probe Value that's not in words.txt
      try {
        ((OpenHashing) hashDS).delete("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

    } else {

      System.out.println("Failed to load words from text file");

    }

    // ------------------------------------------------
    // Repeat for second hash function

    hashDS = new OpenHashing(HashTable.HASH_FUNC2);
    System.out.printf("\n%s ----------------------------------\n", "Open Hashing: FUNC2");

    if (hashDS.loadWords()) {

      ((OpenHashing) hashDS).initialize();

      System.out.printf("Number of non-unique words in file = %d\n", hashDS.getWords().size());

      /* ------------------------------------------------
       * DONE:
       * ------------------------------------------------
       * 1) Insert each word into hash data structure
       * 2) Calculate load factor value and print to
       *    console (using System.printf or System.println)
       * 3) Calculate successful searches value and print to
       *    console (using System.printf or System.println)
       * 4) Calculate unsuccessful searches value and print to
       *    console (using System.printf or System.println)
       * 5) For each word in words list, search in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 6) For a word (you choose) that does not exist in hashDS,
       *    try to search for it in the hashDS and then print the probe
       *    value to console (using System.printf or System.println)
       * 7) For each word in words list, delete word in the hashDS,
       *    and print mean probe value to console (using
       *    System.printf or System.println)
       * 8) For a word (you choose) that does not exist in hashDS,
       *    try to delete in the hashDS and then print the probe value to
       *    console (using System.printf or System.println)
       *
       */
      //1
//      // Part of Duplicate Verification
//      java.util.ArrayList<String> duplicates = new java.util.ArrayList<String>();
      int i = 0;
      List<String> words = hashDS.getWords();
      while (i < words.size()) {
        try {
//          System.out.println(hashDS.getWords().get(i));
          ((OpenHashing) hashDS).insert(words.get(i));
        } catch (HashTableKeyException htke) {
//          System.out.println("Duplicate Key!");
//          // Duplicate verification
//          duplicates.add(hashDS.getWords().get(i));
//          System.out.println(duplicates);
        } finally {
          i++;
        }
      }
      System.out.println("All words have been INSERTED and DUPLICATES have been handled.");
      System.out.println("==============================================");
      //2
      System.out.printf("Load Factor: %f\n", ((OpenHashing) hashDS).loadFactor());
      //3
      System.out.printf("Successful Searches: %f\n", ((OpenHashing) hashDS).successfulSearches());
      //4
      System.out.printf("Unsuccessful Searches: %f\n",
          ((OpenHashing) hashDS).unsuccessfulSearches());
      System.out.println("==============================================");

      //5 Mean Probe Value for Search
      try {
        double mean_probe = 0;
        for (i = 0; i < hashDS.getWords().size(); i++) {
          mean_probe += ((OpenHashing) hashDS).search(hashDS.getWords().get(i));
        }
        mean_probe /= hashDS.getWords().size();
        System.out.printf("Search Mean Probe Value: %f\n", mean_probe);
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

      //6 Probe Value that's not in words.txt
      try {
        ((OpenHashing) hashDS).search("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

      //7 Mean Probe Value for delete
      i = 0;
      double mean_probe = 0;
      while (i < words.size()) {
        try {
          mean_probe += ((OpenHashing) hashDS).delete(words.get(i));
        } catch (HashTableKeyException htke) {
//          System.out.println(htke.getMessage());
        } finally {
          i++;
        }
      }
      mean_probe /= hashDS.getWords().size();
      System.out.printf("Delete Mean Probe Value: %f\n", mean_probe);

      System.out.println("===============================================");
      //8 Probe Value that's not in words.txt
      try {
        ((OpenHashing) hashDS).delete("Mastadon");
      } catch (HashTableKeyException htke) {
        System.out.println(htke.getMessage());
      } finally {
        System.out.println("==============================================");
      }

    } else {

      System.out.println("Failed to load words from text file");
    }

  } // end main() method

} // end Hash Simulator class definition