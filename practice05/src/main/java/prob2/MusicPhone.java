package prob2;

public class MusicPhone extends Phone {
	
	@Override
	public void execute(String function) {
		if(function.equals("음악")) {
			System.out.println("음악을 듣습니다.");
		}else {
		super.execute(function);
		}
	}
}
