package mic.prod.getthegetters;

public class Obj2 {
	private String pp3 = "pop3";
	private String pp4 = "pop4";

	public Obj2(String pp3, String pp4) {
		super();
		this.pp3 = pp3;
		this.pp4 = pp4;
	}

	public String getPp3() {
		return pp3;
	}

	public void setPp3(String pp3) {
		this.pp3 = pp3;
	}

	public String getPp4() {
		return pp4;
	}

	public void setPp4(String pp4) {
		this.pp4 = pp4;
	}

	private Obj3 obj3 = new Obj3("hop", "hup");

	public Obj3 getObj3() {
		return obj3;
	}

	public void setObj3(Obj3 moiMeme) {
		this.obj3 = moiMeme;
	}

}