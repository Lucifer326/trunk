package org.jeecgframework.web.formula.analysis;

import org.springframework.stereotype.Service;

@Service
public class FormulaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8228066959605196905L;

	public FormulaException(){  
        super();  
    }  
    public FormulaException(String msg){  
        super(msg);  
    }  
}
