package code;

public class Contact {

    private Integer cid;
    private String firstName;
    private String lastName;
    private String groupC;
    private String phone;
    private String email;
    private String address;
    private byte[] pic;
    private int userId;


    public Contact() {}

    public Contact(Integer cid, String firstName, String lastName, String groupC, String phone, String email, String address, byte[] pic, int userId) {
        //super();
        this.cid = cid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupC = groupC;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.pic = pic;
        this.userId = userId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fullName) {
        this.firstName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupC() {
        return groupC;
    }

    public void setGroupC(String groupC) {
        this.groupC = groupC;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
