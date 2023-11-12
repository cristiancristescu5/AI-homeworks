import math


def softmax(Z):
    s = sum(Z)
    return [(math.pow(math.e, i) / s) for i in Z]
