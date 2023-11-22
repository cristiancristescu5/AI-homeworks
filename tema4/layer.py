import numpy as np


class Layer:
    def __init__(self, rows, columns, fn, init):
        self.__weights = init(rows, columns)
        self.__activate = fn
        self.__bias = np.random.uniform(-0.5, 0.5, size=(1, columns))
        self.delta = None
        self.inputs = None
        self.outputs = None

    def forward(self, inputs):
        self.inputs = inputs
        self.outputs = self.__activate((np.matmul(inputs, self.__weights) + self.__bias).flatten())
        return self.outputs

    def set_delta(self, delta):
        self.delta = delta

    def get_weights(self):
        return self.__weights

    def set_weights(self, new_weights):
        self.__weights = new_weights
