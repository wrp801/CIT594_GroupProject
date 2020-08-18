package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.Processor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

public class UserInterface {

    protected Processor processor;
    protected Scanner in;

    public UserInterface(Processor processor) {
        this.processor = processor;
        in = new Scanner(System.in);
    }

    public void start() {

        while (true) {

            System.out.println("***************************************************************************\n" +
                    "What do you want to know?" +
                    "\nEnter the number corresponding with the desired calculation." +
                    "\n0 - Exit program" +
                    "\n1 - Total population of all ZIP codes" +
                    "\n2 - Total parking fines per capita for each ZIP code" +
                    "\n3 - Average market value for residences in a specified ZIP code" +
                    "\n4 - Average total livable area for residences in a specified ZIP code" +
                    "\n5 - Total residential market value per capita for a specified ZIP code" +
                    "\n6 - Rank and plot ZIP codes by fines per capita and value per capita" +
                    "\n***************************************************************************");

            int choice = -1;

            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input a single integer.");
                System.exit(0);  // Would be better to loop them back to decision step but not required
            }

            if (choice < 0 || choice > 6) {
                System.out.println("Please input a number 0 through 6.");
                System.exit(0);
            }

            if (choice == 0) System.exit(0);
            if (choice == 1) System.out.println(processor.displayTotalPopulation());
            if (choice == 2) {
                TreeMap<Integer, Double> zipFines = processor.displayFinesPerCapita();
                DecimalFormat df = new DecimalFormat("0.0000");
                for (int zipcode : zipFines.keySet()) {

                    String printFines = df.format(zipFines.get(zipcode));
                    System.out.println(zipcode + " " + printFines);
                }
            }
            if (choice == 3) {
                System.out.println("Enter a zip code.");
                int zipEntered = in.nextInt(); // error check later
                System.out.println(processor.displayAvgMarketValue(zipEntered));
            }
            if (choice == 4) {
                System.out.println("Enter a zip code.");
                int zipEntered = in.nextInt(); // error check later
                System.out.println(processor.displayAvgTotalLivableArea(zipEntered));
            }
            if (choice == 5) {
                System.out.println("Enter a zip code.");
                int zipEntered = in.nextInt(); // error check later
                System.out.println(processor.displayMarketValuePerCapita(zipEntered));
            }
            if (choice == 6) {
                HashMap<Integer, double[]> zipFinesAndValues = processor.displayFinesVsMarketValue();

                System.out.println("+++++ ZIP code plot: fine rank vs. value rank +++++\n");
                System.out.println("Y axis: value rank");

                for (int yCounter = 13; yCounter > 0; yCounter --) {
                    System.out.print("|");
                    for (int xCounter = 1; xCounter <= 13; xCounter ++) {
                        int foundCount = 0;
                        for (double[] zipInfo : zipFinesAndValues.values()) {
                            if (Math.round(zipInfo[2] / 4) == xCounter) {
                                if (Math.round(zipInfo[3] / 4) == yCounter) {
                                    foundCount ++ ;
                                }
                            }
                        }
                        if (foundCount > 0) System.out.print(" * ");
                        else System.out.print("   ");
                    }
                    System.out.println();
                }
                System.out.println(" ---------------------------------------- X axis: fine rank");

                System.out.println();

            }

        }

    }

}
