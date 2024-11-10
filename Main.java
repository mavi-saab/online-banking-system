import java.util.Scanner;

class BankDetails {
    private String accno;
    private String name;
    private String acc_type;
    private long balance;
    Scanner sc = new Scanner(System.in);

    // Method to open a new account
    public void openAccount() {
        System.out.print("Enter Account No: ");
        accno = sc.next();
        System.out.print("Enter Account Type: ");
        acc_type = sc.next();
        System.out.print("Enter Name: ");
        name = sc.next();
        System.out.print("Enter Balance: ");
        balance = sc.nextLong();
    }

    // Method to display account details
    public void showAccount() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account No.: " + accno);
        System.out.println("Account Type: " + acc_type);
        System.out.println("Balance: " + balance);
    }

    // Method to deposit money
    public void deposit() {
        System.out.print("Enter the amount you want to deposit: ");
        long amt = sc.nextLong();
        if (amt > 0) {
            balance += amt;
            System.out.println("Amount Deposited: " + amt);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw() {
        System.out.print("Enter the amount you want to withdraw: ");
        long amt = sc.nextLong();
        if (balance >= amt) {
            balance -= amt;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }

    // Method to search for an account number
    public boolean search(String ac_no) {
        if (accno.equals(ac_no)) {
            showAccount();
            return true;
        }
        return false;
    }
}

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create initial accounts
        System.out.print("How many customers do you want to input? ");
        int n = sc.nextInt();
        BankDetails[] accounts = new BankDetails[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new BankDetails();
            accounts[i].openAccount();
        }

        // Menu-driven application
        int choice;
        do {
            System.out.println("\nBanking System Application");
            System.out.println("1. Display all account details");
            System.out.println("2. Search by Account number");
            System.out.println("3. Deposit amount");
            System.out.println("4. Withdraw amount");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (BankDetails account : accounts) {
                        account.showAccount();
                    }
                    break;

                case 2:
                    System.out.print("Enter account number you want to search: ");
                    String ac_no = sc.next();
                    boolean found = false;
                    for (BankDetails account : accounts) {
                        found = account.search(ac_no);
                        if (found) break;
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account No: ");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : accounts) {
                        found = account.search(ac_no);
                        if (found) {
                            account.deposit();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account No: ");
                    ac_no = sc.next();
                    found = false;
                    for (BankDetails account : accounts) {
                        found = account.search(ac_no);
                        if (found) {
                            account.withdraw();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using our banking system. See you soon!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 5);
        
        sc.close();
    }
}
