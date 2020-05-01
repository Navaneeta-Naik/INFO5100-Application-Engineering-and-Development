/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Customer;

import Business.Organization;
import java.util.ArrayList;

/**
 *
 * @author harold
 */
public class CustomerDirectory {
      private ArrayList<Organization> organizationList;

    public CustomerDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Organization.Type type){
        Organization organization = null;
      if (type.getValue().equals(Organization.Type.Customer.getValue())){
            organization = new Customer();
            organizationList.add(organization);
        }
        return organization;
    }
    
   
      public void deleteCustomer(Customer customer){
        organizationList.remove(customer); 
    }
    
        public Organization searchOrganization(String organizationName){
        for (Organization organization: organizationList) {
            if (organization.getName().equals(organizationName)) {
                return organization;
            }
        }
        return null;
    }
}
