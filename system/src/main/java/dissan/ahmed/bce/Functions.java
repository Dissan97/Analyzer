package dissan.ahmed.bce;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Functions {


    /**
     * this function is the loss function l: R^p x R^p -> [0, inf) difference measure between y~ and y
     * l(y~,y) = || y~ - y || ^2
     * @param yActual the value predicted
     * @param yReal the expected value
     * @return the square Euclidean distance
     */
    public double lossFunction(@NotNull RealVector yActual,@NotNull RealVector yReal){
           RealVector diff = yActual.subtract(yReal);
        // diff.dotProduct(diff): diff'*diff = ||diff||^2
           return diff.dotProduct(diff);
    }


}
