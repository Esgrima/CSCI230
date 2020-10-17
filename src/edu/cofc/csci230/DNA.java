package edu.cofc.csci230;

import java.util.Scanner;

public class DNA {
  /**
   * Application that will check and correct double-stranded DNA patterns.
   */
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);

    String notDefinedError = "The entered upper strand or lower strand is not defined ... Exiting"
        + " Program";
    String diffLenError = "The entered upper and lower strands do not have the same number of "
        + "chemical bases ... Exiting program";
    String combError = "The entered upper and lower strands must only contain combinations of A, "
        + "G, C, or T ... Exiting program";
    String basePairError = "The entered double-stranded DNA pattern had base-pair errors that"
        + " have been corrected: %s %s \n\n ... Exiting program";
    String validMessage = "The entered double-stranded DNA pattern is correct ... Exiting program";

    System.out.print("Enter the upper DNA Strand: ");
    String upperStrand = scnr.nextLine();
    System.out.print("Enter the lower DNA Strand: ");
    String lowerStrand = scnr.nextLine();

    // Checks for a undefined error
    if (upperStrand.isEmpty() || lowerStrand.isEmpty()) {
      System.out.println(notDefinedError);
      scnr.close();
      return;
    }

    // Checks for a length mismatch error
    if (upperStrand.length() != lowerStrand.length()) {
      System.out.println(diffLenError);
      scnr.close();
      return;
    }

    // Checks if any of the strands have invalid characters
    if (upperStrand.split("[TGAC]").length > 0 
        || lowerStrand.split("[TGAC]").length > 0) {
      System.out.println(combError);
      scnr.close();
      return;
    }

    StringBuilder correctedLowerStrand = new StringBuilder();
    boolean corrected = false;

    // Checks for base-pair errors and corrects them if necessary
    for (int i = 0; i < upperStrand.length(); i++) {
      if (upperStrand.charAt(i) == 'A' && lowerStrand.charAt(i) != 'T') {
        correctedLowerStrand.append('t');
        corrected = true;
      } else if (upperStrand.charAt(i) == 'T' && lowerStrand.charAt(i) != 'A') {
        correctedLowerStrand.append('a');
        corrected = true;
      } else if (upperStrand.charAt(i) == 'C' && lowerStrand.charAt(i) != 'G') {
        correctedLowerStrand.append('g');
        corrected = true;
      } else if (upperStrand.charAt(i) == 'G' && lowerStrand.charAt(i) != 'C') {
        correctedLowerStrand.append('c');
        corrected = true;
      } else {
        correctedLowerStrand.append(upperStrand.charAt(i));
      }
    }

    if (corrected) {
      System.out.printf(basePairError, upperStrand, correctedLowerStrand.toString());
      scnr.close();
      return;
    }

    System.out.println(validMessage);
    scnr.close();
  }
}