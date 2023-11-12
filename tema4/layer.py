import numpy as np


class Layer:
    def __init__(self, rows, columns, fn):
        self.__weights = np.random.uniform(0.1, 0.6, size=(rows, columns))
        self.__activate = fn

    def forward(self, inputs, bias=0.):
        return self.__activate(np.matmul(inputs, self.__weights) + bias)
