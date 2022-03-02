import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        String name = "", breed = "";
        int age, count = 0;

        Dog[] dogPound = new Dog[10];

        Scanner keyboard = new Scanner(System.in);

        // Reading f rom the binary file dogs.dat
        File binaryFile = new File("dogs.dat");
        //Check to see if the file exists AND non-zero size
        System.out.println("Previously saved dogs from the binary file:");
        if(binaryFile.exists() &&  binaryFile.length() > 1L)  //int is 32 bits a long (L) is 64 bits
        {
            //Read from the binary file
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // Read the entire array into the dogPound
                // readObject returns the object data type
                // Dog[] = object , catse object as the type of dog
                dogPound = (Dog[]) fileReader.readObject();
                // Loop through the array and print out all the objects
                while(dogPound[count] != null)
                    System.out.println(dogPound[count++]);
                fileReader.close();// if you dont close, writer cant open
            } catch (IOException | ClassNotFoundException e) {
               // e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }
        else
            System.out.println("[None, please enter new dog data]");

        do{
            System.out.print("Please Enter dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if(name.equalsIgnoreCase("quit"))
            break;
            System.out.print("Please Enter dog's breed: ");
            breed = keyboard.nextLine();
            System.out.print("Please Enter dog's age: ");
            age = keyboard.nextInt();
            keyboard.nextLine();

            /// Create new dog
            // Add to the array
            /// Incrementing the count var (spot in the array, starts at 0. Sees count (0) and then the ++ happens after, ++ == post-fix operator)
            dogPound[count++] = new Dog(name, breed, age);
        }
        while(true);

        // After user enters quit, write the dogPound array to binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //Hi

    }
}
