class NeuralNetwork:
    learning_rate = 0.01

    def __init__(self, __layers):
        self.__layers = __layers

    def feed_forward(self, inputs):
        result = inputs
        for layer in self.__layers:
            result = layer.forward(result)
        return result

    # def batch_training(self, learning_rate=0.01, bias=0., epochs=10):
        # for i in range(1, epochs + 1):
