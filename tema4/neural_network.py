import numpy as np


class NeuralNetwork:
    learning_rate = 0.01
    numEpochs = 10

    def __init__(self, __layers):
        self.__layers = __layers

    def feed_forward(self, inputs):
        result = inputs
        for layer in self.__layers:
            result = layer.forward(result)
        return result

    def train_model(self, train_set):
        for ins in train_set:
            res = self.feed_forward(ins[:-1])
            print(f"Output layer: {res}")
            print(f"Expected: {int(ins[7])}, Got: {np.argmax(res) + 1}")
