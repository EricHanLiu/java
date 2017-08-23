/* HOTEL RESERVATION SYSTEM, FULLY FUNCTIONAL. User checks in, inputs name, and says how many days he
 * wants to reserve the room for. He will be charged a certain amount based on the room's cost, and
 * will be told the features of his room. Will ask for the next in line, and next customer will proceed
 * with the program. This program features a date and time system, where the date will advance by
 * one day every 2 customers. Once a room is taken it cannot be chosen for the duration of it's occupation,
 * and after it's occuption is up a message will be displayed in the logs section of the printout, stating
 * that that customer has checked out and that the room is now available. Customers can then choose
 * that room to occupy, and after the day length they choose passes, it will once again be open. This 
 * program supports thirty customers, to a total of 15 days in the month of January, 2014. A lot of the code
 * is customizable, such as the amount of customers per day and the date and time, as well as the
 * prices of the rooms. May implement a check-in date using another class named Calender, which will
 * store the dates that people input and on that date will display a message saying that person
 * has checked in.
 * 
 */
import java.util.*;
public class hotelLiu {
    public static void main(String [ ] args) {
        //VARIABLES
        Scanner stdin = new Scanner(System.in);
        int i = 0;
        int q = 0;
        int d = 0;
        int roomType; //1,2,3,4.
        int random = 0;
        String keepGoing; 
        int day = 1; //Day of the month, goes up 1 ever 2 customers.
        //Following 4 variables are to retrieve the removed rooms from ArrayList used(Regular,etc.), and increase as counters.
        int regular = 0;
        int deluxe = 0;
        int suite = 0;
        int presidential = 0;
        int customerNumber = 0; //Max of 30.
        //Check-in date.
        int[] checkInDay = new int[30]; //Not implemented yet, will be checkin date.   
        String[] checkInMonth = new String[30];      
        //Current date.
        String date; //Month + day + year.
        int[] numberOfDays = new int[30];
        String[] customerNames = new String[30];
        ArrayList usedRooms = new ArrayList(30);
        //Avoids displaying check-out message until a customer checks in.
        for (i = 0; i < 30; i++) { //So the log checkout system works smoothly.
            numberOfDays[i] = 100;
        }

        //Makes 4 arraylist of 30 rooms total, so a room of each type is removed upon rental.
        //15 regular, 8 deluxe, 4 suites, 3 presidentials.
        ArrayList regularRooms = new ArrayList(15); 
        for (i = 1; i <= 15; i++) {
            regularRooms.add("(REGULAR) Room " + i);
        }
        ArrayList deluxeRooms = new ArrayList(8);
        for (i = 16; i <= 23; i++) {
            deluxeRooms.add("(DELUXE) Room " + i);
        }
        ArrayList suiteRooms = new ArrayList(4);
        for (i = 24; i <= 27; i++) {
            suiteRooms.add("(SUITE) Room " + i);
        }
        ArrayList presidentialRooms = new ArrayList(3);
        for (i = 28; i <= 30; i++) {
            presidentialRooms.add("(PRESIDENTIAL) Room " + i);
        }

        i = 0;
        do {
            customerNumber++;
            date = "JANUARY " + day + ", 2014";
            System.out.println("CURRENT DATE: " + date);
            System.out.println("CUSTOMER NUMBER " + customerNumber);
            System.out.println("What's your name?");
            customerNames[i] = stdin.next();
            System.out.println("Welcome to HotelLiu! Please follow the steps to reserve a room.");
            //Check-in date.
            System.out.println("REQUESTED CHECK-IN DATE:");
            System.out.print("DAY: ");
            checkInDay[i] = stdin.nextInt();
            System.out.print("MONTH: ");
            checkInMonth[i] = stdin.next();
            System.out.println();

            //What type of room.
            do { //Loops if a room is full.
                System.out.println("What type of room would you like?");
                System.out.println("ROOM OPTIONS: (1: REGULAR) (2: DELUXE) (3: SUITE) (4: PRESIDENTIAL)");
                System.out.println("                 100$/N      150$/N      300$/N        500$/N ");
                roomType = stdin.nextInt();
                System.out.println("How many days would you like the room for?");
                numberOfDays[i] = stdin.nextInt();
                System.out.print("TOTAL COST: ");
                //If user doesn't enter 1-4.
                if (roomType > 4 || roomType < 1) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                //Code for room type, features, and removes the room from availibility.
                if (roomType == 1 && regularRooms.size() > 0) {
                    System.out.println(100 * numberOfDays[i] + "$");
                    System.out.println();
                    random = (int) (Math.random() * regularRooms.size());
                    System.out.println(regularRooms.get(random) + " assigned.");
                    roomFeatures(roomType);
                    usedRooms.add(regularRooms.get(random));
                    regularRooms.remove(random);                      
                } else if (roomType == 2 && deluxeRooms.size() > 0) {
                    System.out.println(150 * numberOfDays[i] + "$");
                    System.out.println();
                    random = (int) (Math.random() * deluxeRooms.size());
                    System.out.println(deluxeRooms.get(random) + " assigned.");
                    roomFeatures(roomType);
                    usedRooms.add(deluxeRooms.get(random));
                    deluxeRooms.remove(random);
                } else if (roomType == 3 && suiteRooms.size() > 0) {
                    System.out.println(300 * numberOfDays[i] + "$");
                    System.out.println();
                    random = (int) (Math.random() * suiteRooms.size());
                    System.out.println(suiteRooms.get(random) + " assigned.");
                    roomFeatures(roomType);
                    usedRooms.add(suiteRooms.get(random));
                    suiteRooms.remove(random);
                } else if (roomType == 4 && presidentialRooms.size() > 0) {
                    System.out.println(500 * numberOfDays[i] + "$");
                    System.out.println();
                    random = (int) (Math.random() * presidentialRooms.size());
                    System.out.println(presidentialRooms.get(random) + " assigned.");
                    roomFeatures(roomType);
                    usedRooms.add(presidentialRooms.get(random));
                    presidentialRooms.remove(random);  
                } else {
                    System.out.println("Sorry, all the rooms of this type are booked!");
                    roomType = 5; //So it will re-enter the loop.
                }
            } while (roomType != 1 && roomType != 2 && roomType != 3 && roomType != 4);

            //LOGS
            System.out.println();
            System.out.println("LOGS:");
            System.out.println("CHECK-INS:");
            for (int m = 0; m < 30; m++) {
                if (date.equalsIgnoreCase(checkInMonth[m] + " " + checkInDay[m] + ", 2014")) {
                    System.out.println("CUSTOMER " + (m+1) + " (" + customerNames[m].toUpperCase() + ") HAS CHECKED INTO " + usedRooms.get(d) + "!");
                    checkInMonth[m] = "blah";
                    d++;
                }
            }
            System.out.println("CHECK-OUTS:");
            for (int m = 0; m < 30; m++) {
                String test = "";
                if (numberOfDays[m] == 0) {
                    System.out.println("CUSTOMER " + (m+1) + " (" + customerNames[m].toUpperCase() + ") HAS CHECKED OUT OF " + usedRooms.get(q) + "!");
                    numberOfDays[m] = 100; //Avoids displaying checkout message for same customer twice.
                    //READDS THE REMOVED ROOM ON CHECKOUT OF THE CUSTOMER
                    test = (String) usedRooms.get(q);
                    if (test.substring(0, 14).equals("(PRESIDENTIAL)")) {
                        presidentialRooms.add(usedRooms.get(q));
                    } else if (test.substring(0, 9).equals("(REGULAR)")) {
                        regularRooms.add(usedRooms.get(q));
                    } else if (test.substring(0, 8).equals("(DELUXE)")) {
                        deluxeRooms.add(usedRooms.get(q));
                    } else if (test.substring(0, 7).equals("(SUITE)")) {
                        suiteRooms.add(usedRooms.get(q));
                    }
                    q++;
                }
            }

            //Prompt to redo the loop, counts the counter variables.
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println();
            System.out.println("Next in line please! (TYPE 'OK' TO CONTINUE)");
            keepGoing = stdin.next();
            System.out.println();
            System.out.println();
            if (customerNumber % 2 == 0) { //NUMBER OF CUSTOMERS PER DAY
                day++;
                for (int k = 0; k < 30; k++) {
                    numberOfDays[k]--;
                }
            }
            i++;
        } while (keepGoing.equalsIgnoreCase("OK") && customerNumber <= 30);
        System.out.println("Program has been terminated (max # of customers or lack of continuation).");
    }

