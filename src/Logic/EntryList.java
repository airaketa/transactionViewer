package Logic;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EntryList {
    public ArrayList<Entry> list = new ArrayList<Entry>();

    public EntryList(File file) throws IOException{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String entryStr = reader.readLine();
            while (entryStr!=null){
                try {
                    String[] entryArr = entryStr.split(";");
                    long transaction_id = Long.parseLong(entryArr[0]);                          //номер транзакции
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.ENGLISH);
                    Date date = null;                                                           //дата
                    try {
                        date = dateFormat.parse(entryArr[1]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                    Date time = null;                                                             //время
                    try {
                        time = timeFormat.parse(entryArr[2]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int transType = Integer.parseInt(entryArr[3]);                              //тип транзакции
                    long cashReg_id = Long.parseLong(entryArr[4]);                              //номер POS
                    long cheque_id = Long.parseLong(entryArr[5]);                               //номер чека
                    long cashier_id = Long.parseLong(entryArr[6]);                              //код кассира

                    Integer[] transTypes = {1, 2, 3, 4, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 24,
                            30, 31, 32, 33, 72, 73, 75, 85, 90, 175, 300, 309, 411, 412, 413, 414, 424, 430};
                    Long section_id = null;                                                          //номер секции
                    if (Arrays.asList(transTypes).contains(transType))
                        section_id = Long.parseLong(entryArr[8]);
                    transTypes = new Integer[]{11, 12, 13, 30, 31, 32, 33, 15, 16, 17, 18, 19, 76,
                            66, 260, 411, 412, 413, 430};
                    Long itemCode = null;                                                          //код товара
                    if (Arrays.asList(transTypes).contains(transType))
                        itemCode = Long.parseLong(entryArr[7]);
                    transTypes = new Integer[]{1, 11, 2, 12, 3, 13, 4, 14, 30, 31, 32, 33, 34, 74,
                            411, 412, 413, 414, 430};
                    Float price = null;                                                            //цена
                    if (Arrays.asList(transTypes).contains(transType))
                        price = Float.parseFloat(entryArr[9]);
                    transTypes = new Integer[]{1, 11, 2, 12, 3, 13, 4, 14, 31, 32, 33, 34, 74,
                            411, 412, 413, 414};
                    Float amount = null;                                                        //количество
                    if (Arrays.asList(transTypes).contains(transType))
                        amount = Float.parseFloat(entryArr[10]);
                    transTypes = new Integer[]{15, 17, 35, 37, 70, 71, 74, 77, 142, 171};
                    Float discount = null;                                                      //сумма скидки
                    if (Arrays.asList(transTypes).contains(transType))
                        discount = Float.parseFloat(entryArr[11]);
                    transTypes = new Integer[]{1, 11, 2, 12, 3, 13, 4, 14, 31, 32, 33, 34, 40, 50, 51, 55,
                            155, 56, 58, 59, 95, 60, 61, 75, 175, 85, 72, 73, 122, 140, 411, 412, 413, 414};
                    Float total = null;                                                         //сумма чека
                    if (Arrays.asList(transTypes).contains(transType))
                        total = Float.parseFloat(entryArr[11]);
                    transTypes = new Integer[]{171, 67, 121, 141, 142};
                    Long discountCard_id = null;                                                //номер дисконтной карты
                    if (Arrays.asList(transTypes).contains(transType))
                        discountCard_id = Long.parseLong(entryArr[7]);
                    transTypes = new Integer[]{2,12,32,163,173,412};
                    boolean reverse = Arrays.asList(transTypes).contains(transType);            //признак сторнирования

                    Cheque cheque = new Cheque(cheque_id, reverse, section_id, itemCode, price, amount, total, discount);
                    Entry entry = new Entry(transaction_id, date, time, transType, cashReg_id, cheque, cashier_id, discountCard_id);
                    list.add(entry);
                }
                catch(NumberFormatException e){
                    //e.printStackTrace();
                }
                catch(ArrayIndexOutOfBoundsException e1){
                    //e1.printStackTrace();
                }
                entryStr = reader.readLine();
            }
        }

    public static void main(String[] agrs){
        File file = new File("C:\\Users\\Алексей\\Desktop\\Транзакции.rep");
        try {
            EntryList enlist = new EntryList(file);
            for(Entry entry : enlist.list)
                System.out.println(entry.toString());
        }
        catch(FileNotFoundException e1){
            e1.printStackTrace();
        }
        catch(IOException e2){
            e2.printStackTrace();
        }


    }
    }