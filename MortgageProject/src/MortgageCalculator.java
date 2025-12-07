import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.Objects;

public class MortgageCalculator {
    private static final double BAND_LOW_MIN = 2000.0;
    private static final double BAND_LOW_MAX = 3000.0;
    private static final double BAND_MID_MIN = 3000.0;
    private static final double BAND_MID_MAX = 5000.0;
    private static final double BAND_HIGH_MIN = 5000.0;

    private static final double PARTNER_INCOME_COEFFICIENT = 0.94;
    private static final int MINIMUM_AGE = 18;

    public double computeMaxMortgage(int yearOfBirt, int month, int day,
                                     double monthlyIncome,
                                     boolean married,
                                     double monthlyIncomePartner,
                                     String profession) {

        validateInputs(yearOfBirt, month, day, monthlyIncome, monthlyIncomePartner, profession);

        LocalDate birthDate = LocalDate.of(yearOfBirt, month, day);
        int age = calculateAge(birthDate, LocalDate.now());

        if (age < MINIMUM_AGE) {
            return 0d;
        }

        double effectiveIncome = married
                ? combineIncomes(monthlyIncome, monthlyIncomePartner)
                : monthlyIncome;

        ProfessionGroup group = ProfessionGroup.from(profession);

        return computeMortgageByBandAndProfession(effectiveIncome, group);
    }

    private void validateInputs(int year, int month, int day,
                                double monthlyIncome,
                                double monthlyIncomePartner,
                                String profession) {

        Objects.requireNonNull(profession, "profession must not be null");
        if (month < 1 || month > 12) throw new IllegalArgumentException("month out of range");
        if (day < 1 || day > 31) throw new IllegalArgumentException("day out of range");
        if (monthlyIncome < 0) throw new IllegalArgumentException("monthlyIncome must be >= 0");
        if (monthlyIncomePartner < 0) throw new IllegalArgumentException("monthlyIncomePartner must be >= 0");
        if (year < 1900 || year > LocalDate.now().getYear()) throw new IllegalArgumentException("year out of reasonable range");
    }

    private int calculateAge(LocalDate birthday, LocalDate referenceDate) {
        Period p = Period.between(birthday, referenceDate);
        return p.getYears();
    }

    private double combineIncomes(double monthlyIncome, double monthlyIncomePartner) {
        return monthlyIncome + monthlyIncomePartner * PARTNER_INCOME_COEFFICIENT;
    }

    private double computeMortgageByBandAndProfession(double effectiveIncome, ProfessionGroup group) {
        if (effectiveIncome >= BAND_LOW_MIN && effectiveIncome < BAND_LOW_MAX) {
            return switch (group) {
                case TECH -> 160_000d;
                case SUPPORT -> 120_000d;
                case SENIOR -> 220_000d;
                case UNKNOWN -> 0d;
            };
        }

        if (effectiveIncome >= BAND_MID_MIN && effectiveIncome < BAND_MID_MAX) {
            return switch (group) {
                case TECH -> 180_000d;
                case SUPPORT -> 140_000d;
                case SENIOR -> 250_000d;
                case UNKNOWN -> 0d;
            };
        }

        if (effectiveIncome >= BAND_HIGH_MIN) {
            return switch (group) {
                case TECH -> 220_000d;
                case SUPPORT -> 160_000d;
                case SENIOR -> 280_000d;
                case UNKNOWN -> 0d;
            };
        }
        return 0d;
    }

    private enum ProfessionGroup {
        TECH, SUPPORT, SENIOR, UNKNOWN;

        static ProfessionGroup from(String profession) {
            if (profession == null) return UNKNOWN;
            String normalized = profession.trim().toLowerCase(Locale.ROOT);
            return switch (normalized) {
                case "developer", "architect", "scrum master" -> TECH;
                case "tester", "system administrator", "system administrator ", "systemadministrator", "technical writer" -> SUPPORT;
                case "department head", "professor" -> SENIOR;
                default -> UNKNOWN;
            };
        }
    }
}
