package util;

public class StringManager {

	//*문자열의 길이가 1일 경우, 0을 붙여 2자리로 표현함 ex)01 02 03 ...
	public static String getNumString(int n) { //7
		String str=Integer.toString(n); //"7"
		
		if(str.length()<2) { //문자열의 길이가 1이라면
			str="0"+str;
		}
		return str;
		
	}
	
}