
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization;
import Business.Restaurant.RestaurantDirectory;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public abstract class Enterprise extends Organization{
    private String name;
    private String address;
    private ArrayList<Items> itemsList;
    private EnterpriseType enterpriseType;
    private RestaurantDirectory restaurantDirectory;

     public Enterprise(String name,String address,EnterpriseType type){
        super(name);
        this.address=address;
        this.enterpriseType=type;
        this.name=name;
        restaurantDirectory=new RestaurantDirectory();
        
        this.itemsList =new ArrayList();
    }

    public ArrayList<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<Items> itemsList) {
        this.itemsList = itemsList;
    }
     
    
    public enum EnterpriseType{
        Restaurant("Restaurant");
        
        private String value;
        
        private EnterpriseType(String value){
            this.value=value;
        }
        public String getValue() {
            return value;
        }
        @Override
        public String toString(){
        return value;
    }
    }

    public RestaurantDirectory getRestaurantDirectory() {
        return restaurantDirectory;
    }

    public void setRestaurantDirectory(RestaurantDirectory restaurantDirectory) {
        this.restaurantDirectory = restaurantDirectory;
    }
    
            
    public Items createMenuItem(){
        Items item = new Items(); 
        this.itemsList.add(item);
        return item;
    } 
      
    
     public void deleteItem(Items item){
        itemsList.remove(item); 
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }
    
      @Override
        public String toString(){
        return name;
    }
}
