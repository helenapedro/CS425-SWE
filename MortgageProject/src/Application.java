public class Application {
    public static void main(String[] args) {
        MortgageCalculator mortgageCalculator = new MortgageCalculator();

        double mortgage1 = mortgageCalculator.computeMaxMortgage(1967, 5, 2, 3000, false, 0, "Architect");
        System.out.println("Mortgage1: " + mortgage1);

        double mortgage2 = mortgageCalculator.computeMaxMortgage(2001, 5, 2, 3000, false, 0, "Architect");
        System.out.println("Mortgage2: " + mortgage2);

        double mortgage3 = mortgageCalculator.computeMaxMortgage(1977, 12, 2, 4000, true, 2000, "Developer");
        System.out.println("Mortgage3: " + mortgage3);

        double mortgage4 = mortgageCalculator.computeMaxMortgage(1954, 5, 12, 5500, false, 0, "Professor");
        System.out.println("Mortgage4: " + mortgage4);
    }
}
