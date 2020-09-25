import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.pow

class Regressor(private val numFeatures: Int) {
    
    private var weights: DoubleArray
    private var bias = 0.0

    init {
        val random = Random()
        val weights = DoubleArray(numFeatures)
        for (x in 0 until numFeatures) {
            weights[x] = random.nextFloat().toDouble()
        }
        this.weights = weights
    }

    fun fit(x: Array<DoubleArray>, y:DoubleArray, epochs: Int, batchSize: Int) {
        val batches = batch(x, y, batchSize)
        for (e in 0 until epochs) {
            for (batch in batches) {
                val gradients = ArrayList<Array<Any>>()
                for (pair in batch) {
                    val predictions = forwardPropogate(pair.first)
                    gradients.add(calculateGradients(pair.first, predictions, pair.second))
                    println((predictions - pair.second).pow(2))
                }
                optimizeParameters(gradients, 0.001)
            }
        }
    }

    private fun forwardPropogate(x: DoubleArray): Double {
        return MatrixMath.dot(this.weights, x) + bias
    }

    private fun calculateGradients(inputs: DoubleArray, pred: Double, y: Double): Array<Any> {
        val dJ_dPred = meanSquaredErrorDerivative(pred, y)
        val dPred_dW = inputs
        val dJ_dW = MatrixMath.multiplyScaler(dPred_dW, dJ_dPred)
        val dJ_dB = dJ_dPred
        return arrayOf(dJ_dW, dJ_dB)
    } 

    private fun optimizeParameters(gradients: ArrayList<Array<Any>>, learningRate: Double) {
        val weightGradientsList = ArrayList<DoubleArray>()
        for (gradient in gradients) {
            weightGradientsList.add(gradient[0] as DoubleArray)
        }
        val weightGradients = MatrixMath.mean(weightGradientsList.toTypedArray()).toDoubleArray()
        val biasGradientsList = ArrayList<Double>()
        for (gradient in gradients) {
            biasGradientsList.add(gradient[1] as Double)
        }
        val biasGradients = (biasGradientsList.toTypedArray()).average()
        this.weights = MatrixMath.subtract(this.weights, MatrixMath.multiplyScaler(weightGradients, learningRate))
        this.bias = this.bias - (learningRate * biasGradients)
    }

    private fun meanSquaredErrorDerivative(pred: Double, y: Double): Double {
        return 2 * (pred - y)    
    }    

    private fun batch(x: Array<DoubleArray>, y: DoubleArray, batchSize: Int): List<List<Pair<DoubleArray,Double>>> {
        val data = x.zip(y.toTypedArray())
        return data.chunked(batchSize)
    }

}
