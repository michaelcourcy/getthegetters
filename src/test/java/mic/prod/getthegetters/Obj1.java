package mic.prod.getthegetters;

public class Obj1 {
	private String pp1 = "pop1";
	private String pp2 = "pop2";
	private int iThrowExceptionIfYouTryToGetMe = 3;

	private Obj2[] sousObjs = new Obj2[] { new Obj2("p_1_3", "p_1_4"),
			new Obj2("p_2_3", "p_2_4"), new Obj2("p_3_3", "p_3_4"), };
	private Obj2 sousObj = new Obj2("p_3", "p_4");
	private Obj2 sousObjNull = null;

	public Obj2 getSousObjNull() {
		return sousObjNull;
	}

	public void setSousObjNull(Obj2 sousObjNull) {
		this.sousObjNull = sousObjNull;
	}

	public String getPp1() {
		return pp1;
	}

	public void setPp1(String pp1) {
		this.pp1 = pp1;
	}

	public String getPp2() {
		return pp2;
	}

	public void setPp2(String pp2) {
		this.pp2 = pp2;
	}

	public Obj2[] getSousObjs() {
		return sousObjs;
	}

	public void setSousObjs(Obj2[] sousObjs) {
		this.sousObjs = sousObjs;
	}

	public Obj2 getSousObj() {
		return sousObj;
	}

	public void setSousObj(Obj2 sousObj) {
		this.sousObj = sousObj;
	}

	public int getiThrowExceptionIfYouTryToGetMe() {
		throw new RuntimeException("I warn you ....");
	}

	public void setiThrowExceptionIfYouTryToGetMe(
			int iThrowExceptionIfYouTryToGetMe) {
		this.iThrowExceptionIfYouTryToGetMe = iThrowExceptionIfYouTryToGetMe;
	}

}