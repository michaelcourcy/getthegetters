package mic.prod.getthegetters;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GetTheGettersTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public GetTheGettersTest(String testName) {
		super(testName);
	}

	/**
	 * @throws IntrospectionException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void testApp() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			IntrospectionException {
		Obj1 obj1 = new Obj1();
		GetTheGetters getTheGetters = new GetTheGetters(
				new GetTheGetters.GetterHandler() {

					public void handle(String getter, Object value) {
						System.out.println(getter + " : " + value);
					}
				});
		getTheGetters.getTheGetters(obj1);

	}
}
