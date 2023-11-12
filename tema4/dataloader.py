import numpy as np


def load_data():
    with (open("seeds_dataset.txt", 'r') as file):
        lines = file.readlines()
    data = [[num for num in line.split()] for line in lines]
    inputs = np.array(list(map(lambda x: np.array(x, dtype=np.float32), data)))

    np.random.shuffle(inputs)
    print(inputs.shape)
    num = int(0.8 * inputs.shape[0])
    return inputs[:num], inputs[num:inputs.shape[0]]

