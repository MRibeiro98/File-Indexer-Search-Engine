import java.util.HashMap;
import java.util.Map;

public class FindTopic {

	static Map<String, String> topicToNum = new HashMap<>();
	static Map<String, String> numToTopic = new HashMap<>();
	//maps terms to topic numbers
	static {
		topicToNum.put("foreign minorities", "401");
		numToTopic.put("401", "foreign minorities");
		topicToNum.put("germany", "401");
		numToTopic.put("401", "germany");
		topicToNum.put("behavioral genetics", "402");
		numToTopic.put("402", "behavioral genetics");
		topicToNum.put("osteoporosis", "403");
		numToTopic.put("403", "osteoporosis");
		topicToNum.put("ireland", "404");
		numToTopic.put("404", "ireland");
		topicToNum.put("peace talks", "404");
		numToTopic.put("404", "peace talks");
		topicToNum.put("cosmic events", "405");
		numToTopic.put("405", "cosmic events");
		topicToNum.put("parkinson's disease", "406");
		numToTopic.put("406", "parkinson's disease");
		topicToNum.put("poaching", "407");
		numToTopic.put("407", "poaching");
		topicToNum.put("wildlife preserves", "407");
		numToTopic.put("407", "wildlife preserves");
		topicToNum.put("tropical storms", "408");
		numToTopic.put("408", "tropical storms");
		topicToNum.put("legal", "409");
		numToTopic.put("409", "legal");
		topicToNum.put("pan am", "409");
		numToTopic.put("409", "pan am");
		topicToNum.put("103", "409");
		numToTopic.put("409", "103");
		topicToNum.put("schengen agreement", "410");
		numToTopic.put("410", "schengen agreement");
		topicToNum.put("salvaging", "411");
		numToTopic.put("411", "salvaging");
		topicToNum.put("shipwreck", "411");
		numToTopic.put("411", "shipwreck");
		topicToNum.put("treasure", "411");
		numToTopic.put("411", "treasure");
		topicToNum.put("airport security", "412");
		numToTopic.put("412", "airport security");
		topicToNum.put("steel production", "413");
		numToTopic.put("413", "steel production");
		topicToNum.put("cuba", "414");
		numToTopic.put("414", "cuba");
		topicToNum.put("sugar", "414");
		numToTopic.put("414", "sugar");
		topicToNum.put("exports", "414");
		numToTopic.put("414", "exports");
		topicToNum.put("drugs", "415");
		numToTopic.put("415", "drugs");
		topicToNum.put("golden triangle", "415");
		numToTopic.put("415", "golden triangle");
		topicToNum.put("three gorges project", "416");
		numToTopic.put("416", "three gorges project");
		topicToNum.put("creativity", "417");
		numToTopic.put("417", "creativity");
		topicToNum.put("quilts", "418");
		numToTopic.put("418", "quilts");
		topicToNum.put("income", "418");
		numToTopic.put("418", "income");
		topicToNum.put("recycle", "419");
		numToTopic.put("419", "recycle");
		topicToNum.put("automobile tires", "419");
		numToTopic.put("419", "automobile tires");
		topicToNum.put("carbon monoxide poisoning", "420");
		numToTopic.put("420", "carbon monoxide poisoning");
	}

	// compares terms to the ones in the map to return results
	public String findTopic(String term) {
		String num = topicToNum.get(term.toLowerCase());
		return num == null ? "E404" : num;
	}

	//user can search with topic ID 
	public String topicNumToName(String term) {
		String topic = numToTopic.get(term.toLowerCase());
		return topic == null ? term : topic;
	}

}
