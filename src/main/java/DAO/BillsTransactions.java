package DAO;

import Model.Bill;
import Model.Client;
import Model.Currency;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by andrew on 22.06.17.
 */
public class BillsTransactions {
    static HibSession hibSession = HibSession.getInstance();
    public static void fillBill(String billnumber,float sum,Currency currency) throws SQLException {

        Bill bill = new BillDao().GetBillByID(billnumber);
        if(bill == null){
            System.out.println("There is no such bill!");
            return;
        }
        bill.setValue(convertBill(currency,bill)+sum);
        new BillDao().save(bill);
    }
    public static void exchange(String bill1, String bill2, float sum) throws SQLException {
        Bill bill = new BillDao().GetBillByID(bill1);
        if(bill == null){
            System.out.println("There is no such bill!");
            return;
        }
        if(bill.getValue()<sum){
            System.out.println("You have not enough money");
            return;
        }
        fillBill(bill1,(-sum),Currency.UAH);
        fillBill(bill2,sum,Currency.UAH);

    }
    public static float convertTo(String name, Currency currency){
        Client client = new ClientDao().getClientByName(name);
        float sum = 0;
        for (Bill bill : client.getBills()) {
            float value = convertBill(currency, bill);
            sum += value;
        }
        return sum;
    }

    private static float convertBill(Currency currency, Bill bill) {
        float value = bill.getValue();
        value = value * bill.getCurrency().getValue();
        value = value / currency.getValue();
        bill.setValue(value);
        bill.setCurrency(currency);
        new BillDao().save(bill);
        return value;
    }
}