    public static void roomFeatures(int roomType) {
        System.out.println("Features of your room:");
        if (roomType == 1) {
            System.out.println("-A DOUBLE BED");
            System.out.println("-A BATHROOM");
            System.out.println("-A 30' TV");
            System.out.println("-ACCESS TO THE POOL");
        } else if (roomType == 2) {
            System.out.println("-A DOUBLE BED");
            System.out.println("-A SINGLE BED");
            System.out.println("-A BATHROOM");
            System.out.println("-A 40' TV");
            System.out.println("-ACCESS TO THE POOL");
        } else if (roomType == 3) {
            System.out.println("-A QUEEN SIZED BED");
            System.out.println("-A LARGE BATHROOM WITH HOT-TUB");
            System.out.println("-A 50' FLATSCREEN TV");
            System.out.println("-A BALCONY");
            System.out.println("-A KITCHEN");
            System.out.println("-ACCESS TO THE POOL");
        } else if (roomType == 4) {
            System.out.println("-A KING SIZED BED");
            System.out.println("-A DOUBLE BED");
            System.out.println("-A LARGE BATHROOM WITH HOT-TUB");
            System.out.println("-A 60' FLAT-SCREEN TV");
            System.out.println("-A BALCONY");
            System.out.println("-A KITCHEN");
            System.out.println("-FREE ACCESS TO THE BAR");
            System.out.println("-ACCESS TO A PRIVATE POOL");
        }
    }
}