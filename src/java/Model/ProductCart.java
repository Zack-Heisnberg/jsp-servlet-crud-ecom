/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author zakaria
 */
public class ProductCart {
	private int id;
	private ProductModel product;
        private int qt;
	private float price;
	
	public ProductCart(ProductModel product, int qt, float price) {
		super();
		this.product = product;
		this.qt = qt;
                this.price = price;
	}

	public ProductCart(int id, ProductModel product, int qt, float price) {
		super();
                this.id = id;
		this.product = product;
		this.qt = qt;
                this.price = price;
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
     * @return the product
     */
    public ProductModel getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductModel product) {
        this.product = product;
    }

    /**
     * @return the qt
     */
    public int getQt() {
        return qt;
    }

    /**
     * @param qt the qt to set
     */
    public void setQt(int qt) {
        this.qt = qt;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

}