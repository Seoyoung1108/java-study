package prob05;

public class Sol05 {
	public static void main(String[] args) {

		/* 코드 작성 */
		for(int i=1; i<=100; i++) {
			/* 숫자를 문자로 바꾸기 -> 반복문으로 3의 배수 있는지 보기 */
			String str="";		
			String number = String.valueOf(i);
			for(int j=0;j<number.length();j++) {
				if(number.charAt(j)=='3'||number.charAt(j)=='6'||number.charAt(j)=='9') {
					str=str+"짝";
				}
			}
			if(str.length()!=0) {
				System.out.println(number+" "+str);
			}			
		}
	}
}
