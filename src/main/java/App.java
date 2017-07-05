import DAO.BillDao;
import DAO.BillsTransactions;
import DAO.ClientDao;
import DAO.HibSession;
import Model.Bill;
import Model.Client;
import Model.Currency;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by andrew on 10.05.17.
 */
public class App {
   static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        HibSession instance = HibSession.getInstance();
        instance.fillClients();
        while (true){
        System.out.println("1:fill your bill");
        System.out.println("2:exchange money between bills");
        System.out.println("3:convert all money of user to UAH");
        System.out.println("4:convert all money of user to USD");
        System.out.println("5:convert all money of user to EUR");
        System.out.println("6:get all money of user in UAH");

        int x = scanner.nextInt();
        switch (x){
            case 1:fillBill();break;
            case 2:exchange();break;
            case 3:convertTo(Currency.UAH);break;
            case 4:convertTo(Currency.USD);break;
            case 5:convertTo(Currency.EUR);break;
            case 6:showAllMoneyOfUser();break;
            default:continue;
        }
        }
    }



    private static void showAllMoneyOfUser() {
        System.out.println("Print the name of client:");
        String name = scanner.next();
        System.out.println(BillsTransactions.convertTo(name, Currency.UAH));




    }

    private static void convertTo(Currency currency) {
        System.out.println("Print the name of client:");
        String name = scanner.next();
        System.out.println(BillsTransactions.convertTo(name, currency));

    }


    private static void exchange() throws SQLException {
        System.out.println("Print the number of the first Bill:");
        String billnumber1 = scanner.next();
        System.out.println("Print the number of the second Bill:");
        String billnumber2 = scanner.next();
        System.out.println("Print the sum to exchange:");
        float sum = scanner.nextFloat();
        BillsTransactions.exchange(billnumber1,billnumber2,sum);

    }

    private static void fillBill() throws SQLException {

        System.out.println("Print the number of Bill: ");
        String billNumber = scanner.next();
        System.out.println("Print the currency USD/UAH/EUR:");
        String cur = scanner.next();
        Currency currency = null;
        for (Currency curr :Currency.values() ) {
            if(curr.getName().equals(cur)){
                currency = curr;
                break;
            }
        }
        System.out.println("Print the sum you would like to fill: ");
        float sum = scanner.nextFloat();
        BillsTransactions.fillBill(billNumber,sum,currency);
    }


}
