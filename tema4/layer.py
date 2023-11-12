import numpy as np


class Layer:
    def __init__(self, rows, columns, __fn):
        self.__weights = np.random.uniform(-0.5, 0.5, size=(rows, columns))
        self.__activate = __fn

    def forward(self, inputs):
        return np.matmul(inputs, self.__weights)

