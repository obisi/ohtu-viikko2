/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author bisi
 */
public class QueryBuilder {

    Matcher query;

    public QueryBuilder() {
        query = new All();
    }

    public Matcher build() {
        Matcher temp = query;
        query = new All();
        return temp;
    }

    public QueryBuilder playsIn(String team) {
        this.query = new And(query, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.query = new And(query, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.query = new And(query, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher m1, Matcher m2){
        this.query = new Or(m1, m2);
        return this;
    }

}
