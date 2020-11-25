package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    private ArrayList<ItemReceipt> itemReceiptArrayList;

    public PosMachine() {
        this.itemReceiptArrayList = new ArrayList<>();
    }

    public String printReceipt(List<String> barcodes) {
        createItemByBarcodes(barcodes);
        return generateFinalReceipt(concatReceipt());
    }

    private void createItemByBarcodes(List<String> barcodes) {
        List<ItemInfo> itemList = getItemList();
        for (String barcode : barcodes) {
            for (ItemInfo item : itemList) {
                if (barcode.equals(item.getBarcode())) {
                    ItemReceipt itemReceipt = new ItemReceipt(item.getBarcode(), item.getName(), item.getPrice());
                    if (!isItemInList(itemReceipt)) {
                        itemReceiptArrayList.add(itemReceipt);
                    }
                }
            }
        }
    }
    private String generateFinalReceipt(String receipt) {
        String header = "***<store earning no money>Receipt***\n";
        String divider = "----------------------\n";
        String footer = "**********************";
        return header + receipt + divider + getTotalCharge() + footer;
    }
    private String concatReceipt(){
        String receipt = "";
        for (ItemReceipt ir: itemReceiptArrayList){
            receipt += ir.getItemReceipt();
        }
        return receipt;
    }
    private Boolean isItemInList(ItemReceipt itemReceipt){
        Boolean inList = false;
        for(ItemReceipt ir: itemReceiptArrayList){
            if(ir.getBarcode().equals(itemReceipt.getBarcode())){
                inList = true;
                ir.addItem();
                break;
            }
        }
        return inList;
    }
    private String getTotalCharge(){
        int total = 0;
        for(ItemReceipt ir: itemReceiptArrayList){
            total += ir.getSubCharge();
        }
        return String.format("Total: %s (yuan)\n", total);
    }

    private List<ItemInfo> getItemList() {
        return ItemDataLoader.loadAllItemInfos();
    }
}
