import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Car class
class Car {
    private final String model;
    private final String registrationNumber;
    private boolean isAvailable;

    public Car(String model, String registrationNumber) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.isAvailable = true; // Cars are available by default
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        this.isAvailable = false;
    }

    public void returnCar() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return model + " (" + registrationNumber + ")";
    }
}

// RentalAgency class
class RentalAgency {
    private final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void displayAvailableCars() {
        System.out.println("Available Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car);
            }
        }
    }

    public void rentCar(String registrationNumber) {
        for (Car car : cars) {
            if (car.getRegistrationNumber().equals(registrationNumber) && car.isAvailable()) {
                car.rent();
                System.out.println("You have rented: " + car);
                return;
            }
        }
        System.out.println("Car not available for rent.");
    }

    public void returnCar(String registrationNumber) {
        for (Car car : cars) {
            if (car.getRegistrationNumber().equals(registrationNumber)) {
                car.returnCar();
                System.out.println("Car returned: " + car);
                return;
            }
        }
        System.out.println("Invalid registration number.");
    }
}

// Main class to test the system
public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalAgency rentalAgency = new RentalAgency();

        // Adding some cars to the rental agency
        rentalAgency.addCar(new Car("Toyota Camry", "KDB 587F"));
        rentalAgency.addCar(new Car("Honda Accord", "KCJ 966G"));

        while (true) {
            System.out.println("\nCar Rental System");
            System.out.println("1. Display Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Return a Car");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> rentalAgency.displayAvailableCars();
                case 2 -> {
                    System.out.print("Enter the registration number of the car to rent: ");
                    String regNumber = scanner.nextLine();
                    rentalAgency.rentCar(regNumber);
                }
                case 3 -> {
                    System.out.print("Enter the registration number of the car to return: ");
                    String regNumber = scanner.nextLine();
                    rentalAgency.returnCar(regNumber);
                }
                case 4 -> {
                    System.out.println("Thank you for using the Car Rental System!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
