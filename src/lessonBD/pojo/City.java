package lessonBD.pojo;

public class City {
    private int id;
    private String name;
    private String moreInformation;

    public City(int id, String name, String moreInformation) {
        this.id = id;
        this.name = name;
        this.moreInformation = moreInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", moreInformation=" + moreInformation +
                '}';
    }
}
