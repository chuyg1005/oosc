package bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Item implements Serializable {
    private String name;
    private double price;
    private String description;
}
