/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author mahit
 */
public class OrderDirectory {
     private ArrayList<Order> orderList;

    public OrderDirectory() {
        orderList = new ArrayList();
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

     public Order createOrder(){
        Order order = new Order(); 
        orderList.add(order);
        return order;
    }
     
      public void deleteOrder(Order order){
        orderList.remove(order); 
    }
}
