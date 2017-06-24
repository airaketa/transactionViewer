package Logic;

public class Cheque {
    private long id;            //номер позиции
    private boolean reverse;    //признак сторнирования
    private Long section_id;    //номер секции
    private Long itemCode;      //код товара
    private Float price;        //цена
    private Float amount;       //количество
    private Float total;        //сумма чека
    private Float discount;     //сумма скидки

    public Cheque(long id, boolean reverse, Long section_id, Long itemCode, Float price, Float amount, Float total,Float discount) {
        this.id = id;
        this.reverse = reverse;
        this.section_id = section_id;
        this.itemCode = itemCode;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public boolean isReverse() {
        return reverse;
    }

    public Long getSection_id() {
        return section_id;
    }

    public Long getItemCode() {
        return itemCode;
    }

    public Float getTotal() {
        return total;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getPrice() {
        return price;
    }

    public Float getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cheque cheque = (Cheque) o;

        if (id != cheque.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", reverse=" + reverse +
                ", section_id=" + section_id +
                ", itemCode=" + itemCode +
                ", price=" + price +
                ", amount=" + amount +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}
