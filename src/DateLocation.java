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

    public DateLocation()
    {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }
    public DateLocation(String dateDebut, String dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void setDateDebut(String aDateDebut) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date datedebut = sdf.parse(this.dateDebut = aDateDebut);
            Date datefin = sdf.parse(getDateFin());
            boolean isDatedebutBeforeTodays = !datedebut.after(new Date());
            boolean isDateAfterDateDebut=!datefin.before(datedebut);
            if(isDatedebutBeforeTodays && isDateAfterDateDebut)
            {
                System.out.println("La date de debut :"+dateDebut);
            }
            else {
                if(!isDateAfterDateDebut)
                {
                    System.out.println("La date de fin ne peut être antérieure à la date de début.");
                }
                if(!isDatedebutBeforeTodays)
                {
                    System.out.println("La date de début ne peut être antérieure à aujourd'hui");
                }

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

        /**
     * Sets the end date of the rental and checks if it satisfies the rental constraints.
     * @param aDateFin the end date of the rental in the format "DD-MM-YYYY"
     * @throws IllegalArgumentException if the rental duration exceeds 30 days or if the end date is before the start date
     */


    public void setDateFin(String aDateFin) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        sdf.setLenient(false);
        try{
            Date datedebut=sdf.parse(getDateDebut());
            Date datefin=sdf.parse(this.dateFin=aDateFin);
            boolean isDateAfterDateDebut=!datefin.before(datedebut);
            if (isDateAfterDateDebut)
            {
                System.out.println("La date de fin :"+dateFin);
            }
            else {
                System.out.println("L'erreur de date");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//        LocalDate newDateFin = LocalDate.parse(aDateFin, formatter);
//        if (newDateFin.isBefore(this.dateDebut)) {
//            throw new IllegalArgumentException("La date de fin doit être ultérieure à la date de début.");
//        }
//        if (newDateFin.isAfter(this.dateDebut.plusDays(MAXIMUM_RENTAL_DURATION_DAYS))) {
//            throw new IllegalArgumentException("La durée de location ne peut pas dépasser 30 jours.");
//        }
//        this.dateFin = newDateFin;
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


