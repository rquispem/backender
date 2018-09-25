package com.glovoapp.backender.exception;

public class NoDescriptionsValuesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5709991526891832623L;
	
	public NoDescriptionsValuesException(String message) {
        super(message);
    }

    public NoDescriptionsValuesException(String message, Throwable cause) {
        super(message, cause);
    }

}
