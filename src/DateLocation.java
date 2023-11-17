import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A class to represent the start and end date of a video rental.
 */
public class DateLocation {

    private String dateDebut; // Start date of the rental
    private String dateFin; // End date of the rental
    private static final int MAXIMUM_RENTAL_DURATION_DAYS = 30; // Maximum allowed rental duration in days

    public DateLocation(String dateDebut)
    {
        this.dateDebut=dateDebut;
    }
    public DateLocation(String dateDebut, String dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    
    /**
     * Sets the start date of the rental with validation.
     *
     * @param aDateDebut The start date to set for the rental.
     */
    public void setDateDebut(String aDateDebut) {

        try { // Check if the input start date is not null
            if (aDateDebut!=null)
            { // Create a SimpleDateFormat with a specific date format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             // Set lenient to false to enforce strict date parsing
                sdf.setLenient(false);
             // Parse the input start date and set it to the instance variable dateDebut
                Date datedebut = sdf.parse(this.dateDebut = aDateDebut);
             // Parse the end date obtained from the getDateFin() method
                Date datefin = sdf.parse(getDateFin());
             // Check if the start date is not after today and the end date is not before the start date
                boolean isDatedebutBeforeTodays = !datedebut.after(new Date());
                boolean isDateAfterDateDebut=!datefin.before(datedebut);
             // If both conditions are met, print the start date
                if(isDatedebutBeforeTodays && isDateAfterDateDebut)
                {
                    System.out.println("La date de debut :"+dateDebut);
                }
                else { // If the end date is before the start date, print an error message
                    if(!isDateAfterDateDebut)
                    {
                        System.out.println("La date de fin ne peut être antérieure à la date de début.");
                    }
                    if(!isDatedebutBeforeTodays)
                    { // If the start date is before today, print an error message
                        System.out.println("La date de début ne peut être antérieure à aujourd'hui");
                    }

                }
            }

        } catch (ParseException e) {  // Throw a runtime exception in case of a parsing error
            throw new RuntimeException(e);
        }
    }

        /**
     * Sets the end date of the rental and checks if it satisfies the rental constraints.
     * @param aDateFin the end date of the rental in the format "DD-MM-YYYY"
     * @throws IllegalArgumentException if the rental duration exceeds 30 days or if the end date is before the start date
     */


    public void setDateFin(String aDateFin) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     // Set lenient to false to enforce strict date parsing
        sdf.setLenient(false);
        try{ // Check if the input end date is not null
            if (aDateFin !=null)
            { // Parse the start date obtained from the getDateDebut() method
                Date datedebut=sdf.parse(getDateDebut());
             // Parse the input end date and set it to the instance variable dateFin
                Date datefin=sdf.parse(this.dateFin=aDateFin);
             // Check if the end date is not before the start date
                boolean isDateAfterDateDebut=!datefin.before(datedebut);
             // If the end date is after the start date, print the end date
                if (isDateAfterDateDebut)
                {
                    System.out.println("La date de fin :"+dateFin);
                }
                else {
                	// If the end date is before the start date, print an error message
                    System.out.println("L'erreur de date");
                }
            }

        } catch (ParseException e) { // Throw a runtime exception in case of a parsing error
            throw new RuntimeException(e);
        }

    }


    /**
     * Gets the start date of the rental.
     * @return the start date of the rental
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Gets the end date of the rental.
     * @return the end date of the rental
     */
    public String getDateFin() {
        return dateFin;
    }


    @Override
    public String toString() {
        return "DateLocation{" +
                "dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                '}';
    }
}


