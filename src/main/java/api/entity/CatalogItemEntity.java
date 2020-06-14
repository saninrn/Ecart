package api.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "catalog_item")
@NamedQuery(name = "findAllItems", query = "select c from CatalogItemEntity c")
public class CatalogItemEntity {


    @Id
    @Column(name = "itemid")
    //@GeneratedValue(strategy = GenerationType.AUTO) //made bigserial, with db taking care.
    private int itemId;
    @Column(name = "itemname")
    private String itemName;
    @Column(name = "itemdescription")
    private String itemDescription;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "maxretailprice")
    private double maxRetailPrice;
    @Column(name = "reviews")
    private String reviews;
    @Column(name = "rating")
    private Integer rating;
    /*NOTE IF RELATIONSHIP NOT DEFINED PROPERLY, THEN HIBERNATE ERRORS*/
    //many to many via intermediate table
    @ManyToMany(targetEntity = OrderEntity.class, cascade = CascadeType.ALL)
    @JoinTable(name = "Products_Orders", joinColumns = { @JoinColumn(name = "itemid") }, inverseJoinColumns = { @JoinColumn(name = "orderid") })
    private List<OrderEntity> orders;

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public void setMaxRetailPrice(double maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}







