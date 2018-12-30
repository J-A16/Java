import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 input
 
20th Oct 2052
6th Jun 1933
26th May 1960
20th Sep 1958
16th Mar 2068
25th May 1912
16th Dec 2018
26th Dec 2061
4th Nov 2030
28th Jul 1963

output

2052-10-20
1933-06-06
1960-05-26
1958-09-20
2068-03-16
1912-05-25
2018-12-16
2061-12-26
2030-11-04
1963-07-28
 */



class FormatDates {

	
	public static void main(String[] args){
		String[] datesArray = {"20th Oct 2052",
				"6th Jun 1933",
				"26th May 1960",
				"20th Sep 1958",
				"16th Mar 2068",
				"25th May 1912",
				"16th Dec 2018",
				"26th Dec 2061",
				"4th Nov 2030",
				"28th Jul 1963"};
		
		ArrayList<String> dates = new ArrayList<String>();
		
		for(String date: datesArray){
			dates.add(date);
		}
		
		dates = (ArrayList<String>) reformatDate(dates);
		
		for(String date: dates){
			System.out.println(date);
		}
		
		
	}
	
	
	
    /*
     * Complete the 'reformatDate' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY dates as parameter.
     */

    public static List<String> reformatDate(List<String> dates) {
    // Write your code here
        
        ArrayList<String> formattedDates = new ArrayList<String>();
        
        HashMap<String, String> months = new HashMap<String, String>();
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");
        
        for(int i = 0; i < dates.size(); i++){
            String[] splitDate = dates.get(i).split(" ");
            
            String formattedDate = splitDate[2] + "-";
            
            formattedDate += months.get(splitDate[1]) + "-";
            
            String day = splitDate[0];
            
            for(int j = 0; j < day.length(); j++){
                if(Character.isAlphabetic(day.charAt(j))){
                    day = day.substring(0, j);
                    break;
                }
            }
            
            if(day.length() == 1){
                formattedDate += "0" + day;
            } else {
                formattedDate += day;
            }
            
            formattedDates.add(formattedDate);
        }
        
        return formattedDates;
    }

}

