package home.shop.demo.domain;

//Accepted values for status are:

// - 'new'        -> the order is being created. The client decides a whishlist.
//                   orders with status 'new' have not been added to the Orders table yet.
// - 'processing' -> the order is currently in process. The client has submitted the request and the order needs
//                   to be handled by an employee. In this state the products have not been shipped yet.
//                   in this state the order can be cancelled.
// - 'cancelled'  -> the order was cancelled by the user and does not need further processing.
//                   The order and all corresponding orderdetails are deleted
// - 'sent'       -> the order has been executed and the products left the storages

//Default value when creating a new order is -> 'new'
public enum OrderStatus {
    NEW("new"),
    PROCESSING("processing"),
    CANCELLED("cancelled"),
    SENT("sent");

    private final String stringValue;
    OrderStatus(final String s) { stringValue = s; }

    @Override
    public String toString() { return stringValue; }
}
