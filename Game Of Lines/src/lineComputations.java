public class lineComputations {


        // Function for extended Euclidean Algorithm
        static int gcdExtended(int a, int b)
        {

            // Base Case
            if (a == 0) {
                return b;
            }

            // To store results of recursive call

            return gcdExtended(b % a, a);
        }

        // method prints integer point on a line with two
        // points U and V.
        public static int[] integerPoint(int[] pointU, int[] pointV, int offset)
        {
            int run = pointU[0] - pointV[0];
            int rise = pointU[1] - pointV[1];
            int g = gcdExtended(run, rise);
            run = run / g;
            rise = rise / g;
            return new int[]{run * offset + pointU[0], rise * offset + pointU[1]};
        }
}
