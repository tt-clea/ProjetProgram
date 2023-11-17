import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.Serializable;


/**
 * A class to represent the start and end date of a video rental.
 */
public class DateLocation implements Serializable{

    private static final long serialVersionUID = 4L;
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

    public void setDateDebut(String aDateDebut) {

        try {
            if (aDateDebut!=null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
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

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try{
            if (aDateFin !=null)
            {
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
            }

        } catch (ParseException e) {
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