package Model;

import java.util.Date;
import java.util.List;

public class OrderModel {

    private int id;
    private ClientModel client;
    private List<ProductCart> products;
    private float total;
    private Date date;

    public OrderModel(ClientModel client, List<ProductCart> products, float total, Date date) {
        this.client = client;
        this.products = products;
        this.total = total;
        this.date = date;
    }

    public OrderModel(int id, ClientModel client, List<ProductCart> products, float total, Date date) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.total = total;
        this.date = date;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the client
     */
    public ClientModel getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(ClientModel client) {
        this.client = client;
    }

    /**
     * @return the products
     */
    public List<ProductCart> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductCart> products) {
        this.products = products;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }


}
