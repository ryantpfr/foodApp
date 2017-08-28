package org.toepfer.foodApp.web.bean;

/**
 * Created by toepfer on 8/20/2017.
 */
public class Restaurant implements Comparable<Restaurant> {

    private int id;

    private String name;

    private int votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Restaurant o) {
        if(o.getVotes() != this.votes){
            return o.getVotes() - votes;
        }
        return this.name.compareTo(o.getName());
    }
}
