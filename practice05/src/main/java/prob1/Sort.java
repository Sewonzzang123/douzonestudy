package prob1;

public class Sort {

	public static void main(String[] arg) {

		int array[] = { 5, 9, 3, 8, 60, 20, 1 };
		int count = array.length;

		System.out.println("Before sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}

		//
		// 정렬 알고리즘이 적용된 코드를 여기에 작성합니다.
		bubble_sort(array,count);

		// 결과 출력
		System.out.println("\nAfter Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}

	}

	public static void bubble_sort(int list[], int n) {
		int i, j, temp;

		for (i = n - 1; i > 0; i--) {
			// 0 ~ (i-1)까지 반복
			for (j = 0; j < i; j++) {
				// j번째와 j+1번째의 요소가 크기 순이 아니면 교환
				if (list[j] < list[j + 1]) {
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}
}