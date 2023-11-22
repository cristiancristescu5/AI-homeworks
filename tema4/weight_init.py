import math

import numpy as np


class WeightInit:

    @staticmethod
    def Xavier_Uniform_Distribution(num_inputs, num_outputs, gain=1.):
        lower_limit = (-1) * gain * math.sqrt(6. / (num_inputs + num_outputs))
        upper_limit = lower_limit * (-1)
        return np.random.uniform(lower_limit, upper_limit, size=(num_inputs, num_outputs))
