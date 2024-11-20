package prob05;

public class Sol05 {
	public static void main(String[] arg) {

		int array[] = {5, 9, 3, 8, 60, 20, 1};
		int count = array.length;

		System.out.println("Before Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}

		// 정렬 알고리즘이 적용된 코드를 여기에 작성합니다.
		
		
		for (int j=0; j<count; j++) {
			for (int i = 0; i < count-1; i++) {
				if(array[i]<array[i+1]) {
					int k=array[i];
					array[i]=array[i+1];
					array[i+1]=k;
				}
			}
		}
		
		
		System.out.println('\n');
		System.out.println("After Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}
	}
}