package sg.edu.nus.iss.paf_day24.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private int statusCode;

    private Date timeStamp;

    private String message;

    private String description;

    
}
