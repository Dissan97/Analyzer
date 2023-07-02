package dissan.ahmed.bce.util;

import org.apache.commons.math.linear.RealMatrix;
import org.jetbrains.annotations.Contract;

public class Functions {

    /**
     * ReLu function that returns 0 if z is negative or z otherwise
     * @param z the scalar value passed to the method
     * @return max {0, z}
     */
    @Contract(pure = true)
    public static double relu (double z){
        return z > 0? z : 0;
    }


}
