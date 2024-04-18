package dao;

public class Test {
	private int id;
    private String text;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test(int id, String text) {
        this.id = id;
        this.text = text;
    }
    public Test (String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }
}


