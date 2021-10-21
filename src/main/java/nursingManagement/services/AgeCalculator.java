package nursingManagement.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class AgeCalculator {

    public static int calculateAge(Date birthDate){

        System.out.println(birthDate);

        if(birthDate == null){
            return 0;
        }

        String birthDateString = birthDate.toString();
        birthDateString = birthDateString.substring(0,10);
        System.out.println(birthDateString);

        String[] birthDateArray = birthDateString.split("-");

        LocalDate today = LocalDate.now();                          //Today's date
        LocalDate birthdayConverted = LocalDate.of(Integer.parseInt(birthDateArray[0]), Integer.parseInt(birthDateArray[1]), Integer.parseInt(birthDateArray[2]));  //Birth date

        Period p = Period.between(birthdayConverted, today);

        return p.getYears();

    }

}
