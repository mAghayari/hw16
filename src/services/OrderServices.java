package services;

import dao.OrderDao;
import model.Order;
import model.OrderItem;

import java.util.List;

public class OrderServices {

    public Order cancelOrder(Order order) {
        ProductServices productServices = new ProductServices();
        List<OrderItem> orderedItems = order.getOrderItems();

        productServices.getAllSoledItemsBack(orderedItems);
        return order;
    }

    public double getTotalCost(Order order) {
        order.setTotalCost(order.getOrderItems());
        return order.getTotalCost();
    }

    public void addingOrder(Order order) {
        OrderDao orderDao = new OrderDao();
        orderDao.addAOrder(order);
    }
}