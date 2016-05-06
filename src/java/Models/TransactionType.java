/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 0aps
 */
@Entity
@Table(name = "TransactionType")
public class TransactionType implements java.io.Serializable {
    
    @Id @GeneratedValue
    @Column(name = "TYPE_TRANSACTION_ID")
    private int id;
     
    @Column(name = "TYPE_TRANSACTION_DESCRIPTION")
    private String description;
    
     public TransactionType() {
    }
     
    public TransactionType(int id) {
        this.id = id;
    }
    
    public TransactionType(int id, String description) {
       this.id = id;
       this.description = description;
    }
    
    @JsonCreator
    public static Transaction Create(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Transaction module = null;
        module = mapper.readValue(jsonString, Transaction.class);
        return module;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
