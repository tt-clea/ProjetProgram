
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class to represent the start and end date of a video rental.
 */
public class DateLocation {

    private LocalDate dateDebut; // Start date of the rental
    private LocalDate dateFin; // End date of the rental
    private static final int MAXIMUM_RENTAL_DURATION_DAYS = 30; // Maximum allowed rental duration in days

    /**
     * Sets the start date of the rental.
     * @param dateDebut the start date of the rental in the format "DD-MM-YYYY"
     */
    public void setDateDebut(String aDateDebut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        this.dateDebut = LocalDate.parse(aDateDebut, formatter);
    }

    /**
     * Sets the end date of the rental and checks if it satisfies the rental constraints.
     * @param dateFin the end date of the rental in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the rental duration exceeds 30 days or if the end date is before the start date
     */
    public void setDateFin(String aDateFin) {
        LocalDate newDateFin = LocalDate.parse(aDateFin, DateTimeFormatter.ISO_DATE);
        if (newDateFin.isBefore(this.dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être ultérieure à la date de début.");
        }
        if (newDateFin.isAfter(this.dateDebut.plusDays(MAXIMUM_RENTAL_DURATION_DAYS))) {
            throw new IllegalArgumentException("La durée de location ne peut pas dépasser 30 jours.");
        }
        this.dateFin = newDateFin;
    }

    /**
     * Gets the start date of the rental.
     * @return the start date of the rental
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Gets the end date of the rental.
     * @return the end date of the rental
     */
    public LocalDate getDateFin() {
        return dateFin;
    }
}
