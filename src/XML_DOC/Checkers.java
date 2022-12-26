package XML_DOC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Checkers {
    Scanner scanner = new Scanner(System.in);

    //c. Author name is characters (a-z) only
    Boolean checkAuthorAlphapit(String str) {
        //str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 'z' || str.charAt(i) < 'a') {
                return false;
            }
        }
        return true;
    }

    public String checkAuthorName() {
        String author;
        while (true) {
            author = scanner.nextLine();
            try {
                if (checkAuthorAlphapit(author)) {
                    break;
                }
                System.out.println("Enter the book Author name");
            } catch (Exception e) {
            }
        }
        return author;
    }

    public String checkGenre() {
        Map<String, Boolean> Genres = new HashMap<String, Boolean>();
        Genres.put("Science", true);
        Genres.put("Fiction", true);
        Genres.put("Drama", true);
        String genre;
        while (true) {
            try {
                genre = scanner.nextLine();
                if (Genres.get(genre) != null && Genres.get(genre) == true) {
                    break;
                }
                System.out.println("Enter the book Genre (Science, Fiction, Drama)");
            } catch (Exception e) {
            }
        }
        return genre;
    }

    public String checkPreviousId(Map<String, Boolean> perviousIds) {
        String id = new String();
        while (true) {
            try {
                id = scanner.nextLine();
                if (perviousIds.get(id) != null && perviousIds.get(id) == true) {
                    System.out.println("This id was added before.\n");
                    continue;
                }
            }
            catch (Exception e) {
            }
            perviousIds.put(id, true);
            break;
        }
        return id;
    }

    public double checkPrice() {
        double price;
        while (true) {
            try {
                price = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Enter double value");
            }
        }
        return price;
    }

    public String checkDate() {
        String d=new String();
        while (true) {
            String strDate = scanner.nextLine();
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
                d = String.valueOf(date);
                break;
            } catch (ParseException e) {
                System.out.println("enter date in Date format yyyy-MM-dd");
            }
        }
        return d;
    }

}
