import math

import numpy as np


def softmax(Z):
    li = []
    for i in range(0, len(Z)):
        li.append(math.pow(math.e, Z[i]))

    newli = []

    for i in range(0, len(Z)):
        newli.append(li[i] / sum(li))

    return np.array(newli)


def cross_entropy(expected, predicted):
    return (1 - expected) * math.log2(1 - expected) - expected * math.log2(predicted)


def softmax_dx(Z):
    softmax_o = np.array(Z).reshape(-1, 1)
    return np.diagflat(softmax_o) - np.dot(softmax_o, softmax_o.T)
