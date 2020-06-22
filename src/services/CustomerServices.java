package services;

import dao.CustomerDao;
import model.customer.Customer;

import java.util.List;

public class CustomerServices {
    CustomerDao customerDao = new CustomerDao();

    public Customer signUp(Customer customer) {
        customerDao.addCustomer(customer);
        customer.setId(customerDao.getCustomerCounter());
        return customer;
    }

    public Customer findCustomer(Customer signedInCustomer) {
        return customerDao.fetchCustomer(signedInCustomer);
    }

    public List<Customer> getCustomersList() {
        return customerDao.fetchAllCustomers();
    }
}