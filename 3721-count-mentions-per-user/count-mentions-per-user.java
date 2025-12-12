import java.util.*;

class Solution {
    public int[] countMentions(int N, List<List<String>> events) {

        int[] counts = new int[N];
        int[] offline = new int[N];

        // MESSAGE events come after OFFLINE if timestamps equal
        Map<String, Integer> k = new HashMap<>();
        k.put("MESSAGE", 1);
        k.put("OFFLINE", 0);

        // Sort events by timestamp, and type priority
        events.sort((a, b) -> {
            int t1 = Integer.parseInt(a.get(1));
            int t2 = Integer.parseInt(b.get(1));
            if (t1 != t2) return t1 - t2;
            return k.get(a.get(0)) - k.get(b.get(0));
        });

        for (List<String> e : events) {

            String type = e.get(0);
            int ts = Integer.parseInt(e.get(1));
            String mentions = e.get(2);

            if (type.equals("MESSAGE")) {

                // MESSAGE mentions
                if (mentions.equals("ALL")) {

                    for (int i = 0; i < N; i++) counts[i]++;

                } else if (mentions.equals("HERE")) {

                    for (int i = 0; i < N; i++) {
                        if (ts >= offline[i]) counts[i]++;
                    }

                } else {

                    // list of ids: "id1 id2 id3"
                    String[] parts = mentions.split(" ");
                    for (String m : parts) {
                        if (m.startsWith("id")) {
                            int id = Integer.parseInt(m.substring(2));
                            counts[id]++;
                        }
                    }
                }

            } else { 
                // OFFLINE event
                int uid = Integer.parseInt(mentions);
                offline[uid] = ts + 60;
            }
        }

        return counts;
    }
}
