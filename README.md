Utility class that get recursively all the getters of an object. 
Useful when you work with a template technology (JSP, Freemarkers, Jasper ...) and you need to know all the available getters and their values.

Here is an example 

Obj1 obj1 = new Obj1();

GetTheGetters getTheGetters = new GetTheGetters(new GetTheGetters.GetterHandler() {
	
	public void handle(String getter, Object value) {
		System.out.println(getter + " : " + value);
	}
	
});

getTheGetters.getTheGetters(obj1);


-- RESULT IN THE CONSOLE --

getiThrowExceptionIfYouTryToGetMe() : ERROR java.lang.RuntimeException: I warn you ....

getPp1() : pop1

getPp2() : pop2

getSousObj().getObj3().getPp3() : hop

getSousObj().getObj3().getPp4() : hup

getSousObj().getPp3() : p_3

getSousObj().getPp4() : p_4

getSousObjNull() : null

getSousObjs()[0].getObj3().getPp3() : hop

getSousObjs()[0].getObj3().getPp4() : hup

getSousObjs()[0].getPp3() : p_1_3

getSousObjs()[0].getPp4() : p_1_4

getSousObjs()[1].getObj3().getPp3() : hop

getSousObjs()[1].getObj3().getPp4() : hup

getSousObjs()[1].getPp3() : p_2_3

getSousObjs()[1].getPp4() : p_2_4

getSousObjs()[2].getObj3().getPp3() : hop

getSousObjs()[2].getObj3().getPp4() : hup

getSousObjs()[2].getPp3() : p_3_3

getSousObjs()[2].getPp4() : p_3_4