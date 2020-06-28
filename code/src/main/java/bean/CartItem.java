package bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CartItem implements Serializable {
    private String pName; // 商家名
    private String gName; // 商品名
    private double price;
    private int amount; // 数量
}
