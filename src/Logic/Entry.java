package Logic;

import java.util.Date;

public class Entry {
    private long id;
    private Date date;              //дата
    private Date time;              //время
    private int transType;          //тип транзакции
    private long cashReg_id;        //номер кассы
    private Cheque cheque;          //чек - номер чека, вид чека, сумма чека
    private long cashier_id;        //код кассира
    private Long discountCard_id;   //номер дисконтной карты

    public Entry(long id,Date date, Date time, int transType,long cashReg_id, Cheque cheque, long cashier_id, Long discountCard_id) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.transType = transType;
        this.cashReg_id = cashReg_id;
        this.cheque = cheque;
        this.cashier_id = cashier_id;
        this.discountCard_id = discountCard_id;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    public long getCashReg_id() {
        return cashReg_id;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public long getCashier_id() {
        return cashier_id;
    }

    public Long getDiscountCard_id() {
        return discountCard_id;
    }

    public int getTransType() {
        return transType;
    }

    public long getChequeId() { return cheque.getId(); }

    public Float getTotal() {
        if (cheque.getTotal()!=null) return cheque.getTotal();
        else return cheque.getDiscount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (id != entry.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", transType=" + transType +
                ", cashReg_id=" + cashReg_id +
                ", cheque=\n" + cheque.toString() +
                "\n, cashier_id=" + cashier_id +
                ", discountCard_id=" + discountCard_id +
                '}';
    }
}
