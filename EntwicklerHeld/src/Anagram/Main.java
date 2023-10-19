import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		

		class EasyStorage {

		    private HashMap<String, String> myHashMap = new HashMap<>();

		    public void store(String item, String repository) {

		        try {
		            myHashMap.put(item, repository);
		        } catch (IllegalArgumentException e) {
		            e.printStackTrace();
		        } catch (NullPointerException e) {
		            e.printStackTrace();
		        }
		    }

		    public Map<String, String> getAllData() {
		        return myHashMap;
		        System.out.println(myHashMap);
		    }

		    public String getRepository(String item) {
		        return myHashMap.get(item);
		        System.out.println(item);
		    }

		    public Set<String> getItems(String repository) {
		        for (String key : myHashMap.keySet()) {
		            String value = myHashMap.get(key);
		            return value;
		        }
		    }   
		}
		
	}
}