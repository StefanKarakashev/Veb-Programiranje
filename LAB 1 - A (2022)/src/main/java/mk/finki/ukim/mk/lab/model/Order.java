package mk.finki.ukim.mk.lab.model;


import lombok.Getter;

@Getter
public class Order {
    private String balloonColor;
    private String balloonSize;
    private String clientName;
    private String clientAdress;
    Long orderId;

    public Order(String balloonColor, String clientName, String clientAdress) {
        this.balloonColor = balloonColor;
        this.clientName = clientName;
        this.clientAdress = clientAdress;
    }

    public Order(String balloonColor, String balloonSize, String clientName, String clientAdress, Long orderId) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.clientName = clientName;
        this.clientAdress = clientAdress;
        this.orderId = orderId;
    }
}
