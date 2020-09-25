class MatrixMath {
    
    companion object {
    
        fun dot(a: DoubleArray, b: DoubleArray): Double {
            var dotProduct = 0.0;
            for (i in 0 until a.size) {
                dotProduct += (a[i] * b[i])
            } 
            return dotProduct
        }

        fun subtract(a: DoubleArray, b: DoubleArray): DoubleArray {
            val difference = DoubleArray(a.size)
            for (i in 0 until a.size) {
                difference[i] = (a[i] - b[i])
            }
            return difference
        }

        fun multiplyScaler(a: DoubleArray, k: Double): DoubleArray {
            val results = DoubleArray(a.size)
            for (i in 0 until a.size) {
                results[i] = a[i] * k    
            }
            return results
        }

        fun mean(x: Array<DoubleArray>): Array<Double> {
            val meanArray = ArrayList<Double>()
            for (i in 0 until x[0].size) {
                var sum = 0.0
                for (array in x) {
                    sum += array[i]
                }
                meanArray.add(sum / x.size)
            }
            return meanArray.toTypedArray()
        }
    }
}
