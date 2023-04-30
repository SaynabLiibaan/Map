package handin2.ModelPart;

import java.io.Serializable;

public class Address implements Serializable {
    Long nodeId;
    String city; 
    String postcode; 
    String street; 
    String housenumber;

    public Address(Long nodeId, String city, String postcode, String street, String housenumber) {
        this.nodeId = nodeId;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.housenumber = housenumber;
    }

    public String getAddress(){
        return street + " " + housenumber + ", " + city + " " + postcode;
    }
    
}
