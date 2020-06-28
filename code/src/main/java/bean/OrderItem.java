package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
public class OrderItem implements Serializable {
    private String cName;
    private String pName;
    private String gName;
    private double price;
    private int amount;
    private double total; // 总价：由于存在优惠，可能总价!=单价x数量
    private Date date;
}
