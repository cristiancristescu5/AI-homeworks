import numpy as np

from neural_network import NeuralNetwork


class Validate:
    @staticmethod
    def validate(network: NeuralNetwork, validation_set):
        correctly_classified = 0
        for instance in validation_set:
            result = network.feed_forward(instance[:-1])

            if int(instance[-1]) == np.argmax(result) + 1:
                correctly_classified += 1

        print(f"Validation finished:")
        print(f"""Accuracy: {correctly_classified / len(validation_set)}""")
