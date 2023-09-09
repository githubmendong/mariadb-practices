package bookmall.vo;

public class OrderVo {

    private int no;
    private int member_no;
    private String orders_no;
    private int price;
    private String receive_address;
    private String name;
    public OrderVo(int member_no, String orders_no, int price, String receive_address, String name) {
        this.member_no = member_no;
        this.orders_no = orders_no;
        this.price = price;
        this.receive_address = receive_address;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderVo [no=" + no + ","+ "name : " + name+ " , member_no=" + member_no + ", orders_no=" + orders_no + ", price="
                + price + ", address=" + receive_address + "]";
    }

    public String getName(){
        return name;
    }

    public String setName(){
        return name;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public int getMember_no() {
        return member_no;
    }
    public void setMember_no(int member_no) {
        this.member_no = member_no;
    }
    public String getOrders_no() {
        return orders_no;
    }
    public void setOrders_no(String orders_no) {
        this.orders_no = orders_no;
    }
    public int getOrders_price() {
        return price;
    }
    public void setOrders_price(int orders_price) {
        this.price = orders_price;
    }
    public String getAddress() {
        return receive_address;
    }
    public void setAddress(String address) {
        this.receive_address = address;
    }
}