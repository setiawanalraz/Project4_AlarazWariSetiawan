package vsga.mobile.project4_alarazwarisetiawan.model;

public class Data {
    private String id, name, address, contact;

    public Data() {
    }

    public Data(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
