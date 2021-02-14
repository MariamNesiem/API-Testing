package jiraAPITest;

public class payload {
	
	public static String getSessionBody(){
		return"{\n" + 
				"    \"username\":\"mariam.nesiem\" ,\n" + 
				"    \"password\":\"12345678Gg\"\n" + 
				"}";
	}
	
	public static String addIssueIntoProjectBody(String projectID){
		return"{"+
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \""+projectID+"\""+
       "},"+
       "\"summary\": \"Issue for adding comment\","+
       "\"description\": \"Creating bug\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}}";
	}
}
