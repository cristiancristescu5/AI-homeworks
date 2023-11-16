import numpy as np


class Layer:
    def __init__(self, rows, columns, fn):
        self.__weights = np.random.uniform(-0.5, 0.5, size=(rows, columns))
        self.__activate = fn
        self.__bias = np.random.uniform(-0.5, 0.5, size=(1, columns))

    def forward(self, inputs):
        return self.__activate((np.matmul(inputs, self.__weights) + self.__bias).flatten())
