from neural_network import *
import numpy as np
from func import Activation, LossFunction


class Training:
    @staticmethod
    def run(network: NeuralNetwork, training_set, epochs, batch_size, learning_rate): pass


class Backpropagation(Training):
    possible_outputs = np.array([
        [1, 0, 0],
        [0, 1, 0],
        [0, 0, 1]
    ])

    @staticmethod
    def run(network: NeuralNetwork, training_set, epochs, batch_size, learning_rate=.01):
        assert len(training_set) % batch_size == 0
        batches = np.split(training_set, batch_size)
        for epoch in range(1, epochs + 1):
            correctly_classified = 0.
            loss = 0
            for batch in batches:
                ret_correctly_classified, loss = Backpropagation.__train(network, learning_rate, batch)
                correctly_classified += ret_correctly_classified

            print(f"Finished epoch {epoch} with accuracy: {correctly_classified / len(training_set)} "
                  f"with loss={loss / len(training_set)}")

            if epoch in (epochs / 4, epochs / 2, 3 * epochs / 4):
                learning_rate /= 4.

    @staticmethod
    def __train(network, learning_rate, data):
        correctly_classified = 0
        loss = 0.
        for instance in data:
            result = network.feed_forward(instance[:-1])

            output = np.argmax(result)

            if int(instance[-1]) == output + 1:
                correctly_classified += 1
                continue

            network.layers[-1].delta = result - Backpropagation.possible_outputs[output]
            loss += LossFunction.CEL(result, Backpropagation.possible_outputs[output])

            Backpropagation.__propagate(network.layers, learning_rate)
        return correctly_classified, loss

    @staticmethod
    def __compute_delta(layers, index, learning_rate):
        current_layer = layers[index]
        delta = layers[index + 1].delta @ layers[index + 1].weights.T
        current_layer.delta = np.zeros_like(current_layer.weights) + learning_rate * np.dot(
            delta, np.diag(Activation.dx[current_layer.activate](current_layer.output))
        )

    @staticmethod
    def __propagate(layers, learning_rate):
        for index in range(len(layers) - 2, -1, -1):
            Backpropagation.__compute_delta(layers, index, learning_rate)

        for layer in layers:
            layer.bias += learning_rate * np.sum(layer.delta, axis=0)
            layer.weights += learning_rate * layer.delta
