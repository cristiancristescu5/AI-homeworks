import numpy as np
from layer import Layer

import func


class NeuralNetwork:
    learning_rate = 0.01
    numEpochs = 10

    def __init__(self, __layers: list[Layer]):
        self.__layers = __layers

    def feed_forward(self, inputs):
        result = inputs
        outputs = []
        for layer in self.__layers:
            result = layer.forward(result)
            outputs.append(result)
        return result, outputs

    def train_model(self, train_set):
        for ins in train_set:
            res, outputs = self.feed_forward(ins[:-1])

            expected = int(ins[-1])
            output = np.argmax(res) + 1
            error = output - expected

            if expected != output:
                # for i in range(len(self.__layers) - 1, -1, -1):
                #     self.__layers[i].set_delta(error * np.diag(func.softmax_dx(outputs[i])))
                self.__layers[-1].delta = error * np.diag(func.softmax_dx(outputs[-1]))

                self.back_propagation(outputs)
            print(f"Output layer: {res}")
            print(f"Expected: {int(ins[7])}, Got: {np.argmax(res) + 1}")

    def compute_delta(self, index, outputs):
        current_layer = self.__layers[index]
        delta = np.dot(self.__layers[index].delta, np.transpose(current_layer.get_weights()))
        print(delta)
        delta = delta * func.softmax_dx(outputs[index])
        current_layer.delta = delta

    def back_propagation(self, outputs):
        # delta strat de iesire
        for ind in range(len(self.__layers) - 1, -1, -1):
            self.compute_delta(ind, outputs)

        for i in range(len(self.__layers)):
            layer = self.__layers[i]
            self.__layers[i].set_weights(layer.get_weights() - self.learning_rate * np.matmul(outputs[i], layer.delta))
