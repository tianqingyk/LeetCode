package leetcode.hard;

import javax.swing.*;
import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0332 {

    /**
     * 332. Reconstruct Itinerary
     */

    /**
     * Solution
     * Runtime: 9 ms, faster than 63.95% of Java online submissions for Reconstruct Itinerary.
     * Memory Usage: 42.6 MB, less than 92.99% of Java online submissions for Reconstruct Itinerary.
     */
    class Place{
        String name;
        List<Place> nexts = new ArrayList<>();

        Place(String name) {
            this.name = name;
        }
    }

    Map<String, Place> placeMap = new HashMap<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            Place from = placeMap.compute(ticket.get(0), (k,v) -> v == null ? new Place(k) : v);
            Place to = placeMap.compute(ticket.get(1), (k,v) -> v == null ? new Place(k) : v);
            from.nexts.add(to);
            from.nexts.sort((a,b) -> a.name.compareTo(b.name));
        }

        Place departure = placeMap.get("JFK");
        return dfs(departure);
    }

    public List<String> dfs(Place departure){
        if(departure.nexts.isEmpty()) return new ArrayList<>(){{add(departure.name);}};
        List<List<String>> endWithDeparture = new ArrayList<>();
        List<List<String>> endWithoutDeparture = new ArrayList<>();
        while (!departure.nexts.isEmpty()){
            Place next = departure.nexts.remove(0);
            List<String> tmpResult = dfs(next);
            if (tmpResult.get(tmpResult.size()-1).equals(departure.name)) endWithDeparture.add(tmpResult);
            else endWithoutDeparture.add(tmpResult);
        }
        List<String> result = new ArrayList<>();
        result.add(departure.name);
        for (List<String> r : endWithDeparture) {
            result.addAll(r);
        }
        for (List<String> r : endWithDeparture) {
            result.addAll(r);
            break;
        }
        return result;
    }
}
