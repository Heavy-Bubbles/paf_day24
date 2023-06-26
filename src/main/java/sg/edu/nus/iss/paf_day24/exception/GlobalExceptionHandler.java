package sg.edu.nus.iss.paf_day24.exception;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BankAccountNotFoundException.class)
    public ModelAndView handleBankAccountNotFoundException(BankAccountNotFoundException ex,
    HttpServletRequest request){
        
        // forming the custom error message
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(404);
        errorMessage.setTimeStamp(new Date());
        errorMessage.setMessage("Bank account does not exist.");
        errorMessage.setDescription(request.getRequestURI());

        // return the error page with injected error message
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errorMessage);
        return mav;
    
    }
}
