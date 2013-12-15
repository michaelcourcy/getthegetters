package mic.prod.getthegetters;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.ClassUtils;

/**
 * This class aims to provide all the getters and their value on an object.
 * It works recursively on not null property or not null collection.
 *
 */
public class GetTheGetters 
{
	
	/**
	 * An implementation of this handler 
	 * must be passed to the constructor.
	 */
	private GetterHandler handler;
	
	/**
	 * decide if we also want to have the getClass method on the list.
	 */
	private boolean addGetClassGetters = false;
	
	
	
	/**
	 * The constructor take the handler.
	 * @param handler
	 */
	public GetTheGetters(GetterHandler handler){
		if (handler==null){
			throw new RuntimeException("Yous must provide a not null handler");
		}
		this.handler = handler;
	}


	/**
	 * Get all the plublic getters of objectToAnalyse.  
     * 
     * Every time a getter of a primitive type is found, the pair getter/Value 
	 * is passed to the getterHandler.
	 * 
	 * See JUnit test to see example.
	 *  
	 * @param objectToAnalyse
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IntrospectionException
	 */
    public void getTheGetters(Object objectToAnalyse) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IntrospectionException
    {
    	getTheGetters(objectToAnalyse, "");
    }
    
    
    /**
     * this method is called recursively on each subobject or elements of 
     * collections. Every time we go a level down we append the name of the getter
     * to previous path.
     * @param objectToAnalyse 
     * @param previousPath 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IntrospectionException
     */
    private void getTheGetters(Object objectToAnalyse, String previousPath) throws IllegalAccessException,  NoSuchMethodException, IntrospectionException{
    	
    	PropertyDescriptor[] propertyDescriptors;
    			
		if (addGetClassGetters){
			propertyDescriptors = Introspector.getBeanInfo(objectToAnalyse.getClass()).getPropertyDescriptors();
		}else{
			//get all the getters except getClass. 
			propertyDescriptors = Introspector.getBeanInfo(objectToAnalyse.getClass(),Object.class).getPropertyDescriptors();
		}
    	
    	for(PropertyDescriptor propertyDescriptor : propertyDescriptors){

    		//we get the value of the getter 
    		Object value;
			try {
				value = propertyDescriptor.getReadMethod().invoke(objectToAnalyse, new Object[]{});
			} catch (InvocationTargetException e) {
				//if the invocation of the getter throw an error 
				//we don't want to block the whole process thus 
				//we just assign the value to a string 
				//with an easy to understand message.
				value = "ERROR " + e.getCause();
			}
    		
    		
    		String methodName = previousPath + 
					(!previousPath.equals("")?".":"") + propertyDescriptor.getReadMethod().getName() + "()";
    		
    		//if the object is null or if it's a primitive type we pass it to the handler 
    		if (value==null || isPrimitiveOrString(value.getClass())){
    			handler.handle(methodName, value);
    		}else
    		//if it's an array
    		if (value.getClass().isArray())
    		{
    			//we can safely cast it.
    			Object[] values = (Object[]) value;
    			for (int i=0; i < values.length; i++){
    				getTheGetters(values[i], methodName + "[" + i + "]");
    			}    			
    		}
    		else
    		//if it's a collection 
    		if (value instanceof Collection<?>){
    			int i = 0;
    			Iterator<Object> it = ((Collection<Object>) value).iterator();
    			while (it.hasNext()){
    				getTheGetters(it.next(), methodName + "[" + i++ + "]");
    			}
    		}
    		//otherwise go inside the object.
    		else{
    			getTheGetters(value, methodName);
    		}
    	    
    	    
    	}
    }
    
    private boolean isPrimitiveOrString(Class<?> clazz){
    	if (clazz.equals(String.class)){
    		return true;
    	}else{
    		return ClassUtils.isPrimitiveOrWrapper(clazz);
    	}    	
    }
    
    /**
     * A simple interface that you give to the GetTheGetters instance 
     * to handle a pair getter / value.
     * 
     * @author michael
     *
     */
    public interface GetterHandler {
    	
    	/**
    	 * Implements this method to decide what you do 
    	 * with the pair getter / value. In the 
    	 * JUnit sample we just print the result on the console.
    	 * 
    	 * @param getter
    	 * @param value
    	 */
    	void handle(String getter, Object value);
    	
    }
    
    
    /**
     * Call true on this method il you want to operate also on 
     * the getClass method.
     * 
     * @param addGetClassGetters
     */
    public void setAddGetClassGetters(boolean addGetClassGetters) {
		this.addGetClassGetters = addGetClassGetters;
	}
    
    
    
}
