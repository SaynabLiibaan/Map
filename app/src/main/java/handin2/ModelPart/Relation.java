package handin2.ModelPart;

import java.io.Serializable;
import java.util.HashMap;

public class Relation implements Serializable{ //SERIALIZABLE IS A NEED!!!!! DONT DELETE IT OR YOU WILL DIE
    String type;
    Long id;
    String areaType;
    String name;

    HashMap <Long,String> id2role;

    public Relation(Long id, HashMap <Long,String> id2role, String type, String areaType, String name)  {
        this.type = type;
        this.id = id;
        this.areaType = areaType;
        this.id2role = id2role; 
        this.name = name;
    }
    
    public String getType(){
        return type;
    }

    public Long getId(){
        return id;
    }

    public HashMap<Long,String> getMap(){
        return id2role;
    }

    public String getAreaType(){
        return areaType;
    }
<<<<<<< HEAD
}
=======

    public String getName(){
        return name;
    }
}
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
