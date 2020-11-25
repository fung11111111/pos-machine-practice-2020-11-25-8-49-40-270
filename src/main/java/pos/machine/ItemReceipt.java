package pos.machine;

public class ItemReceipt extends ItemInfo{
    private int count;
    private int subCharge;
    public ItemReceipt(String barcode, String name, int price) {
        super(barcode, name, price);
        count = 0;
        subCharge = 0;
    }
    public void addItem(){
        count ++;
    }
    public int getCount(){
        return count;
    }
    public int getSubCharge(){
        return getPrice()*getCount();
    }
    public String getItemReceipt(){
        String receipt = "Name: " + getName() + ", Quantity: " + getCount() +
                ", Unit price: " + getPrice() + " (yuan), Subtotal: " + getSubCharge() + " (yuan)\n";
        return receipt;
    }


}
