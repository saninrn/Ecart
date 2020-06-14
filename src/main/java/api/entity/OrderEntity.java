package api.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Orders")
@NamedQueries({
        @NamedQuery(name = "findStatusOfItem", query = "select o.status from OrderEntity o where o.orderId=:orderId"),
        @NamedQuery(name = "findOrder", query = "select o from OrderEntity o where o.orderId=:orderId"),


})
public class OrderEntity {

    @Column(name = "orderid")
    @Id
    private int orderId;
    @Column(name = "status")
    private String status;
    @Column(name = "recipientaddress")
    private String recipientAddress;
    @ManyToMany(mappedBy = "orders")
    private List<CatalogItemEntity> items;

    public List<CatalogItemEntity> getItems() {
        return items;
    }

    public void setItems(List<CatalogItemEntity> items) {
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }
}
