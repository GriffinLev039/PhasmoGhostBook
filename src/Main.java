import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Book ghostBook = new Book();
        Scanner input = new Scanner(System.in);
        int userInput = 0;
        while (!(userInput == 6)) {
            System.out.print("Enter a number corresponding to your option. \n 0. Set # of Evidence (0/1/2/3) \n 1. List info \n 2. Input evidence \n 3. Remove Evidence \n 4. Get ghost info \n 5. Reset \n 6. Exit \nYour input: ");
            userInput = input.nextInt();
            input.nextLine();
            switch (userInput) {
                case 0:
                    int userInputForCount = input.nextInt();
                    ghostBook.setEvidenceCount(userInputForCount);
                    System.out.println("The evidence count is: " + userInputForCount);
                    input.nextLine();
                    break;
                case 1:
                    System.out.println(ghostBook.toString());
                    break;
                case 2:
                    System.out.print("Enter the evidence name (UV/EMF/FREEZING/ORB/BOX/DOTS/WRITING): ");
                    String evidenceName = input.nextLine().trim();
                    ghostBook.validateEvidence(evidenceInputValidation(evidenceName));
                    System.out.println(ghostBook.toString());
                    break;
                case 3:
                    System.out.print("Enter the evidence name (UV/EMF/FREEZING/ORB/BOX/DOTS/WRITING): ");
                    String badEvidenceName = input.nextLine().trim();
                    ghostBook.invalidateEvidence(evidenceInputValidation(badEvidenceName));
                    System.out.println(ghostBook.toString());
                    break;
                case 4:
                    System.out.print("Enter the name of the ghost: ");
                    String ghostName = input.next();
                    System.out.println(ghostBook.getGhostInfo(ghostName));
                    break;
                case 5:
                    ghostBook = new Book();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
            }
        }
    }

    public static String evidenceInputValidation(String inputEvidence) {
        final String[] EVIDENCE = new String[]{"EMF", "DOTS", "UV","Ghost Writing","Ghost Orb", "Spirit Box", "Freezing Temperatures"};

        inputEvidence = inputEvidence.trim().toUpperCase();
        //Common varations for "Freezing Temperature"
        if (inputEvidence.contains("FREEZING") || inputEvidence.equals("TEMP")) {
            return "FREEZING TEMPERATURE";
        }
        // Common variations for "Ghost Orb"
        if (inputEvidence.contains("ORB")) {
            return "GHOST ORB";
        }
        // Common variations for "Ghost Writing"
        if (inputEvidence.contains("WRITING")) {
            return "GHOST WRITING";
        }
        //Common variations for EMF 5
        if (inputEvidence.contains("EMF") || inputEvidence.contains("5")) {
            return "EMF";
        }
        //Common variations for "Spirit Box"
        if (inputEvidence.contains("SPIRIT") || (inputEvidence.contains("BOX"))) {
            return "Spirit Box";
        }
        //Common variations for DOTS
        if (inputEvidence.contains("DOTS") || inputEvidence.contains("D.O.T.S")) {
            return "DOTS";
        }
        //Common variations for UV
        if (inputEvidence.contains("UV") || inputEvidence.contains("ULTRA") || inputEvidence.contains("FING")) {
            return "UV";
        }
        // Check for other evidence types
        for (String evidence : EVIDENCE) {
            if (inputEvidence.contains(evidence.toUpperCase())) {
                return evidence.toUpperCase();
            }
        }
        return null;
    }
}