////////////////////////////////////////////////////////////////////
// MARCEL JUNIOR WANDJI 1171044
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {

    public enum ItemType {Panino, Fritto, Bevande}
    private ItemType type;
    private String name;
    private double price;
    public MenuItem() {}
    public MenuItem(ItemType type, String name, double price) {
        this.type=type;
        this.name=name;
        this.price=price;
    }
    public ItemType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}