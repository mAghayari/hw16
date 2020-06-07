package dto.cart;

import dto.address.Address;
import dto.customer.Customer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<CartItem> cartItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    private Address address;
    private double totalCost;
    private Timestamp cartDate;

    public int getId() {
        return id;
    }

    public void setId(int orderID) {
        this.id = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(List<CartItem> orderedItems) {
        totalCost = 0;
        orderedItems.forEach(orderItem -> totalCost += orderItem.getCount() * orderItem.getProduct().getPrice());
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> orderedItems) {
        this.cartItems = orderedItems;
    }

    public Timestamp getCartDate() {
        return cartDate;
    }

    public void setDate() {
        java.util.Date now = new java.util.Date();
        cartDate = new Timestamp(now.getTime());
    }

    @Override
    public String toString() {
        String cartString ="cart: "+ id +
                "\ntotalCost: " + totalCost +
                "\ncartDate: " + cartDate +
                "\ncartItems: ";
        for (CartItem cartItem : cartItems) {
            cartString += cartItem.getId()+" ";
        }
        cartString += "\n*********************************\n";
        return cartString;
    }
}