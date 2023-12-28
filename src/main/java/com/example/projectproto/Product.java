package com.example.projectproto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product") // Use the desired collection name
public class Product {

    @Id
    private String productid;

    @Setter
    @Getter
    private String ProductName;
    // Add other fields as needed

    // Getters and setters

    public String getProductId() {
        return productid;
    }

    public void setProductId(String ProductId) {
        this.productid = ProductId;
    }

}
